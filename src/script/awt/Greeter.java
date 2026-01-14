// Seite: 86 - 87
package script.awt;

import java.awt.*;
import java.awt.event.*;

public class Greeter {

    public static void main(String[] args) {
        Frame frame = new Frame("Greeter");

        Button btnGreet = new Button("Greet");
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

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
