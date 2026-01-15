package awt.awt_106.prof;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GreeterFrame extends Frame {
    private Button b;

    public GreeterFrame() {
        super("Greeter");

        addComponents();
        retgisterEventHandlers();

        pack();
//        setSize(250, 100);

        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

        });

        setVisible(true);
    }

    private void addComponents() {
        b = new Button("Greet me");
        add(b);
    }

    private void retgisterEventHandlers() {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click");
            }
        });
    }
}
