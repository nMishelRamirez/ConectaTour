using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiTuristas.Data;

namespace ApiTuristas.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class CategoriasController : ControllerBase
    {
        private readonly TurismoContext _context;

        public CategoriasController(TurismoContext context)
        {
            _context = context;
        }

        // GET: api/categorias
        [HttpGet]
        public async Task<IActionResult> GetCategorias()
        {
            var categorias = await _context.Categorias.ToListAsync();
            return Ok(categorias);
        }

        // GET: api/categorias/1
        [HttpGet("{id}")]
        public async Task<IActionResult> GetCategoria(int id)
        {
            var categoria = await _context.Categorias
                .Include(c => c.Atractivos)
                .FirstOrDefaultAsync(c => c.CategoriaId == id);

            if (categoria == null)
                return NotFound();

            return Ok(categoria);
        }
    }
}
