using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiTuristas.Models;
using ApiTuristas.Response;
using ApiTuristas.Data;
using ApiTuristas.DTOs;
using System.Threading.Tasks;

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
        public async Task<ActionResult> Register([FromBody] RegisterDto dto)
        {
            // Validación de campos obligatorios
            if (string.IsNullOrEmpty(dto.Nombre) ||
                string.IsNullOrEmpty(dto.Correo) ||
                string.IsNullOrEmpty(dto.Celular) ||
                string.IsNullOrEmpty(dto.Contraseña))
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
                .AnyAsync(x => x.Correo == dto.Correo);

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

            // Crear el turista desde el DTO
            var turista = new Turista
            {
                Nombre = dto.Nombre,
                Celular = dto.Celular,
                Correo = dto.Correo,
                Contraseña = dto.Contraseña
            };

            // Agregar el turista a la BD
            _context.turistas.Add(turista);
            await _context.SaveChangesAsync();

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
        public async Task<ActionResult> Login([FromBody] LoginDto dto)
        {
            // Buscar en la BD por correo y contraseña
            var turista = await _context.turistas
                .FirstOrDefaultAsync(x =>
                    x.Correo == dto.Correo &&
                    x.Contraseña == dto.Contraseña);

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
            var turista = await _context.turistas.FindAsync(id);

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

        // ================= EDITAR PERFIL ================= sumar un cerrar sesion en profle frgament manda al login
        [HttpPut("perfil/{id}")]
        public async Task<ActionResult> UpdatePerfil(int id, [FromBody] UpdatePerfilDto dto)
        {
            var turista = await _context.turistas.FindAsync(id);

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
            turista.Nombre = dto.Nombre;
            turista.Correo = dto.Correo;
            turista.Celular = dto.Celular;

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