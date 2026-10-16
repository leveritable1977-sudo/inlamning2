package inlamning2;


import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KassaFonster().setVisible(true);
        });
    }
}
