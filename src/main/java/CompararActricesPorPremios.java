import java.util.Comparator;

public class CompararActricesPorPremios implements Comparator<Actriz> {

    @Override
    public int compare(Actriz a1, Actriz a2) {
        return a2.getPeliculas().size() - a1.getPeliculas().size();
    }
}
