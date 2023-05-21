import java.util.*;

public class Actriz {

    private String nombre;
    private Set<Pelicula> peliculas;

    public Actriz(String nombre) {
        this.nombre = nombre;
        this.peliculas = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public List<Pelicula> getPeliculasOrdenadasPorAnio(){
        List<Pelicula>peliculasPorAnio = new ArrayList<>(peliculas);
        Collections.sort(peliculasPorAnio, new CompararPeliculasAnios());
        return peliculasPorAnio;
    }

    public boolean aniadirPelicula(Pelicula pelicula){
        return this.peliculas.add(pelicula);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actriz actriz = (Actriz) o;

        return Objects.equals(nombre, actriz.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Actriz{");
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", peliculas=").append(peliculas);
        sb.append('}');
        return sb.toString();
    }
}
