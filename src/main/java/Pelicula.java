import java.util.Objects;

public class Pelicula {

    private int anioGanador;
    private String nombre;
    private int edadActriz;

    public Pelicula(int anioGanador, String nombre, int edadActriz) {
        this.anioGanador = anioGanador;
        this.nombre = nombre;
        this.edadActriz = edadActriz;
    }

    public int getAnioGanador() {
        return anioGanador;
    }

    public void setAnioGanador(int anioGanador) {
        this.anioGanador = anioGanador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

        return Objects.equals(nombre, pelicula.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pelicula{");
        sb.append("anioGanador=").append(anioGanador);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", edadActriz=").append(edadActriz);
        sb.append('}');
        return sb.toString();
    }
}
