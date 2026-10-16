package inlamning2;


public class ReceiptRow {
    private Produkt produkt;
    private int antal;

    public ReceiptRow(Produkt produkt, int antal) {
        this.produkt = produkt;
        this.antal = antal;
    }

    public Produkt getProdukt() {

        return produkt;
    }

    public int getAntal() {

        return antal;
    }

    public void laggTillAntal(int extraAntal) {

        this.antal += extraAntal;
    }

    // Totalsumma för denna rad
    public double getRadTotal() {

        return produkt.getPris() * antal;
    }

    // Total moms i kronor för denna rad
    public double getRadMoms() {

        return produkt.getMoms() * antal;
    }
}
