using ApiCategoria.Models;
using ApiImagen.Models;

namespace ApiAtractivo.Models
{
    public class AtractivoTuristico
    {
        public int AtractivoId { get; set; }
        public string Nombre { get; set; }
        public string Descripcion { get; set; }
        public string Direccion { get; set; }
        
        // AGREGAR ESTAS PROPIEDADES
        public double Latitud { get; set; }
        public double Longitud { get; set; }
        
        public string Horario { get; set; }
        public string PrecioEntrada { get; set; }
        public string Actividades { get; set; }
        public string ImagenPrincipal { get; set; }

        public int CategoriaId { get; set; }
        public Categoria Categoria { get; set; }

        public ICollection<ImagenAtractivo> Imagenes { get; set; }
    }
}