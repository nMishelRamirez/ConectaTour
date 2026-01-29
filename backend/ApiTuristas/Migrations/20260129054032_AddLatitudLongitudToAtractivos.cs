using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ApiTuristas.Migrations
{
    /// <inheritdoc />
    public partial class AddLatitudLongitudToAtractivos : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<double>(
                name: "Latitud",
                table: "Atractivos",
                type: "double precision",
                nullable: false,
                defaultValue: 0.0);

            migrationBuilder.AddColumn<double>(
                name: "Longitud",
                table: "Atractivos",
                type: "double precision",
                nullable: false,
                defaultValue: 0.0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Latitud",
                table: "Atractivos");

            migrationBuilder.DropColumn(
                name: "Longitud",
                table: "Atractivos");
        }
    }
}
