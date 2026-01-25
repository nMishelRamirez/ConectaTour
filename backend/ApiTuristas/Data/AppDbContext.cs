using Microsoft.EntityFrameworkCore;
using ApiTuristas.Models;
using ApiAtractivo.Models;
using ApiCategoria.Models;
using ApiImagen.Models;
namespace ApiTuristas.Data
{

    public class AppDbContext: DbContext{

         public AppDbContext(DbContextOptions<AppDbContext> options): base(options){}
         // Tabla de usuarios
         public DbSet<Turista> turistas {get; set;}

         // Tablas de atractivos
        public DbSet<AtractivoTuristico> Atractivos { get; set; }
        public DbSet<Categoria> Categorias { get; set; }
        public DbSet<ImagenAtractivo> Imagenes { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Configurar primary keys explícitamente
            modelBuilder.Entity<AtractivoTuristico>().HasKey(a => a.AtractivoId);
            modelBuilder.Entity<Categoria>().HasKey(c => c.CategoriaId);
            modelBuilder.Entity<ImagenAtractivo>().HasKey(i => i.ImagenId);

            // Relaciones de atractivos
            modelBuilder.Entity<Categoria>()
                .HasMany(c => c.Atractivos)
                .WithOne(a => a.Categoria)
                .HasForeignKey(a => a.CategoriaId)
                .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<AtractivoTuristico>()
                .HasMany(a => a.Imagenes)
                .WithOne(i => i.Atractivo)
                .HasForeignKey(i => i.AtractivoId)
                .OnDelete(DeleteBehavior.Cascade);

            base.OnModelCreating(modelBuilder);
        }



    }



}
