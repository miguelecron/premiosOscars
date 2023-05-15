import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActricesPremiadasTest {
    ActricesPremiadas ap = new ActricesPremiadas();
    Actriz a1 = new Actriz(25, "Amaia Salamanca");
    Actriz a2 = new Actriz(26, "Clara Lago");
    Actriz a21 = new Actriz(27, "Clara Lago");
    Actriz a3 = new Actriz(30, "Blanca Suárez");
    Pelicula p1 = new Pelicula(1990, "Pelicula1");
    Pelicula p11 = new Pelicula(1991, "Pelicula1");
    Pelicula p2 = new Pelicula(1995, "Pelicula2");
    Pelicula p3 = new Pelicula(2000, "Pelicula3");


    @Test
    void addActriz() {
        assertTrue(ap.addActriz(a1, p1));
        assertFalse(ap.addActriz(a1, p11));

        assertTrue(ap.addActriz(a1, p2));
        assertFalse(ap.addActriz(a1, p2));

        assertTrue(ap.addActriz(a2, p1));
        assertTrue(ap.addActriz(a3, p3));
    }

    @Test
    void actricesOrdenadasPorPremios() {
        ap.addActriz(a2, p2);
        ap.addActriz(a2, p1);
        ap.addActriz(a3, p3);
        ap.addActriz(a1, p1);
        ap.addActriz(a1, p2);
        ap.addActriz(a1, p3);


        List<Actriz> actricesOrdenadas = ap.actricesOrdenadasPorPremios();

        // Crear una lista de películas esperada ordenada por año
        List<Actriz> actricesEsperadas = Arrays.asList(a1, a2, a3);

        // Comprobar si la lista de películas ordenadas es igual a la lista esperada
        assertEquals(actricesEsperadas, actricesOrdenadas);
    }
}