import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActrizTest {

    Actriz actriz = new Actriz(25, "Actriz1");
        Pelicula p1 = new Pelicula(1990, "Pelicula1");
        Pelicula p2 = new Pelicula(1995, "Pelicula2");
        Pelicula p3 = new Pelicula(2000, "Pelicula3");

    @org.junit.jupiter.api.Test
    void aniadirPelicula() {
        assertTrue(actriz.aniadirPelicula(p3));
        assertTrue(actriz.aniadirPelicula(p1));
        assertTrue(actriz.aniadirPelicula(p2));
    }

    @org.junit.jupiter.api.Test
    void getPeliculasOrdenadasPorAnio() {
        actriz.aniadirPelicula(p3);
        actriz.aniadirPelicula(p1);
        actriz.aniadirPelicula(p2);
        List<Pelicula> peliculasOrdenadas = actriz.getPeliculasOrdenadasPorAnio();

        // Crear una lista de películas esperada ordenada por año
        List<Pelicula> peliculasEsperadas = Arrays.asList(p1, p2, p3);

        // Comprobar si la lista de películas ordenadas es igual a la lista esperada
        assertEquals(peliculasEsperadas, peliculasOrdenadas);
    }
}