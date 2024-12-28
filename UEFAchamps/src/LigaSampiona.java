import java.util.List;

public class LigaSampiona extends UEFATakmicenje {
    public LigaSampiona(List<Tim> timoviTakmicenja, List<Grupa> grupeTakmicenja, int koeficijentTakmicenja) {
        super("Liga Sampiona", timoviTakmicenja, grupeTakmicenja, koeficijentTakmicenja);
    }

    @Override
    public double jacinaTakmicenja() {
        double prosecniKoeficijentTimova = 0;
        for (Tim tim : this.timoviTakmicenja)
            prosecniKoeficijentTimova += tim.getKoeficijent();
        prosecniKoeficijentTimova /= this.timoviTakmicenja.size();

        return prosecniKoeficijentTimova;
    }
}
