using Microsoft.EntityFrameworkCore;
using ApiTuristas.Models;
namespace ApiTuristas.Data
{

    public class AppDbContext: DbContext{

         public AppDbContext(DbContextOptions<AppDbContext> options): base(options){}

         public DbSet<Turista> turistas {get; set;}


    }



}
