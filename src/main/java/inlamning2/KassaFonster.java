package inlamning2;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KassaFonster extends JFrame {

    private JTextField inputField;     //
    private JTextField displayField;   //
    private JButton btnAdd;
    private JButton btnPay;
    private JTextArea receiptArea;     //


    private Produkt valdVara = null;

    // Koppling till Receipt-klass
    private Receipt kvitto = new Receipt();
    private int kvittoNummer = 1;

    public KassaFonster() {
        setTitle("Kassasystem");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(Color.GREEN);

        // 1. Skapa produkterna separat
        Produkt bulle = new Produkt("Bulle", 20.0);
        Produkt kaffe = new Produkt("Kaffe", 25.0);
        Produkt tidning = new Tidning("Aftonbladet", 35.0);

        // 2. Lägg dem i en array
        Produkt[] produkter = { bulle, kaffe, tidning };

        // 3. Loopa igenom och skapa knappar dynamiskt
        for (Produkt prod : produkter) {
            JButton btn = new JButton(prod.getNamn());

            btn.addActionListener(e -> {
                valdVara = prod;
                displayField.setText(prod.getNamn());
            });

            centerPanel.add(btn);
        }


        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.BLACK);

        JLabel label = new JLabel("Antal:");
        label.setForeground(Color.WHITE);

        inputField = new JTextField(5);
        inputField.setText("1");

        displayField = new JTextField(12);
        displayField.setEditable(false);
        displayField.setText("Ingen vald");

        btnAdd = new JButton("Add");
        btnPay = new JButton("Pay");

        bottomPanel.add(label);
        bottomPanel.add(inputField);
        bottomPanel.add(displayField);
        bottomPanel.add(btnAdd);
        bottomPanel.add(btnPay);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setPreferredSize(new Dimension(300, 0));

        add(mainPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.EAST);


        rensaKvitto();

        // Händelselyssnare för knappar
        btnAdd.addActionListener(e -> laggTillIInkopslista());
        btnPay.addActionListener(e -> hanteraBetalning());
    }

    private void laggTillIInkopslista() {
        if (valdVara == null) {
            JOptionPane.showMessageDialog(this, "Välj en produkt först!");
            return;
        }

        try {
            int antal = Integer.parseInt(inputField.getText().trim());
            if (antal <= 0) {
                JOptionPane.showMessageDialog(this, "Antal måste vara större än 0!", "Varning", JOptionPane.WARNING_MESSAGE);
                inputField.setText("1");
                return;
            }

            kvitto.add(valdVara, antal);
            uppdateraKvittoVisning();

            inputField.setText("1");
            displayField.setText("Ingen vald");
            valdVara = null;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Skriv ett  tal !", "Fel", JOptionPane.ERROR_MESSAGE);
            inputField.setText("1");
        }
    }

    private void uppdateraKvittoVisning() {
        rensaKvitto();
        for (ReceiptRow rad : kvitto.getRader()) {
            String radText = String.format("%-12s %2d * %6.2f = %6.2f",
                    rad.getProdukt().getNamn(),
                    rad.getAntal(),
                    rad.getProdukt().getPris(),
                    rad.getRadTotal());
            receiptArea.append(radText + "\n");
        }
    }

    private void hanteraBetalning() {
        if (kvitto.getRader().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inga varor i vagnen!");
            return;
        }

        double brutto = kvitto.getTotal();
        double totalMoms = kvitto.getMoms();
        double netto = kvitto.getNetto();
        double momsProcent = kvitto.getMomsProcent();


        receiptArea.append("\n--------------------------------\n");
        receiptArea.append("       TACK FÖR DITT KÖP!\n");
        receiptArea.append("--------------------------------\n");
        receiptArea.append(String.format("Moms%%:  %.2f%%\n", momsProcent));
        receiptArea.append(String.format("Moms:   %.2f kr\n", totalMoms));
        receiptArea.append(String.format("Netto:  %.2f kr\n", netto));
        receiptArea.append(String.format("Brutto: %.2f kr\n", brutto));

        JOptionPane.showMessageDialog(this, "Betalning utförd! Totalt: " + String.format("%.2f kr", brutto));

        kvitto.rensa();
        kvittoNummer++;
        valdVara = null;
        displayField.setText("Ingen vald");
        inputField.setText("1");
        rensaKvitto();
    }

    private void rensaKvitto() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        receiptArea.setText("====== KVITTO #" + kvittoNummer + " ======\n" +
                "Datum: " + formattedDate + "\n\n");
    }
}
