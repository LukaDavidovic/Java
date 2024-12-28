import java.util.List;

public class Nosilac extends Tim implements Povlasceni {
    public Nosilac(List<Double> bodovi, String nazivTima) {
        super(bodovi, nazivTima);
    }

    @Override
    public double verovatnocaOsvajanja() {
        return this.getKoeficijent() * 30 / 100;
    }
}
