using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ApiTuristas.Migrations
{
    /// <inheritdoc />
    public partial class UpdateCoordenadasAtractivos : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            // Actualizar coordenadas para los lugares (Precisión Google Maps)
            migrationBuilder.Sql(@"
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.250323, ""Longitud"" = -78.518625 WHERE ""AtractivoId"" = 1; -- Virgen del Panecillo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.002188, ""Longitud"" = -78.455061 WHERE ""AtractivoId"" = 2; -- Mitad del Mundo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.192094, ""Longitud"" = -78.519486 WHERE ""AtractivoId"" = 3; -- Teleférico
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.209661, ""Longitud"" = -78.495017 WHERE ""AtractivoId"" = 4; -- MUNA
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.221796, ""Longitud"" = -78.514289 WHERE ""AtractivoId"" = 5; -- Museo del Pasillo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.133329, ""Longitud"" = -78.493104 WHERE ""AtractivoId"" = 6; -- Museo FAE
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.222724, ""Longitud"" = -78.515273 WHERE ""AtractivoId"" = 7; -- Carmen Alto
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.221797, ""Longitud"" = -78.513227 WHERE ""AtractivoId"" = 8; -- Casa de Sucre
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.218843, ""Longitud"" = -78.527511 WHERE ""AtractivoId"" = 9; -- Templo de la Patria
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.221256, ""Longitud"" = -78.515888 WHERE ""AtractivoId"" = 10; -- Casa del Alabado
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.006456, ""Longitud"" = -78.448403 WHERE ""AtractivoId"" = 11; -- Templo del Sol
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.219766, ""Longitud"" = -78.511397 WHERE ""AtractivoId"" = 12; -- Museo Mana
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.222274, ""Longitud"" = -78.515814 WHERE ""AtractivoId"" = 13; -- Museo de la Ciudad
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.223847, ""Longitud"" = -78.512683 WHERE ""AtractivoId"" = 14; -- Convento Sto Domingo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.165604, ""Longitud"" = -78.471926 WHERE ""AtractivoId"" = 15; -- Casa Carlota Jaramillo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.220194, ""Longitud"" = -78.515159 WHERE ""AtractivoId"" = 16; -- Museo Padre Almeida
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.218732, ""Longitud"" = -78.508569 WHERE ""AtractivoId"" = 17; -- Antiguo Círculo Militar
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.220268, ""Longitud"" = -78.511874 WHERE ""AtractivoId"" = 18; -- Centro Cultural Metropolitano
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.211758, ""Longitud"" = -78.504936 WHERE ""AtractivoId"" = 19; -- CAC (San Juan)
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.221444, ""Longitud"" = -78.503611 WHERE ""AtractivoId"" = 20; -- Itchimbía
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.223847, ""Longitud"" = -78.512683 WHERE ""AtractivoId"" = 21; -- Iglesia Sto Domingo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.220197, ""Longitud"" = -78.510619 WHERE ""AtractivoId"" = 22; -- Catedral Metropolitana
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.219665, ""Longitud"" = -78.511394 WHERE ""AtractivoId"" = 23; -- Iglesia San Agustín
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.201886, ""Longitud"" = -78.489725 WHERE ""AtractivoId"" = 24; -- Santa Teresita
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.235941, ""Longitud"" = -78.459344 WHERE ""AtractivoId"" = 25; -- Iglesia Guápulo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.222386, ""Longitud"" = -78.515250 WHERE ""AtractivoId"" = 26; -- Santa Clara
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.220361, ""Longitud"" = -78.511972 WHERE ""AtractivoId"" = 27; -- El Sagrario
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.214815, ""Longitud"" = -78.502931 WHERE ""AtractivoId"" = 28; -- El Belén
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.221389, ""Longitud"" = -78.512778 WHERE ""AtractivoId"" = 29; -- La Compañía
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.220556, ""Longitud"" = -78.515278 WHERE ""AtractivoId"" = 30; -- San Francisco
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.218528, ""Longitud"" = -78.509778 WHERE ""AtractivoId"" = 31; -- Carmen Bajo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.222536, ""Longitud"" = -78.515233 WHERE ""AtractivoId"" = 32; -- Plaza Santa Clara
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.223833, ""Longitud"" = -78.511667 WHERE ""AtractivoId"" = 33; -- Plaza Sto Domingo
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.184344, ""Longitud"" = -78.485125 WHERE ""AtractivoId"" = 34; -- Jardín Botánico
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.165417, ""Longitud"" = -78.468861 WHERE ""AtractivoId"" = 35; -- Jardín Alado
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.047528, ""Longitud"" = -78.466750 WHERE ""AtractivoId"" = 36; -- Pomasquinde
                UPDATE ""Atractivos"" SET ""Latitud"" = 0.015389, ""Longitud"" = -78.681194 WHERE ""AtractivoId"" = 37; -- Bellavista Cloud Forest
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.221972, ""Longitud"" = -78.511611 WHERE ""AtractivoId"" = 38; -- Teatro Bolívar
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.220194, ""Longitud"" = -78.510611 WHERE ""AtractivoId"" = 39; -- Teatro Sucre
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.214778, ""Longitud"" = -78.507111 WHERE ""AtractivoId"" = 40; -- Basílica Voto Nacional
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.211758, ""Longitud"" = -78.504936 WHERE ""AtractivoId"" = 41; -- CAC (Repetido)
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.180472, ""Longitud"" = -78.487861 WHERE ""AtractivoId"" = 42; -- Mercado Iñaquito
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.198306, ""Longitud"" = -78.490722 WHERE ""AtractivoId"" = 43; -- Palacio Circasiana
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.208167, ""Longitud"" = -78.492278 WHERE ""AtractivoId"" = 44; -- Mercado Artesanal
                UPDATE ""Atractivos"" SET ""Latitud"" = 0.120556, ""Longitud"" = -78.841667 WHERE ""AtractivoId"" = 45; -- Tulipe
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.187806, ""Longitud"" = -78.473528 WHERE ""AtractivoId"" = 46; -- Capilla del Hombre
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.201778, ""Longitud"" = -78.484889 WHERE ""AtractivoId"" = 47; -- Quito Jazz Club
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.219778, ""Longitud"" = -78.511528 WHERE ""AtractivoId"" = 48; -- Heladería San Agustín
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.220806, ""Longitud"" = -78.512056 WHERE ""AtractivoId"" = 49; -- República del Cacao
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.194472, ""Longitud"" = -78.481194 WHERE ""AtractivoId"" = 50; -- Salsoteca Lavoe
                UPDATE ""Atractivos"" SET ""Latitud"" = -0.220556, ""Longitud"" = -78.511667 WHERE ""AtractivoId"" = 51; -- Cacao & Cacao
            ");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {

        }
    }
}
