import java.util.Objects;

public class Pelicula implements Comparable<Pelicula> {

    private int anioGanador;
    private String titulo;
    private int edadActriz;

    public Pelicula(int anioGanador, String nombre, int edadActriz) {
        this.anioGanador = anioGanador;
        this.titulo = nombre;
        this.edadActriz = edadActriz;
    }

    public int getAnioGanador() {
        return anioGanador;
    }

    public void setAnioGanador(int anioGanador) {
        this.anioGanador = anioGanador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEdadActriz() {
        return edadActriz;
    }

    public void setEdadActriz(int edadActriz) {
        this.edadActriz = edadActriz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pelicula pelicula = (Pelicula) o;

        return Objects.equals(titulo, pelicula.titulo);
    }

    @Override
    public int hashCode() {
        return titulo != null ? titulo.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pelicula{");
        sb.append("anioGanador=").append(anioGanador);
        sb.append(", nombre='").append(titulo).append('\'');
        sb.append(", edadActriz=").append(edadActriz);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Pelicula o) {
        return this.titulo.compareTo(o.titulo);
    }
}
