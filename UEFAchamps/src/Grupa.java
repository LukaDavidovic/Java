import java.util.ArrayList;
import java.util.List;

public class Grupa {
    private String nazivGrupe;
    private List<Tim> timoviGrupe;

    public Grupa(String nazivGrupe, List<Tim> timoviGrupe) {
        this.nazivGrupe = nazivGrupe;
        this.timoviGrupe = timoviGrupe;
    }

    public Grupa(String nazivGrupe) {
        this.nazivGrupe = nazivGrupe;
        this.timoviGrupe = new ArrayList<>();
    }

    public String getNazivGrupe() {
        return nazivGrupe;
    }

    public List<Tim> getTimovi() {
        return timoviGrupe;
    }

    public void setNazivGrupe(String nazivGrupe) {
        this.nazivGrupe = nazivGrupe;
    }

    public void setTimovi(List<Tim> timoviGrupe) {
        this.timoviGrupe = timoviGrupe;
    }

    public void dodajTim(Tim tim){
        this.timoviGrupe.add(tim);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.nazivGrupe).append("\n");

        for (Tim tim : this.timoviGrupe)
            sb.append(tim.toString()).append("\n");

        return sb.toString();
    }
}
