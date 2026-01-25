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
    }
}
