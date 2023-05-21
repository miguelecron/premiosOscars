El proyecto de premiosOscars lee un archivo csv que contiene datos de actrices premiadas con uno o varios oscar y la
película por la que fueron premiadas. 
Una vez leído, almacena la información en ActricesPremidas y posee un método generarHtmlActrices que procesa los
datos del CSV y genera un fichero .html con el nombre de las actrices y las películas por las que han gando uno o más oscars. 
Además ordena a las actrices por el número de oscars ganádos y las películas por el año, siendo la más reciente la 
primera y la más antigua la última. 

Se ha añadido al proyecto el método generarHtmlYayasPremiadas, que genera otro html con las actrices que han ganado uno o más Oscar con más de 65 años y la edad y la pelicula o películas por la que han sido premiadas.

También se ha añadido un método generar Json que genera un archivo .json con las actrices ordenadas por oscars.


El proyecto cuenta también con dos clases de test que prueban los métodos clave del proyecto.
