import java.util.Comparator;

public class CompararPeliculasAnios implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        return p2.getAnioGanador() - p1.getAnioGanador();
    }
}
