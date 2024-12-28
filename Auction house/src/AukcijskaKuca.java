import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AukcijskaKuca {
    private List<UmetnickoDelo> umetnickaDelaNaProdaju;
    private int zarada;

    public AukcijskaKuca() {
        umetnickaDelaNaProdaju = new ArrayList<>();
        zarada = 0;
    }

    public int getZarada() {
        return zarada;
    }

    public void dodajUmetnickoDelo(UmetnickoDelo ud){
        umetnickaDelaNaProdaju.add(ud);
    }

    public void ucitajUmetnickaDelaIzFajla(String filePath){
        try {
            List<String> linije = Files.readAllLines(Paths.get(filePath));
            for (String l : linije) {
                String[] elems = l.split(",");
                String ID = elems[0];
                String naziv = elems[1];
                String imeAutora = elems[2];
                Kvalitet kvalitet = Kvalitet.createFromInt(Integer.parseInt(elems[3].trim()));

                if (ID.startsWith("K")) {
                    String izdavac = elems[4];
                    int godinaIzdanja = Integer.parseInt(elems[5].trim());
                    int brojStrana = Integer.parseInt(elems[6].trim());
                    dodajUmetnickoDelo(new Knjiga(ID, naziv, imeAutora, kvalitet, izdavac, godinaIzdanja, brojStrana));
                } else if (ID.startsWith("S")) {
                    int godinaStvaranja = Integer.parseInt(elems[4].trim());
                    int tezina = Integer.parseInt(elems[5].trim());
                    dodajUmetnickoDelo(new Skulptura(ID, naziv, imeAutora, kvalitet, godinaStvaranja, tezina));
                } else {
                    System.err.println("Fajl nije u dobrom formatu");
                    System.exit(1);
                }
            }
        }
        catch (IOException e){
            System.err.println("Nije uspelo citanje iz fajla " + filePath);
            System.exit(1);
        }
    }

    public boolean licitacija(String ID, int ponudjenaCena){
        for (int i = 0; i < umetnickaDelaNaProdaju.size(); i++){
            UmetnickoDelo ud = umetnickaDelaNaProdaju.get(i);

            if (ud.getID().equalsIgnoreCase(ID)) {
                if (ponudjenaCena >= ud.getCena()) {
                    zarada += ponudjenaCena - ud.getCena();
                    umetnickaDelaNaProdaju.remove(i);
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        KomparatorUmetnickihDela komparator = new KomparatorUmetnickihDela();
        Collections.sort(umetnickaDelaNaProdaju, komparator);

        StringBuilder sb = new StringBuilder();
        for (UmetnickoDelo ud : umetnickaDelaNaProdaju)
            sb.append(ud).append("\n");

        return sb.toString();
    }
}
