// Seite: 86 - 87
package swing.swing_86_87.skript;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Greeter {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Greeter");

        JButton btnGreet = new JButton("Greet");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello world.");
            }
        };

        btnGreet.addActionListener(listener);

        frame.add(btnGreet);

        frame.setSize(250, 80);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }
}
