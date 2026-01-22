
using ApiAtractivo.Models;
using ApiImagen.Models;

namespace ApiCategoria.Models
{
    public class Categoria
{
    public int CategoriaId { get; set; }
    public string Nombre { get; set; }
    public string Descripcion { get; set; }

    public ICollection<AtractivoTuristico> Atractivos { get; set; }
}
}
