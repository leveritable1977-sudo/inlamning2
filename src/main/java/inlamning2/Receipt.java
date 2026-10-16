package inlamning2;


import java.util.ArrayList;

public class Receipt {
    private ArrayList<ReceiptRow> rader = new ArrayList<>();

    // Lägger till en produkt eller öka antalet
    public void add(Produkt produkt, int antal) {
        for (ReceiptRow rad : rader) {
            if (rad.getProdukt().getNamn().equalsIgnoreCase(produkt.getNamn())) {
                rad.laggTillAntal(antal);
                return;
            }
        }
        // 1. Skapa den nya raden först
        ReceiptRow nyRad = new ReceiptRow(produkt, antal);

// 2. Lägg till den i listan
        rader.add(nyRad);
    }

    // Beräknar totala bruttopriset (inkl. moms) för hela kvittot
    public double getTotal() {
        double total = 0;
        for (ReceiptRow rad : rader) {
            total += rad.getRadTotal();
        }
        return total;
    }

    // Beräknar totala momsen i kronor för hela kvittot
    public double getMoms() {
        double totalMoms = 0;
        for (ReceiptRow rad : rader) {
            totalMoms += rad.getRadMoms();
        }
        return totalMoms;
    }

    public ArrayList<ReceiptRow> getRader() {

        return rader;
    }

    // Beräknar nettot (totalt minus moms)
    public double getNetto() {
        return getTotal() - getMoms();
    }
    // Beräknar momsprocenten för hela köpet
    public double getMomsProcent() {
        double netto = getNetto();
        if (netto == 0) {
            return 0;
        }
        return getMoms() / netto * 100;
    }
    // Nollställer varorna inför nästa köp
    public void rensa() {

        rader.clear();
    }
}
