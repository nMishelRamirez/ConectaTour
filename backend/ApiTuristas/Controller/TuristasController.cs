using Microsoft.AspNetCore.Mvc;
using ApiTuristas.Models;
using ApiTuristas.Response;
using System.Collections.Generic;
using System.Linq;

namespace ApiTuristas.Controllers
{
    [ApiController]
    [Route("api/turistas")]
    public class TuristasController : ControllerBase
    {
        private static List<Turista> turistas = new List<Turista>();

        // ================= REGISTER =================
        [HttpPost("register")]
        public ActionResult Register([FromBody] Turista turista)
        {
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
                    Datos = null
                });
            }

            if (turistas.Any(x => x.Correo == turista.Correo))
            {
                return BadRequest(new ApiResponse<string>
                {
                    Estado = "Error",
                    Codigo = "409",
                    Mensaje = "El correo ya está registrado",
                    Datos = null
                });
            }

            turista.Id = turistas.Count + 1;
            turistas.Add(turista);

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
        public ActionResult Login([FromBody] Turista login)
        {
            var turista = turistas.FirstOrDefault(x =>
                x.Correo == login.Correo &&
                x.Contraseña == login.Contraseña    );

            if (turista == null)
            {
                return Unauthorized(new ApiResponse<string>
                {
                    Estado = "Error",
                    Codigo = "401",
                    Mensaje = "Credenciales incorrectas",
                    Datos = null
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

        // ================= HOME =================
        /*[HttpGet("home/{id}")]
        public ActionResult Home(int id)
        {
            var turista = turistas.FirstOrDefault(x => x.Id == id);

            if (turista == null)
                return NotFound();

            return Ok(new ApiResponse<string>
            {
                Estado = "Ok",
                Codigo = "200",
                Mensaje = $"Bienvenido {turista.Nombre}",
                Datos = "Contenido principal de la app de turismo"
            });
        }*/

        // ================= PERFIL =================
        [HttpGet("perfil/{id}")]
        public ActionResult GetPerfil(int id)
        {
            var turista = turistas.FirstOrDefault(x => x.Id == id);

            if (turista == null)
                return NotFound();

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
        public ActionResult UpdatePerfil(int id, [FromBody] Turista datos)
        {
            var turista = turistas.FirstOrDefault(x => x.Id == id);

            if (turista == null)
            {
                return BadRequest(new ApiResponse<string>
                {
                    Estado = "Error",
                    Codigo = "404",
                    Mensaje = "El turista no existe",
                    Datos = null
                });
            }

            turista.Nombre = datos.Nombre;
            turista.Correo = datos.Correo;
            turista.Celular = datos.Celular;

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
