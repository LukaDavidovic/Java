import java.util.List;

public abstract class Tim {
        private String naziv;
        private double koeficijent;

    public Tim(List<Double> bodovi, String naziv) {
        this.naziv = naziv;

        this.koeficijent = 0;
        for(Double bod : bodovi)
            this.koeficijent += bod;
        this.koeficijent /= bodovi.size();
    }

    public String getNaziv() {
        return naziv;
    }

    public double getKoeficijent() {
        return koeficijent;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setKoeficijent(double koeficijent) {
        this.koeficijent = koeficijent;
    }

    public abstract double verovatnocaOsvajanja();

    @Override
    public String toString() {
        return this.naziv + " " + String.format("%.2f", this.koeficijent) + " " + String.format("%.2f", this.verovatnocaOsvajanja());
    }
}
