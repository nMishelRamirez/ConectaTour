using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiTuristas.Models;
using ApiTuristas.Response;
using ApiTuristas.Data;
using System.Collections.Generic;
using System.Linq;

namespace ApiTuristas.Controllers
{
    [ApiController]
    [Route("api/turistas")]
    public class TuristasController : ControllerBase
    {
        private readonly AppDbContext _context;

        public TuristasController(AppDbContext context)
        {
            _context = context;
        }
        // ================= REGISTER =================
        [HttpPost("register")]
        public async Task<ActionResult> Register([FromBody] Turista turista)
        {
            // Validación de campos obligatorios
            if (string.IsNullOrEmpty(turista.Nombre) ||
                string.IsNullOrEmpty(turista.Correo) ||
                string.IsNullOrEmpty(turista.Celular) ||
                string.IsNullOrEmpty(turista.Contraseña))
            {
                return BadRequest(new ApiResponse<string>
                {
                    Estado = "Error",
                    Codigo = "400",
                    Mensaje = "Todos los campos son obligatorios",
                    Datos = {}
                });
            }

            // Verificar si el correo ya existe en la BD
            var correoExiste = await _context.turistas
                .AnyAsync(x => x.Correo == turista.Correo);

            if (correoExiste)
            {
                return BadRequest(new ApiResponse<string>
                {
                    Estado = "Error",
                    Codigo = "409",
                    Mensaje = "El correo ya está registrado",
                    Datos = {}
                });
            }

            // Agregar el turista a la BD
            _context.turistas.Add(turista);
            await _context.SaveChangesAsync(); // El Id se genera automáticamente

            return Ok(new ApiResponse<Turista>
            {
                Estado = "Ok",
                Codigo = "200",
                Mensaje = "Turista registrado correctamente",
                Datos = turista
            });
        }

        // ================= LOGIN =================
        [HttpPost("login")]
        public async Task<ActionResult> Login([FromBody] Turista login)
        {
            // Buscar en la BD por correo y contraseña
            var turista = await _context.turistas
                .FirstOrDefaultAsync(x =>
                    x.Correo == login.Correo &&
                    x.Contraseña == login.Contraseña);

            if (turista == null)
            {
                return Unauthorized(new ApiResponse<string>
                {
                    Estado = "Error",
                    Codigo = "401",
                    Mensaje = "Credenciales incorrectas",
                    Datos = {}
                });
            }

            return Ok(new ApiResponse<Turista>
            {
                Estado = "Ok",
                Codigo = "200",
                Mensaje = "Login exitoso",
                Datos = turista
            });
        }

        // ================= PERFIL =================
        [HttpGet("perfil/{id}")]
        public async Task<ActionResult> GetPerfil(int id)
        {
            // Buscar turista por ID en la BD
            var turista = await _context.turistas
                .FindAsync(id);

            if (turista == null)
            {
                return NotFound(new ApiResponse<string>
                {
                    Estado = "Error",
                    Codigo = "404",
                    Mensaje = "Turista no encontrado",
                    Datos = {}
                });
            }

            return Ok(new ApiResponse<Turista>
            {
                Estado = "Ok",
                Codigo = "200",
                Mensaje = "Perfil del turista",
                Datos = turista
            });
        }

        // ================= EDITAR PERFIL =================
        [HttpPut("perfil/{id}")]
        public async Task<ActionResult> UpdatePerfil(int id, [FromBody] Turista datos)
        {
            // Buscar turista en la BD
            var turista = await _context.turistas
                .FindAsync(id);

            if (turista == null)
            {
                return BadRequest(new ApiResponse<string>
                {
                    Estado = "Error",
                    Codigo = "404",
                    Mensaje = "El turista no existe",
                    Datos = {}
                });
            }

            // Actualizar campos
            turista.Nombre = datos.Nombre;
            turista.Correo = datos.Correo;
            turista.Celular = datos.Celular;

            // Guardar cambios en la BD
            await _context.SaveChangesAsync();

            return Ok(new ApiResponse<Turista>
            {
                Estado = "Ok",
                Codigo = "200",
                Mensaje = "Perfil actualizado correctamente",
                Datos = turista
            });
        }
    }
}