// Seite: 90 - 91
package swing.swing_90_91.skript;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Greeter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Greeter");

        JButton btnGreet = new JButton("Greet");
        btnGreet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello world.");
            }
        });

        frame.add(btnGreet);
        frame.setSize(250, 80);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
