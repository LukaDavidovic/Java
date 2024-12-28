import java.util.List;

public class Nenosilac extends Tim {
    private double nacionalniKoeficijent;

    public Nenosilac(List<Double> bodovi, String nazivTima, double nacionalniKoeficijent) {
        super(bodovi, nazivTima);
        this.nacionalniKoeficijent = nacionalniKoeficijent;
    }

    @Override
    public double verovatnocaOsvajanja() {
        return this.getKoeficijent() * this.nacionalniKoeficijent / 100;
    }
}
