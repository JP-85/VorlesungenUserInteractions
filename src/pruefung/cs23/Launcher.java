package pruefung.cs23;

public class Launcher {
    public static void main(String[] args) {
        // Swing sauber im EDT starten
        javax.swing.SwingUtilities.invokeLater(() -> {
            View view = new View();
            view.initialise();
        });
    }
}
