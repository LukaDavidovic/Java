public enum Kvalitet {
    LOS(1), OSREDNJI(2), VRHUNSKI(3);

    private int celobrojnaVred;

    Kvalitet(int celobrojnaVred) {
        this.celobrojnaVred = celobrojnaVred;
    }

    static Kvalitet createFromInt(int celobrojnaVred){
        switch (celobrojnaVred){
            case 1 : return LOS;
            case 2 : return OSREDNJI;
            case 3 : return VRHUNSKI;
            default: throw new IllegalArgumentException("Nedozvoljena vrednost");
        }
    }

    public int getCelobrojnaVred() {
        return celobrojnaVred;
    }
}
