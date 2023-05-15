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

       if (!(actricesPremiadas.add(actriz) && actriz.aniadirPelicula(pelicula))){
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

    public List<Actriz> actricesOrdenadasPorPremios(){
        List<Actriz> actricesOrdenadas = new ArrayList<>(actricesPremiadas);
        Collections.sort(actricesOrdenadas, new CompararActricesPorPremios());  //Por qué CompararPorPremios() con parentesis?
        return actricesOrdenadas;
    }

    public void generarHtmlActrices(String nombreFichero, String tituloPagina, List<Actriz> actricesP) {
        PrintWriter impresion = null;

        try {
            impresion = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/" + nombreFichero + ".html")));
            impresion.println(cabeceraHtml(tituloPagina, estilos()));
            impresion.println("<body>");
            impresion.println("<table>");
            impresion.println(cabeceraTabla());
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
                    impresion.print(actricesP.get(i).getPeliculasOrdenadasPorAnio().get(j).getNombre());
                    impresion.print(", ");
                    impresion.print(actricesP.get(i).getPeliculasOrdenadasPorAnio().get(j).getAnio());
                    if (j < oscars -1){
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
        }
    }

    private String cabeceraHtml(String tituloPagina, String estilos){
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

    private String estilos(){
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

    private String cabeceraTabla(){
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
}
