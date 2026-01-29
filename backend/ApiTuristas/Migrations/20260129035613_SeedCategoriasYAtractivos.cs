using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ApiTuristas.Migrations
{
    /// <inheritdoc />
    public partial class SeedCategoriasYAtractivos : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
    {
        // Insertar Categorías
        migrationBuilder.Sql(@"
            INSERT INTO ""Categorias"" (""CategoriaId"", ""Nombre"") VALUES
            (1, 'Patrimonio Histórico'),
            (2, 'Religioso'),
            (3, 'Naturaleza y Miradores'),
            (4, 'Museos y Cultura'),
            (5, 'Experiencias');
        ");

        // Insertar Atractivos (algunos ejemplos)
        migrationBuilder.Sql(@"
            INSERT INTO ""Atractivos""(
                ""AtractivoId"", ""Nombre"", ""Descripcion"", ""Direccion"", 
                ""Horario"", ""PrecioEntrada"", ""Actividades"", 
                ""ImagenPrincipal"", ""CategoriaId"")
            VALUES 
            (1,'Virgen del Panecillo', 
            'Escultura de la Virgen de Quito ubicada sobre una colina que ofrece vista panorámica de la ciudad. Es una de las esculturas con mayor reconocimiento arquitectónico y artístico, misma que se ha convertido en un símbolo icónico.', 
            'Gral. Melchor Aymerich, Quito 170111', '09:30 - 21:00', 'Gratuito', 'Subida al mirador, fotografía, visita al museo de la Virgen, artesanías', 
            'https://media.elcomercio.com/wp-content/uploads/2022/11/panecillo-ec-700x391.jpg', 2),

            (2, 'Mitad del Mundo', 
            'Monumento que marca la línea ecuatorial con museo y actividades culturales. Ubicada a solo 26 km de Quito, este ícono de 30 metros de altura tiene una vista panorámica de 360° del valle de San Antonio de Pichincha. Este complejo turístico y cultural marca el lugar donde la Misión Geodésica Francesa determinó, en el siglo XVIII, el paso de la línea ecuatorial que divide la Tierra en dos hemisferios.', 
            'Av. Manuel Córdova Galarza SN, Quito 170311', '09:00 - 18:00', 'Pagado', 'Museos, fotos en la línea ecuatorial, tiendas de souvenirs', 
            'https://quitotourbus.com/wp-content/uploads/2019/12/mitad-del-mundo-en-quito.jpg', 1),

            (3, 'Cruz Loma (Teleférico)', 
            'Mirador accesible vía teleférico con vistas espectaculares de Quito y senderos de montaña. Sobrevolarás a lo largo de tres micro climas: partiendo del bosque andino formado principalmente por eucaliptos; pocos metros más arriba la vegetación cambia drásticamente, y te encontrarás con arbustos de pumamaqui, achupallas y romerillo; al final del recorrido, llegarás al páramo andino formado por pajonales, alfombrillas y chuquiraguas. El viaje que toma alrededor de 18 minutos, recorres una distancia de 2.5km.',
            'Fulgencio Araujo, y, Quito 170527, Ecuador', '10:00 - 18:00', 'Pagado', 'Subida en teleférico, senderismo, fotografía', 
            'https://www.civitatis.com/f/ecuador/quito/big/excursion-cruz-loma-teleferiqo.jpg', 3),

            (4, 'Museo Nacional del Ecuador (MUNA)', 
            'El MUNA conserva y exhibe arte e historia ecuatorianos. Es la cabeza de la Red Ecuatoriana de Museos, que promueve la integración, articulación y cooperación entre museos. El MuNa se concibe como un espacio en construcción donde la memoria, la creación, el patrimonio y la diversidad están en constante diálogo con los bienes culturales. Apuesta por la producción de conocimiento y experiencias a través de programas educativos y proyectos colaborativos.', 
            'Edificio de los Espejos, Avenida Patria (between, Av. 6 de Diciembre, Quito, Ecuador', 'Diurno', 'Pagada', 'Visitas Autoguiadas, Exposición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/MUNA.jpg',4),
            
            (5, 'Museo del Pasillo', 
            'Celebra la diversidad cultural y musical del Ecuador, promoviendo respeto e igualdad entre todas las manifestaciones artísticas, ofreciendo una visión enriquecedora de la música ecuatoriana. Con una mirada de lo propio y lo regional, este museo será un espacio público de educación, creación, investigación, diálogo, transmisión de saberes, reflexión, producción, difusión musical, consumo cultural y goce, que revitalizará la memoria social, afianzará nuestra identidad, cuidará bienes patrimoniales intangibles, relacionados con la música, el pasillo ecuatoriano; fortalecerá el pensamiento crítico y pondrá en valor las obras de nuestros compositores y músicos.', 
            'García Moreno, Quito 170401, Ecuador', '9:00 - 16:00', 'Pagado', 'Visita Guiada, Exposición, Exhibición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Museo-del-Pasillo.webp', 4),
            
            (6, 'Museo Aeronáutico y del Espacio de La FAE', 
            'Desde 1972, la Fuerza Aérea Ecuatoriana con la necesidad de contar con un espacio de almacenamiento de objetos y material fotográfico histórico que integrara los hitos transcurridos a lo largo de los años, tomó la iniciativa de buscar un sitio para dicho objetivo. Los visitantes recibirán un recorrido dirigido en donde se fomentará la cultura aeronáutica y conocerán los objetos históricos que guarda el museo.', 
            'VG84+HPX, De la Prensa Y Fernandez Salvador, Quito 170104, Ecuador', 'Diurno', 'Pagado', 'Visitas Autoguiadas, Exposición, Museo', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Museo-Aeronautico-y-del-Espacio-de-la-FAE.webp', 4),

            (7, 'Museo del Carmen Alto', 
            'Ofrece una ventana a la vida monástica de la Orden Carmelita Descalza de Quito y a la historia de Santa Mariana de Jesús, reflexionando sobre su legado religioso.', 
            'QFGM+VV3, Rocafuerte, Quito 170401, Ecuador', 'Diurno', 'Pagado', 'Visitas Autoguiadas, Exposición, Museo', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Museo-Del-Carmen-Alto.webp', 4),

            (8, 'Museo de la Defensa Casa de Sucre', 
            'Ofrece una mirada íntima a la vida del mariscal Antonio José de Sucre y su esposa Mariana Carcelén, a través de objetos personales y una detallada museografía.', 
            'QFHP+7P2, Venezuela, Quito 170401, Ecuador', 'Diurno', 'Gratuito', 'Visita Guiada, Exposición', 
            'https://upload.wikimedia.org/wikipedia/commons/8/88/Museo_Casa_de_Sucre%2C_Quito_01.jpg', 4),

            (9, 'Museo de la Defensa Templo de La Patria', 
            'El ""Templo de la Patria"", en la ladera del volcán Pichincha, conmemora la lucha por la independencia de Ecuador de 1822 y ofrece vistas panorámicas.', 
            'QFJC+CXX, Quito 170110, Ecuador', 'Diurno', 'Pagado', 'Características de Cultura, Exposición, Museos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Museo-de-la-Defensa-Templo-de-la-Patria.webp', 4),
            
            (10, 'Museo de Arte Precolombino Casa del Alabado', 
            'En el interior de la casa colonial, el Museo de Arte Precolombino Casa del Alabado alberga alrededor de 5000 piezas arqueológicas en cerámica, piedra, concha, metal, textil y madera de las cuatro regiones del Ecuador actual. La colección contiene piezas de enorme valor estético y arqueológico, correspondientes a los años 7000 a.C. hasta la llegada de los españoles alrededor de 1530 d.C.', 
            'Cuenca N1-41, Quito 170401, Ecuador', 'Diurno', 'Pagado', 'Culturales y Académicos, Mediación Guiada, Actividades Manuales, Talleres de Expresión Creativa, Actividades Manuales, Talleres, Mediación Guiada', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/casa_alabado.png', 4),

            (11, 'Museo Templo del Sol Pintor Cristobal Ortega Maila', 
            'Una majestuosa construcción de piedra exhibe piezas precolombinas y obras del pintor Cristóbal Ortega en 500 metros cuadrados de espacio, construidos con andesita.', 
            'En el Mirador de la reserva geobotanica pululahua Manuel Córdova Galarza E28 y, Eduardo Kingman Oe12 174, Quito 170603, Ecuador', 'Diurno', 'Pagado', 'Visita Guiada, Exposición, Museos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/museo_cristobal_sol.jpg', 4),

            (12, 'Museo Manla', 
            'El Museo de Arte Naïf Latinoamericano es un espacio dedicado a la promoción y difusión del arte y los artistas naïf de América Latina. El MANLA funciona en un inmueble de aproximadamente cien años de antigüedad, declarado como bien patrimonial de la nación por el Instituto Metropolitano de Patrimonio, se encuentra localizado en la ciudad de Quito - Ecuador, en el turístico barrio Mariscal Sucre. El inmueble fue completamente restaurado y reúne las condiciones funcionales y estéticas para el desarrollo de las actividades culturales previstas.', 
            'Av. 6 de Diciembre N24-275 y, Quito 170523, Ecuador', '9:30 - 13:30', 'Gratuito', 'Exposiciones, Talleres', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/08/museo_manla.jpg',4),

            (13, 'Museo de la Ciudad', 
            'Desde 1998, el Museo de la Ciudad de Quito, en el rehabilitado edificio del antiguo Hospital San Juan de Dios, exhibe la vida cotidiana de los habitantes de Quito desde la época aborigen hasta el siglo XX, a través de objetos que reflejan las artes, los oficios, la educación, la cultura y la ciudadanía.', 
            'García Moreno, Quito 170405, Ecuador', 'Diurno', 'Pagado', 'Visita Guiada, Exposición, Museos', 
            'https://www.quitoinforma.gob.ec/wp-content/uploads/2022/01/Museo-De-La-Ciudad-4-800x445.jpg', 4),

            (14, 'Museo del Convento de Santo Domingo ""Fray Pedro Bedón""', 
            'El museo expone la influencia de la comunidad Dominica hacia la sociedad en educación, arte y cultura, y atesora una valiosa colección de la Escuela quiteña de artes y oficios. Es también la puerta de acceso la famosa Capilla del Rosario.', 
            'QFGQ+F2F, Juan José Flores, Santo Domingo 170401, Ecuador', 'Diurno', 'Pagado', 'Apreciar pinturas y esculturas, Exposición, Museos', 
            'https://upload.wikimedia.org/wikipedia/commons/2/24/Museo_%28Fray_Pedro_Bedon%29_pic_011.JPG', 4),

            (15, 'Casa Museo Carlota Jaramillo', 
            'Celebra la vida y música de la ""reina del pasillo ecuatoriano"", explorando su legado y la historia del género. Una visita imperdible para conocer la riqueza cultural del Ecuador. Cuadros, imágenes, fotos, trajes, recuerdos invitan a formar parte de la cotidianidad de esta gran mujer, que forma parte de la Historia del Ecuador.', 
            'XFXP+CP3, Juan Jose FloresE2-23, Calacalí, Ecuador', 'Diurno', 'Pagado', 'Visita Guiada, Exposición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Museo-Casa-Carlota-Jaramillo.webp', 4),

            (16, 'Museo Franciscano del Padre Almeida', 
            'El Museo del Padre Almeida funciona en el interior del convento de San Diego. Aquí se exhiben obras de gran interés como el bello púlpito de la Virgen de Chichinquirá, el Cristo del Padre Almeida y la famosa obra de El Bosco, además ofrece un recorrido completo por los lugares del convento como la sacristía, el templo, los tres patios, la celda del Padre Almeida, el campanario, la sala de profundis y el refectorio.', 
            'Gral, Quito 170812, Ecuador', '10:00 - 16:00', 'Pagado', 'Visita Guiada', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Museo-Del-Padre-Almeida.webp',4),
            
            (17, 'Centro Cultural del Antiguo Círculo Militar', 
            'El Antiguo Círculo Militar de Quito, símbolo del neoclasicismo desde 1948, hoy es un museo que ofrece eventos gratuitos, promoviendo historia y cultura. En su interior, resalta el “Salón de los Espejos”, con un piso taraceado traído desde Italia, además de mobiliario y lámparas de estilo europeo. Hoy, es sede de la Academia Nacional de Historia Militar, conservando su función como espacio de memoria y patrimonio nacional.', 
            'QFJQ+9Q2, Venezuela entre Mejía y, José Joaquín Olmedo, Quito, Ecuador', 'Diurno', 'Gratuito', 'Visita Guiada, Previa Reserva, Evento Sociales, Culturales y Académicos', 
            'https://upload.wikimedia.org/wikipedia/commons/e/eb/Antiguo_C%C3%ADrculo_Militar%2C_Quito_10.jpg', 4),
            
            (18, 'Centro Cultural Metropolitano', 
            'Con una historia que abarca siglos, este símbolo arquitectónico acoge diversas actividades culturales y artísticas, enriqueciendo el panorama cultural de la ciudad.',
            'García Moreno &, Quito 170401, Ecuador', 'Diurno', 'Gratuito', 'Exposición, Centros Culturales', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/06/PHV-QU2-1.jpg', 4),

            (19, 'Centro de Arte Contemporáneo de Quito', 
            'Centro de Arte Contemporáneo de Quito, inmueble de valor histórico que fue recuperado y restaurado por el antiguo FONSAL para ser aprovechado como un espacio dedicado a las artes. Ofrece a los usuarios no solo hermosos espacios arquitectónicos para la contemplación y la gestión de las artes, sino que presenta una imagen integral en la que es digno de admirar la belleza propia de la edificación, su perfecta armonía con el barrio en el que se emplaza, y una de las vistas panorámicas más cautivadoras y de más fácil acceso de Quito.',
            'Montevideo, y, Quito 170103, Ecuador', 'Diurno', 'Gratuito', 'Exhibición, Exposición, Centros Culturales', 
            'https://www.clave.com.ec/wp-content/uploads/2016/01/MG_7497.jpg', 4),

            (20, 'Centro Cultural Itchimbía', 
            'Inaugurado en 2004, es un epicentro del arte y la cultura, que fomenta la expresión artística y el intercambio cultural en un espacio único. El Centro ofrece una programación diversa y dinámica: exposiciones temporales, conciertos, presentaciones teatrales, talleres, ferias y múltiples actividades culturales. Esta flexibilidad lo convierte en un escenario privilegiado para explorar las múltiples voces y prácticas que conforman la identidad del Distrito Metropolitano de Quito.', 
            'QFJX+277, Jose Maria Aguirre, Quito 170136, Ecuador', 'Diurno', 'Gratuito', 'Evento Sociales, Exposición',  
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/06/PHV-Quito-Turismo-SENSACIONES-2018-ITCHIMBIAF442.jpg', 4),
            
            
            (21, 'Iglesia de Santo Domingo', 
            'El templo exhibe trabajos en cedro dorado y numerosas pinturas y tallas,hogar de la Virgen del Rosario. El templo está cubierto por trabajos en cedro cubierto con pan de oro, y por numerosas pinturas y tallas que adornan su interior. Junto al retablo mayor, las diez capillas laterales que completan el conjunto interior de Santo Domingo enriquecen aún más el cuerpo interno de la iglesia con bellísimos trabajos en madera y hojilla de oro.', 
            'C. Guayaquil S1-76, Quito 170130, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/iglesia-cultura-iglesia-de-santo-domingo.webp', 2),

            (22, 'Catedral Metropolitana de Quito', 
            'La Catedral de Quito, sede de la Arquidiócesis Primada, destaca por su arquitectura histórica y su importancia religiosa en Ecuador. Alberga una amplia exposición de arte, pintura y escultura de la época colonial y republicana. El ingreso es permitido a los patios interiores, nave principal y una de las terrazas. Es uno de los templos más antiguos de Sudamérica y orgullo del Centro Histórico de Quito.', 
            'QFHQ+R2C, Eugenio Espejo, Quito 170401, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-La-Catedral.webp', 2),

            (23, 'Iglesia de San Agustín', 
            'El atrio, tallado en piedra del Pichincha, tiene 4 gradas únicas y la fachada refleja el Manierismo con órdenes clásicos en distintas partes. Tanto las salas del museo del convento como las paredes del claustro muestran una serie de obras de arte pictórico y escultórico, incluida una serie de pinturas sobre la vida de San Agustín encargada a Miguel de Santiago por el padre Basilio de Rivera.', 
            'Chile 9-24 y, Quito 170401, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-de-San-Agustin.webp', 2),
            
            (24, 'Iglesia de Santa Teresita', 
            'La iglesia de Santa Teresita destaca en La Mariscal por su estilo neogótico y su altura, siendo uno de los edificios más importantes de este tipo en Quito. La construcción empezó en 1938 y estuvo a cargo del hermano Mariano de San José Riocerezo. Fue inaugurada el 19 de marzo de 1956.', 
            'Av. 09 de Octubre 470 entre Roca y Robles, 9 de Octubre 470, Quito 170143, Ecuador', '9:30 - 17:30', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-Santa-Teresita.webp', 2),

            (25, 'Iglesia de Gúapulo', 
            'El Santuario Nuestra Señora de Guápulo, del siglo XVII, alberga el magnífico púlpito de Juan B. Menacho y obras de Miguel de Santiago. La imagen de la Virgen de Guápulo fue el modelo utilizado para la escultura de Nuestra Señora de El Cisne y también la de El Quinche.', 
            'QGXF+WP3, Quito 170136, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-De-Guapulo.webp', 2),
            
            (26, 'Iglesia de Santa Clara', 
            'El Convento presenta un interesante diseño arquitectónico con domo elíptico, cúpula ochavada, torre con campanario y retablos destacados en la iglesia.', 
            'Fray Antonio de Marchena 60, Quito 170129, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-de-Santa-Clara-de-San-Millan.webp', 2),

            (27, 'Iglesia El Sagrario', 
            'Vinculada a la Catedral Primada de Quito, la Capilla del Sagrario fue construida entre los siglos XVII y XVIII, finalizando en 1715 bajo la dirección del arquitecto José Jaime Ortiz. Llama la atención la hermosa cúpula de color verde, considerada como una de las 2 más grandes de Quito y que se la puede apreciar desde la Plaza Grande.', 
            'QFHP+RR4, García Moreno, Quito 170401, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-El-Sagrario.webp', 2),

            (28, 'Iglesia El Belén', 
            'La Iglesia de El Belén, parte importante de la historia de Quito, es conocida como la más antigua, aunque su construcción data de 1694. Es una iglesia sencilla de una sola nave. La fachada presenta dos pequeñas torres conectadas por un balcón y rematadas en pirámides de tejuelo vitrificado. Su tejado a dos aguas determina la fisonomía general de la fachada con su característica forma de «V» invertida.', 
            'QFPX+PG2, Luis Sodiro, Quito 170136, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-El-Belen.webp', 2),

            (29, 'Iglesia Compañía de Jesús', 
            'El templo de la Compañía de Jesús de Quito, cumbre del barroco latinoamericano, fue construido entre 1605 y 1765 por jesuitas y artistas de la Escuela Quiteña. La iglesia, y su rica ornamentación interna, totalmente cubierta con láminas de oro, es una de las mayores atracciones turísticas de la ciudad y un patrimonio invaluable, tanto artístico como económico, para el país.', 
            'García Moreno N10-43, Quito 170401, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-De-La-Compania.webp', 2),

            (30, 'Convento de San Francisco', 
            'El complejo de San Francisco, del siglo XVI, incluye un templo, capillas y un convento en el Centro Histórico de Quito, con claustros mudéjares y una vasta colección de arte, destacando la Escuela Quiteña y una biblioteca franciscana.', 
            'Cuenca 477 y, Quito 170401, Ecuador', 'Diurno', 'Pagado', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Convento-San-Francisco.webp', 2),

            (31, 'Iglesia del El Carmen Bajo de la Santísima Trinidad', 
            'Tras el terremoto de 1698, la iglesia del Carmen Bajo se trasladó de Latacunga a Quito. Finalizada en 1745, destacan sus claustros y fachada.', 
            'Venezuela 1168, Quito 170401, Ecuador', 'Diurno', 'Pagado', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Convento-Del-Carmen-Bajo.webp', 2),
            
            
            (32, 'Plaza de Santa Clara', 
            'Antiguamente en esta plaza funcionaba el mercado de Santa Clara que posteriormente fue trasladado a un espacio más adecuado, la plaza fue reconstruida con el objetivo de brindar a la ciudad un espacio libre y que permita admirar la Iglesia y Convento de Santa Clara', 
            'QFHM+5JF, Rocafuerte, Quito 170130, Ecuador', 'Diurno', 'Gratuito', 'Exposición, Patrimonio y Monumentos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Plaza-Santa-Clara.webp', 1),

            (33, 'Plaza de Santo Domingo', 
            'Dentro de la plaza de Santo Domingo se puede encontrar el monumento al Mariscal Antonio José de Sucre y la Iglesia y convento de Santo Domingo. Se puede pasear por toda la plaza y tomar fotos de los monumentos y edificios coloniales que están a su alrededor. En algunas fechas especiales del año como fiestas de Quito o Navidad, se suelen hacer conciertos y ferias en la plaza.', 
            'C. Guayaquil S1-76, Quito 170401, Ecuador', 'Diurno', 'Gratuito', 'Exposición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/plaza_santo_domingo.jpg', 1),


            (34, 'Jardín Botánico de Quito', 
            'Inaugurado en 2005, este centro es vital para la conservación y educación sobre la flora andina ecuatoriana, destacando sus orquidearios y jardines temáticos. Tiene una combinación perfecta de naturaleza y belleza, es el lugar ideal para cualquier ocasión especial. Desde la vegetación exuberante del trópico hasta los tranquilos paisajes del páramo, cada rincón de nuestro jardín ofrece un ambiente único para tu evento.', 
            'INTERIOR PARQUE, PASAJE # 34, Rumipamba E6-264 Y, Quito 170135, Ecuador', '10h00 - 15h30', 'Pagado', 'Evento Sociales, Visitas Autoguiadas, Alquiler de Espacios, Evento Sociales, Visitas Autoguiadas, Alquiler de Espacios, Exposicion', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Jardin-botanico.webp', 3),
            
            (35, 'Jardín Alado', 
            'El Centro de Rescate se enfoca en la rehabilitación y liberación de animales silvestres, especialmente aves rapaces, promoviendo el respeto y amor por la naturaleza. Se encargan de Acogida, Rehabilitación, Evaluación y Liberación.', 
            'Ilaló, Quito 170801, Ecuador', '9:30 - 17:00', 'Pagado', 'Exposición, Patrimonio y Monumentos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Santuario-Jardin-Alado.webp', 3),

            (36, 'Pomasquinde', 
            'Es un santuario donde habitan alrededor de 29 especies de aves, ubicado en Pomasqui. Posee una maravillosa área que se puede recorrer junto con los guías para poder avistar flora y fauna propia del lugar. Ten una caminata inmersiva por senderos que te llevarán a través de un abanico de plantas exóticas y autóctonas. Descubre la diversidad botánica que coexiste con la abundante vida aviar.', 
            'Simón Bolivar 516-394, Quito 170120, Ecuador', 'Diurno', 'Pagado', 'Observación de Aves, Exposición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/pomasquinde1.jpg', 3),

            (37, 'Bellavista Cloud Forest', 
            'Explora el Bosque Nublado de Bellavista y sus maravillas a través de paquetes únicos e inmersivos. Desde el vibrante Paraíso de los Colibríes hasta una inmersión completa en el bosque nublado, los visitantes obtienen transporte desde Quito, deliciosas comidas y guías expertos. El alojamiento varía desde una noche hasta tres días, con oportunidades de observación de aves que incluyen el icónico gallo de las rocas andino. Cada paquete promete una experiencia inolvidable en un paraíso natural.', 
            'Km 62 Ruta Antigua Nono, Tandayapa 170104, Ecuador', 'Diurno', 'Pagado', 'Aventura', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Bellavista-Cloud-Forest.webp', 3),
            
            
            
            (38, 'Teatro Bolívar', 
            'El Teatro Bolívar, restaurado en un 85%, está en pleno funcionamiento. Actualmente se encuentra totalmente operativo y en pleno proceso de reactivación. Vive experiencias artísticas y sensoriales al centro cultural más bello del país.', 
            'Eugenio Espejo &, Quito 170401, Ecuador', 'Nocturno', 'Gratuito', 'Evento en Vivo', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/06/teatro_bolivar.jpg', 1),
            
            (39, 'Teatro Sucre', 
            'Inaugurado en 1886, el Teatro Nacional Sucre, de arquitectura neoclásica, es un puente entre el público y las artes escénicas, con 136 años de historia y capacidad para 665 personas. El escenario del Sucre ha sido testigo no solo de las más grandes obras teatrales y orquestales que han pasado por el país, sino que también sirvió como sede de sucesos de la vida política nacional, como la posesión presidencial de algunos mandatarios, de los alcaldes de la capital, y de otros eventos de trascendencia.', 
            'C. Guayaquil &, Quito 170401, Ecuador', 'Diurno', 'Pagado', 'Evento en Vivo', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Teatro-Nacional-Sucre.webp', 1),
            
            (40, 'Basílica del Voto Nacional', 
            'La Basílica del Voto Nacional es considerada como una de las máximas construcciones de estilo neogótico de Latinoamérica debido a su gran dimensión. El arquitecto francés Emilio Tarlier fue contratado para tan magnífica obra arquitectónica. Un elemento caracterizador del estilo gótico son sus gárgolas, que adornan la fachada de este estilo, pero al concebirlo en el Ecuador, se adoptaron animales representativos de la fauna ecuatoriana.', 
            'Venezuela 11-263 y, Quito 170130, Ecuador', 'Diurno', 'Gratuito', 'Evento en Vivo, Iglesias y Conventos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Iglesia-La-Basilica.webp', 1),
            
            (41, 'Centro de Arte Contemporáneo de Quito', 
            'Fomenta la reflexión y participación en el arte con exposiciones, talleres y charlas educativas, promoviendo la diversidad cultural en la ciudad. Promueve, fomenta y difunde prácticas artísticas contemporáneas a través de procesos de investigación y diálogo, con el objetivo de consolidar la oferta, la demanda y la participación de la ciudadanía, los actores culturales y sociales.', 
            'Montevideo, y, Quito 170103, Ecuador', 'Diurno', 'Pagado', 'Exhibición, Exposición, Centros Culturales', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/06/PHV-QU1.jpg', 4),
            
            (42, 'Mercado Iñaquito (Mercado La Carolina)', 
            'Mercado Iñaquito es un mercado vibrante ubicado en el corazón de Quito, Ecuador. Es conocido por su diversa selección de productos frescos, artesanías locales y una variedad de ofertas culinarias que reflejan la cultura ecuatoriana. Los visitantes pueden explorar numerosos puestos que venden frutas, verduras, carnes y platos tradicionales de colores, lo que lo convierte en un lugar excelente tanto para lugareños como para turistas. El mercado ofrece una auténtica experiencia del día a día en Quito, mostrando sabores regionales y productos artesanales, todo dentro de un ambiente animado.', 
            'Iñaquito, Quito 170135, Ecuador', 'Diurno', 'Pagado', 'Exposición, Patrimonio y Monumentos', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Mercado-de-Inaquito.webp', 4),
            
            (43, 'Palacio de la Circasiana', 
            'El Palacio de La Circasiana, antigua mansión en Quito, pertenece a la familia Jijón-Caamaño, destacada en la historia aristocrática de Ecuador. El palacio, sus jardines y edificaciones complementarias son actualmente de propiedad pública.', 
            'Av. Cristóbal Colón, Quito 170129, Ecuador', 'Diurno', 'Gratuito', 'Biblioteca, Exhibición, Visitas, Exposición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Palacio-la-Circasiana.webp', 4),
            
            (44, 'Mercado Artesanal', 
            'El Mercado, fundado en 2000, reubicó vendedores ambulantes de La Mariscal, ofreciendo una amplia gama de artesanías a precios asequibles. Cuenta con varios pasillos, se consigue artesanías de las cuatro regiones del país, además de ropa, chocolates, zapatos, etc.', 
            'Jorge Washington 611, Quito 170143, Ecuador', 'Diurno', 'Gratuito', 'Visitas, Venta de Productos, Exposición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Mercado-Artesanal.webp', 4),
            
            (45, 'Complejo Arqueológico Tulipe', 
            'El Museo de Sitio y Centro Ceremonial Tulipe es un santuario subtropical en donde los Yumbos, antiguos pobladores de la zona, plasmaron sus conocimientos de arquitectura y geometría para honrar a sus dioses (Luna, Tierra, Agua). El complejo comprende tres hectáreas de terreno y se divide en dos partes: El museo, que es un espacio de difusión; Y, en el exterior las piscinas o centro ceremonial, que son las evidencias arqueológicas.', 
            '37P7+W75, Quito, Ecuador', 'Diurno', 'Pagado', 'Visita Guiada, Recorridos Culturales y de Ancestralidad, Exposición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-Complejo-Arquitectonico-Tulipe.webp', 4),
            
            (46, 'Capilla del Hombre y Casa Museo Guayasamín', 
            'Desde 2002, este complejo cultural honra la obra y el pensamiento humanista de Oswaldo Guayasamín, generando patrimonio cultural para Ecuador. Representó escenas de dolor, amor, violencia y esperanza; organizó su trabajo en eras.', 
            'Mariano Calvache E18-94 y, Quito 170135, Ecuador', 'Diurno', 'Pagado', 'Visitas Autoguiadas, Biblioteca, Restaurante, Exposición', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/09/Cultura-La-Capilla-del-Hombre.webp', 4),


            (47, 'Quito Jazz Club', 
            'El Quito Jazz Club es un espacio icónico en el corazón de Quito, dedicado a la magia del jazz en vivo. Ubicado en una casa histórica, ofrece conciertos íntimos con músicos nacionales e internacionales, creando una atmósfera única con excelentes acústicas, cócteles artesanales y cocina gourmet.', 
            'Leonidas Plaza Gutierrez 19-50 entre, 18 de Septiembre, Quito 170143, Ecuador', 'Nocturno', 'Pagado', 'Cultura', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/08/quito_jazz_club.jpg', 5),
            
            (48, 'Heladería San Agustín', 
            'Demostraciones gratuitas de elaboración artesanal de helado de paila, previa reserva. Servicio de entrega a través de la plataforma de motos Fénix a 0,50 $/km. Los invitados de cumpleaños reciben helado ""humeante"" (válido 10 días antes o después del cumpleaños). ""Tiempo de Historia con la Abuela Encarnación"", de viernes a domingo, de 13:00 a 14:30 h, con leyendas de Quito, a cargo de la segunda propietaria de la Heladería San Agustín.', 
            'C. Guayaquil N5-59 y, Quito 170130, Ecuador', 'Diurno', 'Pagado', 'Guiada, Grupal, Familiar, Contemplativa',  
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/06/heladeria_san_agustin1.jpg', 5),
            
            (49, 'República del Cacao', 
            'La Boutique del Chocolate ofrece todas las variedades de tabletas de chocolate y sets de regalo de República del Cacao. El ""Rincón del Emprendedor"" apoya a los emprendedores que crean productos de chocolate o inspirados en el cacao. El Taller del Cacao, dirigido por el chef Santiago Cueva, presenta bombones únicos y repostería de alta', 
            'Venezuela 976, Quito 170401, Ecuador', 'Diurno', 'Pagado', 'Guiada, Grupal, Familiar, Contemplativa', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/06/Republica-del-Cacao.jpg', 5),
            
            (50, 'Salsoteca Lavoe', 
            'Salsoteca Lavoe Un vibrante punto de encuentro tanto para los amantes de la salsa como para los recién llegados, locales y visitantes disfrutan de emocionantes talleres de baile de salsa y bachata para todos los niveles de habilidad. Experimenta el animado ambiente con música en directo que dinamiza las noches de Quito, asegurando momentos inolvidables para todos.', 
            'Quito 170135, Ecuador', 'Nocturno', 'Pagado', 'Vida nocturna', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/06/discotecaLavoe2.jpg', 5),
            
            (51, 
            'Cacao & Cacao', 
            'Una tienda especializada enfocada en productos 100% ecuatorianos, que destaca principalmente el cacao de fino aroma y el café de especialidad del país. Es un negocio familiar que busca promover y resaltar la calidad y el sabor de los productos locales, trabajando directamente con productores ecuatorianos.', 
            'Juan León Mera N21-241 y, Quito 170143, Ecuador', 
            'Diurno', 
            'Pagado', 
            'Gastronomía y Huecas', 
            'https://visitquito-cdn.b-cdn.net/wp-content/uploads/2025/08/cacao_cacao.jpg',
            5)
            
            ");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {

        }
    }
}
