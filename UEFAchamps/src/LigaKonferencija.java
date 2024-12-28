import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LigaKonferencija extends UEFATakmicenje {
    public LigaKonferencija(List<Tim> timoviTakmicenja, List<Grupa> grupeTakmicenja, int koeficijentTakmicenja) {
        super("Liga Konferencija", timoviTakmicenja, grupeTakmicenja, koeficijentTakmicenja);
    }

    @Override
    public double jacinaTakmicenja() {
        List<Tim> timoviTMP = null;

        Collections.copy(timoviTMP, this.timoviTakmicenja);

        Collections.sort(timoviTMP, new Comparator<Tim>() {
            @Override
            public int compare(Tim t1, Tim t2) {
                return Double.compare(t1.getKoeficijent(), t2.getKoeficijent());
            }
        });

        timoviTMP = timoviTMP.subList(5, timoviTMP.size() - 5);

        double prosecniKoeficijentTimova = 0;
        for (Tim tim : timoviTMP)
            prosecniKoeficijentTimova += tim.getKoeficijent();
        prosecniKoeficijentTimova /= timoviTMP.size();

        return prosecniKoeficijentTimova;
    }
}
