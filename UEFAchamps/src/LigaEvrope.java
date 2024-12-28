import java.util.List;

public class LigaEvrope extends UEFATakmicenje {
    public LigaEvrope(List<Tim> timoviTakmicenja, List<Grupa> grupeTakmicenja, int koeficijentTakmicenja) {
        super("Liga Evrope", timoviTakmicenja, grupeTakmicenja, koeficijentTakmicenja);
    }

    @Override
    public double jacinaTakmicenja() {
        double minKoef = this.timoviTakmicenja.get(0).getKoeficijent();
        double maxKoef = this.timoviTakmicenja.get(0).getKoeficijent();

        for (Tim tim : this.timoviTakmicenja){
            if (tim.getKoeficijent() < minKoef)
                minKoef = tim.getKoeficijent();
            if (tim.getKoeficijent() > maxKoef)
                maxKoef = tim.getKoeficijent();
        }

        return (minKoef + maxKoef) / 2;
    }
}
