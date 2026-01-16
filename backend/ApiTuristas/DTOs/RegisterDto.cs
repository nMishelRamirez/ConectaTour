namespace ApiTuristas.DTOs
{
    public class RegisterDto
    {
        public string Nombre { get; set; } = string.Empty;
        public string Celular { get; set; } = string.Empty;
        public string Correo { get; set; } = string.Empty;
        public string Contraseña { get; set; } = string.Empty;
    }
}