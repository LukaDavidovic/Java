import java.util.List;

public abstract class UEFATakmicenje {
    protected String nazivTakmicenja;
    protected List<Tim> timoviTakmicenja;
    protected List<Grupa> grupeTakmicenja;
    protected int koeficijentTakmicenja;

    public UEFATakmicenje(String nazivTakmicenja, List<Tim> timoviTakmicenja, List<Grupa> grupeTakmicenja, int koeficijentTakmicenja) {
        this.nazivTakmicenja = nazivTakmicenja;
        this.timoviTakmicenja = timoviTakmicenja;
        this.grupeTakmicenja = grupeTakmicenja;
        this.koeficijentTakmicenja = koeficijentTakmicenja;
    }

    public double nagradniFond(){
        double prosecniKoeficijentTimova = 0;
        for (Tim tim : this.timoviTakmicenja)
            prosecniKoeficijentTimova += tim.getKoeficijent();
        prosecniKoeficijentTimova /= this.timoviTakmicenja.size();

        return prosecniKoeficijentTimova * koeficijentTakmicenja / this.timoviTakmicenja.size();
    }

    public abstract double jacinaTakmicenja();

    public String prikaziGrupe(){
        StringBuilder sb = new StringBuilder();

        for (Grupa grupa : this.grupeTakmicenja)
            sb.append(grupa.toString()).append("\n");

        return sb.toString();
    }

    public String prikaziTimove(){
        StringBuilder sb = new StringBuilder();

        for (Tim tim : this.timoviTakmicenja)
            sb.append(tim.toString()).append("\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.nazivTakmicenja).append(" ");
        sb.append(this.koeficijentTakmicenja).append(" ");
        sb.append(String.format("%.2f", this.jacinaTakmicenja())).append(" ");
        sb.append(String.format("%.2f", this.nagradniFond()));

        return sb.toString();
    }
}
