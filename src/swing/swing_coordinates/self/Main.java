package swing.swing_coordinates.self;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame(250, 10));
    }
}
