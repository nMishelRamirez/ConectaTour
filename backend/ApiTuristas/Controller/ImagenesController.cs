using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiTuristas.Data;

namespace ApiTuristas.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ImagenesController : ControllerBase
    {
        private readonly TurismoContext _context;

        public ImagenesController(TurismoContext context)
        {
            _context = context;
        }

        // GET: api/imagenes/atractivo/1
        [HttpGet("atractivo/{atractivoId}")]
        public async Task<IActionResult> GetImagenesPorAtractivo(int atractivoId)
        {
            var imagenes = await _context.Imagenes
                .Where(i => i.AtractivoId == atractivoId)
                .ToListAsync();

            return Ok(imagenes);
        }
    }
}
