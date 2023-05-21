import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public boolean addActriz(Actriz actriz, Pelicula pelicula) {

        if (!(actricesPremiadas.add(actriz) && actriz.aniadirPelicula(pelicula))) {
            return buscarActriz(actriz).aniadirPelicula(pelicula);
        }
        return true;
    }

    private Actriz buscarActriz(Actriz actrizBuscada) {
        for (Actriz actriz : actricesPremiadas) {
            if (actrizBuscada.equals(actriz)) {
                return actriz;
            }
        }
        return null;
    }

    public List<Actriz> actricesOrdenadasPorPremios() {
        List<Actriz> actricesOrdenadas = new ArrayList<>(actricesPremiadas);
        Collections.sort(actricesOrdenadas, new CompararActricesPorPremios());
        return actricesOrdenadas;
    }

    // -------------------------------------- Generadores de html---------------------------------

    // -------------------------------------- Head de html---------------------------------

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
        sb.append("");

        return sb.toString();
    }

    // -------------------------------------- Estilos y cabecera para tabla html---------------------------------

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

    public void generarTablaHtml(String nombreFichero, String tituloPagina, List<Actriz> actricesP) {
        PrintWriter impresion = null;

        try {
            impresion = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/" + nombreFichero + ".html")));
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
            if (impresion != null) {
                impresion.close();
            }
            System.out.println("Generado html de actrices premiadas");
        }
    }


    // -------------------------------------- Buscador actrices mayores de 65 anios premiadas---------------------------


    public Map<Actriz, ArrayList<Pelicula>> yayasPremiadas() {
        Map<Actriz, ArrayList<Pelicula>> yayasPremiadas = new HashMap<>();

        for (Actriz actriz : actricesPremiadas) {

            for (Pelicula pelicula : actriz.getPeliculas()) {

                if (pelicula.getEdadActriz() > 65) {

                    if (yayasPremiadas.containsKey(actriz)) {
                        yayasPremiadas.get(actriz).add(pelicula);
                    } else {
                        ArrayList<Pelicula> peliculasYayas = new ArrayList<>();
                        peliculasYayas.add(pelicula);
                        yayasPremiadas.put(actriz, peliculasYayas);
                    }

                }
            }
        }
        return yayasPremiadas;
    }

    // -------------------------------------- Html con actrices mayores de 65 anios premiadas---------------------------


    public void generarHtmlYayasPremiadas(String nombreFichero, String tituloPagina, Map<Actriz, ArrayList<Pelicula>> yayasPremiadas){
        PrintWriter impresion = null;

        try {
            impresion = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/" + nombreFichero + ".html")));

            impresion.println(headHtml(tituloPagina, "<style></style>"));
            impresion.println("<body>");
                impresion.println("<ol>");

                    for (Actriz actriz: yayasPremiadas.keySet()) {
                         impresion.println("<li>");
                               impresion.print("-");
                               impresion.println(actriz.getNombre());
                                    impresion.println("<ul>");
                                         for (Pelicula pelicula: yayasPremiadas.get(actriz)) {
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

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (impresion != null){
                impresion.close();
            }
            System.out.println("Generado html de yayas premiadas");
        }
    }


    // -------------------------------------- Generador Json ---------------------------


    public void generarJson(){
        Gson gson = new Gson();

        try {
            gson.toJson(actricesOrdenadasPorPremios(), new FileWriter("src/main/resources/actricesJson"));
            System.out.println("Generado Json de actrices ordenadas por premios");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // -------------------------------------- Buscador peliculas por cadena de texto -----------------------------------

    private List<String> buscadorPeliculas(String titulo){
        List<String> peliculasMatch = new ArrayList<>();

        for (Actriz actriz: actricesPremiadas) {
            for (Pelicula pelicula: actriz.getPeliculas()){
                if (pelicula.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                    peliculasMatch.add(new String(
                            "- " + pelicula.getTitulo() +
                                    ", " + pelicula.getAnioGanador() +
                                    ". " + actriz.getNombre() +
                                    "(" + pelicula.getEdadActriz() + ")"
                    ));
                }
            }
        }
        return peliculasMatch;
    }

    public void buscarPeliculasPorTitulo(String titulo){
        List<String> peliculas = buscadorPeliculas(titulo);

        System.out.println("Películas que empiezan por " +  titulo);

        for (String str:peliculas) {
            System.out.println(str);
        }
    }


}
