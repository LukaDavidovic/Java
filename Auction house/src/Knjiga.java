public class Knjiga extends UmetnickoDelo {
    private String izdavac;
    private int godinaIzdanja;
    private int brojStrana;

    public Knjiga(String ID, String naziv, String imeAutora, Kvalitet kvalitet, String izdavac, int godinaIzdanja, int brojStrana) {
        super(ID, naziv, imeAutora, kvalitet);
        this.izdavac = izdavac;
        this.godinaIzdanja = godinaIzdanja;
        this.brojStrana = brojStrana;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    @Override
    public int getCena() {
        return this.getKvalitet().getCelobrojnaVred() * brojStrana;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + izdavac + " " + godinaIzdanja + ", " + brojStrana + " strana " + "(" + getCena() + "$)";
    }
}
