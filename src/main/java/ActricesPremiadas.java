import com.google.gson.Gson;

import java.io.*;
import java.util.*;


public class ActricesPremiadas {

    private Set<Actriz> actricesPremiadas;

    public ActricesPremiadas() {
        this.actricesPremiadas = new HashSet<>();
    }

    public Set<Actriz> getActricesPremiadas() {
        return actricesPremiadas;
    }

    public void setActricesPremiadas(Set<Actriz> actricesPremiadas) {
        this.actricesPremiadas = actricesPremiadas;
    }


    /**
     * Recibe una actriz y una película y los aniade a la colecion de actricesPremiadas en caso de no existir aun
     * Si ya existe en la coleccion, aniade la pelicula a la coleccion de peliculas de la actriz
     * @param actriz recibe una actriz
     * @param pelicula  recibe una pelicula
     * @return devuelve true si se han aniadido
     */
    public boolean addActriz(Actriz actriz, Pelicula pelicula) {

        if (!(actricesPremiadas.add(actriz) && actriz.aniadirPelicula(pelicula))) {
            if (buscarActriz(actriz) != null){
                return buscarActriz(actriz).aniadirPelicula(pelicula);
            }
        }
        return true;
    }


    /**
     * Busca a una actriz y la retorna
     * @param actrizBuscada objeto Actriz
     * @return actriz
     */
    private Actriz buscarActriz(Actriz actrizBuscada) {
        for (Actriz actriz : actricesPremiadas) {
            if (actrizBuscada.equals(actriz)) {
                return actriz;
            }
        }
        return null;
    }


    /**
     * Ordena a las actrices por el nupero de premios obtenidos
     * @return la lista de actrices ordenada por premios obtenidos
     */
    public List<Actriz> actricesOrdenadasPorPremios() {
        List<Actriz> actricesOrdenadas = new ArrayList<>(actricesPremiadas);
        Collections.sort(actricesOrdenadas, new CompararActricesPorPremios());
        return actricesOrdenadas;
    }

    // -------------------------------------- Generadores de html---------------------------------

    // -------------------------------------- Head de html---------------------------------


