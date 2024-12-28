import java.util.Comparator;

public class KomparatorUmetnickihDela implements Comparator<UmetnickoDelo> {
    @Override
    public int compare(UmetnickoDelo ud1, UmetnickoDelo ud2) {
        if (ud1 instanceof Knjiga && ud2 instanceof Skulptura)
            return -1;
        else if (ud1 instanceof Skulptura && ud2 instanceof Knjiga)
            return 1;
        else if (ud1.getKvalitet() != ud2.getKvalitet())
            return -Integer.compare(ud1.getKvalitet().getCelobrojnaVred(), ud2.getKvalitet().getCelobrojnaVred());
        else if (ud1 instanceof Knjiga && ud2 instanceof Knjiga){
            Knjiga k1 = (Knjiga) ud1;
            Knjiga k2 = (Knjiga) ud2;
            return Integer.compare(k1.getGodinaIzdanja(), k2.getGodinaIzdanja());
        }
        else {
            Skulptura s1 = (Skulptura) ud1;
            Skulptura s2 = (Skulptura) ud2;
            return Integer.compare(s1.getGodinaStvaranja(), s2.getGodinaStvaranja());
        }
    }
}
