package com.epn.projectconectatour

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epn.projectconectatour.network.RetrofitClient
import com.epn.projectconectatour.network.models.AtractivoHome
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NearMeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var userLocation: LatLng? = null
    private val sites = mutableListOf<SiteWithCoordinates>()
    private lateinit var adapter: DestinationsAdapter

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001

        // IDs de los atractivos que queremos mostrar (5-6 lugares)
        private val FEATURED_SITES = listOf(1, 2, 40, 29, 30, 22)
    }

    // Mapa hardcodeado de coordenadas (temporal)
    private val coordinatesMap = mapOf(
        1 to LatLng(-0.214587, -78.508392),   // Virgen del Panecillo
        2 to LatLng(-0.002457, -78.455734),   // Mitad del Mundo
        40 to LatLng(-0.214920, -78.506500),  // Basílica del Voto Nacional
        29 to LatLng(-0.216667, -78.516667),  // Iglesia Compañía de Jesús
        30 to LatLng(-0.216667, -78.516667),  // Convento de San Francisco
        22 to LatLng(-0.220833, -78.512778)   // Catedral Metropolitana
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_near_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        // Configurar MapView
        mapView = view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // Botón de regreso
        val backButton = view.findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Configurar RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.destinationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = DestinationsAdapter(sites)
        recyclerView.adapter = adapter

        // Cargar sitios desde la API
        cargarAtractivosCercanos()

        // Solicitar permisos de ubicación
        checkAndRequestLocationPermission()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap?.uiSettings?.apply {
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }
        enableUserLocation()
    }

    private fun cargarAtractivosCercanos() {
        RetrofitClient.atractivosApi.getAtractivosHome()
            .enqueue(object : Callback<List<AtractivoHome>> {
                override fun onResponse(
                    call: Call<List<AtractivoHome>>,
                    response: Response<List<AtractivoHome>>
                ) {
                    if (response.isSuccessful) {
                        sites.clear()

                        // Filtrar solo los atractivos que están en FEATURED_SITES
                        response.body()?.filter { it.id in FEATURED_SITES }?.forEach { atractivo ->
                            val coords = coordinatesMap[atractivo.id]

                            if (coords != null) {
                                sites.add(
                                    SiteWithCoordinates(
                                        id = atractivo.id,
                                        title = atractivo.nombre,
                                        description = "",  // No viene en AtractivoHome
                                        imageUrl = atractivo.imagenPrincipal,
                                        category = atractivo.categoria,
                                        latitude = coords.latitude,
                                        longitude = coords.longitude
                                    )
                                )
                            }
                        }

                        // Ordenar por distancia si tenemos ubicación del usuario
                        userLocation?.let { calculateDistancesAndSort(it) }

                        // Actualizar UI
                        adapter.notifyDataSetChanged()
                        addMarkersToMap()
                        centerCameraOnLocations()
                    } else {
                        Toast.makeText(context, "Error al cargar atractivos", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<AtractivoHome>>, t: Throwable) {
                    Toast.makeText(context, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            })
    }

    private fun checkAndRequestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            enableUserLocation()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun enableUserLocation() {
        // Verificar permisos
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Si no tenemos permisos, solicitarlos
            checkAndRequestLocationPermission()
            return
        }

        // IMPORTANTE: Habilitar la capa de "Mi ubicación" en el mapa
        try {
            googleMap?.isMyLocationEnabled = true
            googleMap?.uiSettings?.isMyLocationButtonEnabled = true

            // Obtener la última ubicación conocida
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    userLocation = LatLng(location.latitude, location.longitude)

                    // Calcular distancias
                    calculateDistancesAndSort(userLocation!!)

                    // Actualizar marcadores y lista
                    addMarkersToMap()
                    adapter.notifyDataSetChanged()

                    // Centrar cámara
                    if (sites.isNotEmpty()) {
                        centerCameraOnLocations()
                    } else {
                        // Si no hay sitios aún, centrar en la ubicación del usuario
                        googleMap?.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(userLocation!!, 13f)
                        )
                    }
                } else {
                    // No hay ubicación disponible, solicitar actualización
                    requestNewLocationData()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(context, "Error al obtener ubicación: ${e.message}", Toast.LENGTH_SHORT).show()
                // Centrar en Quito por defecto
                val quitoCenter = LatLng(-0.1807, -78.4678)
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(quitoCenter, 12f))
                addMarkersToMap()
            }
        } catch (e: SecurityException) {
            Toast.makeText(context, "Error de permisos de ubicación", Toast.LENGTH_SHORT).show()
        }
    }

    // Método para solicitar una nueva ubicación si lastLocation es null
    private fun requestNewLocationData() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val locationRequest = com.google.android.gms.location.LocationRequest.Builder(
            com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
            10000 // 10 segundos
        ).apply {
            setMinUpdateIntervalMillis(5000) // 5 segundos
            setMaxUpdates(1) // Solo una actualización
        }.build()

        val locationCallback = object : com.google.android.gms.location.LocationCallback() {
            override fun onLocationResult(locationResult: com.google.android.gms.location.LocationResult) {
                locationResult.lastLocation?.let { location ->
                    userLocation = LatLng(location.latitude, location.longitude)
                    calculateDistancesAndSort(userLocation!!)
                    addMarkersToMap()
                    adapter.notifyDataSetChanged()

                    if (sites.isNotEmpty()) {
                        centerCameraOnLocations()
                    } else {
                        googleMap?.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(userLocation!!, 13f)
                        )
                    }

                    // Remover actualizaciones después de obtener la ubicación
                    fusedLocationClient.removeLocationUpdates(this)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private fun calculateDistancesAndSort(userLatLng: LatLng) {
        val userLoc = Location("").apply {
            latitude = userLatLng.latitude
            longitude = userLatLng.longitude
        }

        sites.forEach { site ->
            val siteLoc = Location("").apply {
                latitude = site.latitude
                longitude = site.longitude
            }
            site.distanceInMeters = userLoc.distanceTo(siteLoc)
        }

        sites.sortBy { it.distanceInMeters }
    }

    private fun addMarkersToMap() {
        googleMap?.clear()

        sites.forEach { site ->
            val position = LatLng(site.latitude, site.longitude)
            googleMap?.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(site.title)
                    .snippet(if (site.distanceInMeters > 0) "${(site.distanceInMeters / 1000).format(2)} km" else "")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            )
        }
    }

    private fun centerCameraOnLocations() {
        if (sites.isEmpty() || googleMap == null) return

        val builder = LatLngBounds.Builder()

        userLocation?.let { builder.include(it) }

        sites.forEach { site ->
            builder.include(LatLng(site.latitude, site.longitude))
        }

        val bounds = builder.build()
        val padding = 100

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
    }

    private fun Float.format(decimals: Int): String {
        return "%.${decimals}f".format(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation()
            } else {
                Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class DestinationsAdapter(private val sites: List<SiteWithCoordinates>) :
        RecyclerView.Adapter<DestinationsAdapter.DestinationViewHolder>() {

        inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.destinationNameTextView)
            val imageView: ImageView = itemView.findViewById(R.id.destinationImageView)
            val detailsButton: MaterialButton = itemView.findViewById(R.id.detailsButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_destination, parent, false)
            return DestinationViewHolder(view)
        }

        override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
            val site = sites[position]

            val distanceText = if (site.distanceInMeters > 0) {
                " • ${(site.distanceInMeters / 1000).format(2)} km"
            } else ""

            holder.nameTextView.text = "${site.title}$distanceText"

            Glide.with(holder.itemView.context)
                .load(site.imageUrl)
                .circleCrop()
                .into(holder.imageView)

            holder.detailsButton.setOnClickListener {
                val intent = Intent(holder.itemView.context, SiteDetailActivity::class.java).apply {
                    putExtra("SITE_ID", site.id)
                }
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount() = sites.size
    }

    // Ciclo de vida del MapView
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}

// Data class temporal con coordenadas
data class SiteWithCoordinates(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: String,
    val latitude: Double,
    val longitude: Double,
    var distanceInMeters: Float = 0f
)