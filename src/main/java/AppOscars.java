import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AppOscars {

    public static void main(String[] args) {

        ActricesPremiadas ap = leerFichero();

        ap.generarTablaHtml("actricesPremiadas", "Actrices premiadas con Oscars", ap.actricesOrdenadasPorPremios());
        ap.generarHtmlPremiadasConXEdad("yayasPremiadas", "Yayas premiadas", ap.premiadasConXEdad(65));
        // ap.generarJson();
        ap.buscarPeliculasPorTitulo("good");
        ap.generarCsvSupuestoTroll(33, "csvTroll");
    }

    public static ActricesPremiadas leerFichero() {

        ActricesPremiadas listaActrices = new ActricesPremiadas();

        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader("src/main/resources/csvTarea15.txt"));

            String linea = entrada.readLine();
            linea = entrada.readLine();

            while (linea != null) {
                String[] datosFichero = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                //Creamos una nueva actriz
                Actriz actriz = new Actriz(datosFichero[3].trim().replaceAll("\"", "")); // nombre actriz

                //Creamos una nueva película
                Pelicula pelicula = new Pelicula(
                        Integer.parseInt(datosFichero[1].trim()),  // anio pelicula ganadora
                        datosFichero[4].trim().replaceAll("\"", ""),                                 // nombre pelicula
                        Integer.parseInt(datosFichero[2].trim()));           // edad actriz en el momento del premio);

                //pasamos la actiz a la clase contenedora
                listaActrices.addActriz(actriz, pelicula);
                linea = entrada.readLine();
            }


        } catch (FileNotFoundException e) {
            System.out.println("Error al leer " + "csvTarea15.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (NullPointerException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaActrices;
    }
}
