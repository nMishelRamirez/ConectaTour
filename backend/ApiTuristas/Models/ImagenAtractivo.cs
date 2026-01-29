using ApiAtractivo.Models;

namespace ApiImagen.Models
{
    public class ImagenAtractivo
{
    public int ImagenId { get; set; }
    public string UrlImagen { get; set; }

    public int AtractivoId { get; set; }
    public AtractivoTuristico Atractivo { get; set; }
}
    
}

