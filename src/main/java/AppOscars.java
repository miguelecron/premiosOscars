import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AppOscars {

    public static final String DELIMITADOR = ",";

    public static void main(String[] args) {
        ActricesPremiadas listaActrices = new ActricesPremiadas();
        leerFichero(listaActrices);
        listaActrices.generarHtmlActrices("actricesPremiadas", "Actrices premiadas con Oscars", listaActrices.actricesOrdenadasPorPremios());
    }

    public static void leerFichero(ActricesPremiadas ac) {

        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader("src/main/resources/csvTarea15.txt"));

            String linea = entrada.readLine();
            linea = entrada.readLine();

            while (linea != null) {
                String[] datosFichero = linea.split(DELIMITADOR);

                //Creamos una nueva actriz
                Actriz actriz = new Actriz(
                        Integer.parseInt(datosFichero[2].trim()),  // edad actriz
                        datosFichero[3].trim());                           // nombre actriz

                //Creamos una nueva película
                Pelicula pelicula = new Pelicula(
                        Integer.parseInt(datosFichero[1].trim()),  // anio pelicula
                        datosFichero[4].trim());                           // nombre pelicula

                //pasamos la actiz a la clase contenedora
                ac.addActriz(actriz, pelicula);
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
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
