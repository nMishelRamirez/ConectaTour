using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiTuristas.Data;

namespace ApiAtractivo.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AtractivosController : ControllerBase
    {
        private readonly AppDbContext _context;

        public AtractivosController(AppDbContext context)
        {
            _context = context;
        }

        // GET: api/Atractivos/Home
        [HttpGet("Home")]
        public async Task<IActionResult> GetAtractivosHome()
        {
            var atractivos = await _context.Atractivos
                .Include(a => a.Categoria)
                .Select(a => new
                {
                    Id = a.AtractivoId,
                    Nombre = a.Nombre,
                    ImagenPrincipal = a.ImagenPrincipal,
                    Categoria = a.Categoria.Nombre
                })
                .ToListAsync();

            return Ok(atractivos);
        }

        [HttpGet("NearMe")]
        public async Task<IActionResult> GetAtractivosNearMe()
        {
            var atractivos = await _context.Atractivos
                .Include(a => a.Categoria)
                .Select(a => new
                {
                    Id = a.AtractivoId,
                    Nombre = a.Nombre,
                    ImagenPrincipal = a.ImagenPrincipal,
                    Categoria = a.Categoria.Nombre,
                    Latitud = a.Latitud,
                    Longitud = a.Longitud
                })
                .ToListAsync();

            return Ok(atractivos);
        }

        // GET: api/Atractivos/Detalle/id
        [HttpGet("Detalle/{id}")]
        public async Task<IActionResult> GetAtractivoDetalle(int id)
        {
            var atractivo = await _context.Atractivos
                .Include(a => a.Categoria)
                .Include(a => a.Imagenes)
                .FirstOrDefaultAsync(a => a.AtractivoId == id);

            if (atractivo == null)
                return NotFound();

            var detalle = new
            {
                InformacionGeneral = new
                {
                    Nombre = atractivo.Nombre,
                    Descripcion = atractivo.Descripcion,
                    Direccion = atractivo.Direccion
                },
                InformacionAdicional = new
                {
                    Horario = atractivo.Horario,
                    PrecioEntrada = atractivo.PrecioEntrada,
                    Actividades = atractivo.Actividades,
                    ImagenesAdicionales = atractivo.Imagenes.Select(i => i.UrlImagen).ToList()
                },
                ImagenPrincipal = atractivo.ImagenPrincipal,
                Categoria = atractivo.Categoria.Nombre
            };

            return Ok(detalle);
        }
        
        // GET: api/Atractivos/Buscar?query=nombre
        [HttpGet("Buscar")]
        public async Task<IActionResult> BuscarAtractivos([FromQuery] string query)
        {
            if (string.IsNullOrEmpty(query))
            {
                return BadRequest("La consulta de búsqueda no puede estar vacía.");
            }

            var atractivos = await _context.Atractivos
                .Include(a => a.Categoria)
                .Where(a => a.Nombre.Contains(query) || a.Descripcion.Contains(query) || a.Categoria.Nombre.Contains(query))
                .Select(a => new
                {
                    Id = a.AtractivoId,
                    Nombre = a.Nombre,
                    ImagenPrincipal = a.ImagenPrincipal,
                    Categoria = a.Categoria.Nombre
                })
                .ToListAsync();

            if (atractivos.Count == 0)
            {
                return NotFound("No se encontraron resultados.");
            }

            return Ok(atractivos);
        }

        // GET: api/Atractivos/Sugerencias
        [HttpGet("Sugerencias")]
        public async Task<IActionResult> ObtenerSugerencias([FromQuery] string prefix)
        {
            var sugerencias = await _context.Atractivos
                .Where(a => a.Nombre.StartsWith(prefix))
                .Select(a => a.Nombre)
                .Take(5) 
                .ToListAsync();

            return Ok(sugerencias);
        }


    }
}
