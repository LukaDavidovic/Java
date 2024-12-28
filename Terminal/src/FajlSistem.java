import java.util.Comparator;

public class FajlSistem {
    private Direktorijum koren;
    private Direktorijum trenutniDirektorijum;

    public FajlSistem() {
        koren = new Direktorijum("/");
        koren.setKoren(true);
        trenutniDirektorijum = koren;
    }

    public Direktorijum getKoren() {
        return koren;
    }

    public Direktorijum getTrenutniDirektorijum() {
        return trenutniDirektorijum;
    }

    public String ls(Comparator<Fajl> komparator) {
        return trenutniDirektorijum.sadrzaj(komparator);
    }

    public String apsolutnaPutanja(){
        Direktorijum td = trenutniDirektorijum;

        if (td.isKoren())
            return "/";

        String apsolutnaPutanja = "";
        while(!td.isKoren()) {
            apsolutnaPutanja = "/" + td.getNaziv() + apsolutnaPutanja;
            td = td.getNadDirektorijum();
        }
        return apsolutnaPutanja;
    }

    public String pwd() {
        return this.apsolutnaPutanja();
    }

    public void cd(String dir) {
        if((!dir.equals("..") && !trenutniDirektorijum.getFajlovi().containsKey(dir)) ||
                (dir.equals("..") && trenutniDirektorijum.isKoren()))
            throw new IllegalArgumentException("Nije moguce prebaciti se u direktorijum " + dir + "!");
        else if(dir.equals(".."))
            trenutniDirektorijum = trenutniDirektorijum.getNadDirektorijum();
        else
            trenutniDirektorijum = (Direktorijum) trenutniDirektorijum.getFajlovi().get(dir);
    }
}