    /**
     * Genera la cabecera de una página de html
     * @param tituloPagina titulo de la pagina html
     * @param estilos hoja de estilos personalizada
     * @return página con el titulo y su hoja de estilos
     */
    private String headHtml(String tituloPagina, String estilos) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"es-Es\">");
        sb.append("<head>");
        sb.append("<meta charset=\"UTF-8\">");
        sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        sb.append(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        sb.append("<title>").append(tituloPagina).append("</title>");
        sb.append(estilos);
        sb.append("</head>");

        return sb.toString();
    }

    // -------------------------------------- Estilos y cabecera para tabla html---------------------------------

    /**
     * Estilos para una tabla de html
     * @return retorna los estilos css para la tabla html
     */
    private String estilosTabla() {
        StringBuilder sb = new StringBuilder();

        sb.append("<style>");
        sb.append("table {");
        sb.append("width: 90%;");
        sb.append("border-collapse: collapse;");
        sb.append("}");
        sb.append("table, th,td {");
        sb.append("border: 1px solid black;");
        sb.append("}");
        sb.append("th {background-color: gold;}");
        sb.append(" </style>");

        return sb.toString();
    }


    /**
     * Cabecera de la tabla de html
     * @return retorna la cabecera de la tabla de actrices
     */
    private String cabeceraTablaHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<thead>");
        sb.append(" <tr>");
        sb.append(" <th>Nombre de la actiz</th>");
        sb.append(" <th>Oscars ganados</th>");
        sb.append(" <th>Películas premiadas</th>");
        sb.append(" </tr>");
        sb.append("</thead>");

        return sb.toString();
    }


    // -------------------------------------- Tabla de html con actrices ---------------------------------


    /**
     * Genera un fichero html en la carpeta de resources con una lista de actrices y los premios obtenidos junto
     * con sus peliculas
     * @param nombreFichero nombre del fichero html
     * @param tituloPagina titulo de la pagina html
     * @param actricesP listado de las actrices ordenado
     */
    public void generarTablaHtml(String nombreFichero, String tituloPagina, List<Actriz> actricesP) {

        try (PrintWriter impresion = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/" + nombreFichero + ".html")))) {
            impresion.println(headHtml(tituloPagina, estilosTabla()));
            impresion.println("<body>");
            impresion.println("<table>");
            impresion.println(cabeceraTablaHtml());
            impresion.println("<tbody>");

            for (int i = 0; i < actricesOrdenadasPorPremios().size(); i++) {
                int oscars = actricesP.get(i).getPeliculas().size();
                impresion.println("<tr>");
                impresion.print("<td>");
                impresion.print(actricesP.get(i).getNombre());
                impresion.println("</td>");

                impresion.print("<td>");
                impresion.print(oscars);
                impresion.print(" Oscars");
                impresion.println("</td>");

                impresion.print("<td colspan=\"" + oscars + "\">");
                for (int j = 0; j < oscars; j++) {
                    impresion.print("\t- ");
                    impresion.print(actricesP.get(i).getPeliculasOrdenadasPorAnio().get(j).getTitulo());
                    impresion.print(", ");
                    impresion.print(actricesP.get(i).getPeliculasOrdenadasPorAnio().get(j).getAnioGanador());
                    if (j < oscars - 1) {
                        impresion.print("<br>");
                    }
                }
                impresion.println("</td>");
                impresion.println("</tr>");
            }
            impresion.println("</tbody");
            impresion.println("</table>");
            impresion.println("</body>");
            impresion.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Generado html de actrices premiadas");
        }
    }

    // -------------------------------------- Tarea 16 Supuesto 1 ---------------------------

    // -------------------------------------- Buscador actrices mayores de X anios premiadas---------------------------


    /**
     * Busca a las actrices premiadas con más de la edad pasada por parametro y devuelve un mapa de la actriz como clave
     * y las peliculas como valor
     * @param edad edad de la actriz
     * @return devuelve el mapa con actriz y peliculas
     */
    public Map<Actriz, ArrayList<Pelicula>> premiadasConXEdad(int edad) {
        Map<Actriz, ArrayList<Pelicula>> premiadasConXEdad = new HashMap<>();

        for (Actriz actriz : actricesPremiadas) {

            for (Pelicula pelicula : actriz.getPeliculas()) {

                if (pelicula.getEdadActriz() > edad) {

                    if (premiadasConXEdad.containsKey(actriz)) {
                        premiadasConXEdad.get(actriz).add(pelicula);
                    } else {
                        ArrayList<Pelicula> peliculasYayas = new ArrayList<>();
                        peliculasYayas.add(pelicula);
                        premiadasConXEdad.put(actriz, peliculasYayas);
                    }

                }
            }
        }
        return premiadasConXEdad;
    }



    // -------------------------------------- Html con actrices mayores de 65 anios premiadas---------------------------


    /**
     * Genera un fichero html con las actrices premiadas con más ead que la pasada por parametro
     * @param nombreFichero nombre del fichero html
     * @param tituloPagina titulo de la pagina html
     * @param actrices coleccion de actrices premiadas con mas edad que la pasada por parametro
     */
    public void generarHtmlPremiadasConXEdad(String nombreFichero, String tituloPagina, Map<Actriz, ArrayList<Pelicula>> actrices){

        try (PrintWriter impresion = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/" + nombreFichero + ".html")))) {

            impresion.println(headHtml(tituloPagina, "<style></style>"));
            impresion.println("<body>");
            impresion.println("<ol>");

            for (Actriz actriz : actrices.keySet()) {
                impresion.println("<li>");
                impresion.print("-");
                impresion.println(actriz.getNombre());
                impresion.println("<ul>");
                for (Pelicula pelicula : actrices.get(actriz)) {
                    impresion.print("<li>");
                    impresion.print(pelicula.getEdadActriz());
                    impresion.print(" años, ");
                    impresion.print(pelicula.getTitulo());
                    impresion.println("</li>");
                    impresion.println("<br>");
                }
                impresion.println("</ul>");
                impresion.println("</li>");
            }
            impresion.println("</ol>");
            impresion.println("</body>");
            impresion.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Generado html de actrices premiadas con más de x edad");
        }
    }

    // -------------------------------------- Aniadido personal ---------------------------
    // -------------------------------------- Generador Json ---------------------------


    /**
     * Genera un fichero json con las actrices ordenadas por premios obtenidos
     */
    public void generarJson(){
        Gson gson = new Gson();

        try {
            gson.toJson(actricesOrdenadasPorPremios(), new FileWriter("src/main/resources/actricesJson"));
            System.out.println("Generado Json de actrices ordenadas por premios");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ------------------------------------------- Tarea 16 supuesto 2 ---------------------------------------------

    // -------------------------------------- Buscador peliculas por cadena de texto -----------------------------------

    /**
     * Busca las películas que contienen en el titulo el string pasado como parametro
     * @param titulo charSecuence del titulo
     * @return un ArrayList de las peliculas que contienen en el título el charSecuence pasaado por parametro,
     * las devuelve en formato String "- titulo pelicula, anio ganador. Nombre actriz (edad)"
     */
    private List<String> buscadorPeliculas(String titulo){
        List<String> peliculasMatch = new ArrayList<>();

        for (Actriz actriz: actricesPremiadas) {
            for (Pelicula pelicula: actriz.getPeliculas()){
                if (pelicula.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                    peliculasMatch.add("- " + pelicula.getTitulo() +
                            ", " + pelicula.getAnioGanador() +
                            ". " + actriz.getNombre() +
                            "(" + pelicula.getEdadActriz() + ")");
                }
            }
        }
        return peliculasMatch;
    }

    /**
     * Imprime la lista de peliculas que coinciden con el parametro pasado
     * @param titulo titulo completo o parte del titulo de una pelicula
     */
    public void buscarPeliculasPorTitulo(String titulo){
        List<String> peliculas = buscadorPeliculas(titulo);

        System.out.println("Películas que empiezan por " +  titulo);

        for (String str:peliculas) {
            System.out.println(str);
        }
    }


    // -------------------------------------- Tarea 16 supuesto 3 ------------------------------------------------

    // -------------------------------------- Buscador supuesto troll -----------------------------------

    /**
     * Busca las peliculas y las actrices que ganaron con la edad pasada por parmetro y los devuelve en un map
     * @param edad edad que tenia la actriz al ganar el oscar
     * @return mapa con Pelicula, Actriz que coinciden con la edad de la actriz pasada por parametro
     */
    private Map<Pelicula, Actriz> buscardorSupuestoTroll(int edad){
        Map<Pelicula, Actriz> listaPeliculaActriz = new HashMap<>();

        for (Actriz actriz: actricesPremiadas) {
            for (Pelicula pelicula:actriz.getPeliculas()) {
                if (pelicula.getEdadActriz() == edad){
                    listaPeliculaActriz.put(pelicula, actriz);
                }
            }
        }
        return listaPeliculaActriz;
    }


    /**
     * Genera un fichero .csv con el formato "nombre actriz; titulo pelicula"
     * @param edad edad de la actriz ganadora
     * @param nombreFichero nombre del fichero csv a generar
     */
    public void generarCsvSupuestoTroll(int edad, String nombreFichero){
        Map<Pelicula, Actriz> peliculaActriz = buscardorSupuestoTroll(edad);
        List<Pelicula> peliculasOrdenadas = new ArrayList<>(peliculaActriz.keySet());

        Collections.sort(peliculasOrdenadas);

        try (PrintWriter impresion = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/" + nombreFichero + ".csv")))) {

            impresion.print("pelicula;");
            impresion.println("actriz");
            for (Pelicula pelicula : peliculasOrdenadas) {
                impresion.print(peliculaActriz.get(pelicula).getNombre());
                impresion.print(";");
                impresion.println(pelicula.getTitulo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Generado csv de actrices premiadas con " + edad + " años de edad y su película");
        }


    }

}
