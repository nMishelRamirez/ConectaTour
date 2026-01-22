using Microsoft.EntityFrameworkCore;
using ApiCategoria.Models;
using ApiAtractivo.Models;
using ApiImagen.Models;
namespace ApiTuristas.Data
{
    public class TurismoContext : DbContext
{
    public DbSet<Categoria> Categorias { get; set; }
    public DbSet<AtractivoTuristico> Atractivos { get; set; }
    public DbSet<ImagenAtractivo> Imagenes { get; set; }
}
}

