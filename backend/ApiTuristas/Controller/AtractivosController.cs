using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiTuristas.Data;

namespace ApiTuristas.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AtractivosController : ControllerBase
    {
        private readonly TurismoContext _context;

        public AtractivosController(TurismoContext context)
        {
            _context = context;
        }

        // GET: api/atractivos
        [HttpGet]
        public async Task<IActionResult> GetAtractivos()
        {
            var atractivos = await _context.Atractivos
                .Include(a => a.Categoria)
                .Include(a => a.Imagenes)
                .ToListAsync();

            return Ok(atractivos);
        }

        // GET: api/atractivos/1
        [HttpGet("{id}")]
        public async Task<IActionResult> GetAtractivo(int id)
        {
            var atractivo = await _context.Atractivos
                .Include(a => a.Categoria)
                .Include(a => a.Imagenes)
                .FirstOrDefaultAsync(a => a.AtractivoId == id);

            if (atractivo == null)
                return NotFound();

            return Ok(atractivo);
        }

        // GET: api/atractivos/categoria/2
        [HttpGet("categoria/{categoriaId}")]
        public async Task<IActionResult> GetAtractivosPorCategoria(int categoriaId)
        {
            var atractivos = await _context.Atractivos
                .Where(a => a.CategoriaId == categoriaId)
                .Include(a => a.Imagenes)
                .ToListAsync();

            return Ok(atractivos);
        }
    }
}
