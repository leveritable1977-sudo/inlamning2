package inlamning2;


public class Produkt {
    private String namn;
    private double pris;

    public Produkt(String namn, double pris) {
        this.namn = namn;
        this.pris = pris;
    }

    public String getNamn() { return namn; }
    public double getPris() { return pris; }

    // 1. Bas-metoden returnerar 12% som standard
    public double getMomsSats() {

        return 12.0;
    }

    // 2. Lärarens formel – hämtar momssatsen via anropet getMomsSats()
    public double getMoms() {
        double brutto = pris;
        double momsSats = getMomsSats(); // <-- HÄR anropas metoden!
        double netto = brutto / (1.0 + (momsSats / 100.0));
        return brutto - netto;
    }


}
