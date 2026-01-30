package swing.cardlayout.prof;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private final static String BUTTON = "Button";
    private final static String TEXTFIELD = "Textfield";

    public MyFrame() {

        super("CardLayout");

        setLayout(new BorderLayout(5,5));

        addComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void addComponents() {
        JComboBox<String> c = new JComboBox<>();
        c.addItem(BUTTON);
        c.addItem(TEXTFIELD);
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());

        c.addItemListener(new CardLayoutItemlistener(cardPanel));


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Button"));

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(new JTextField(20));

        cardPanel.add(buttonPanel, BUTTON);
        cardPanel.add(textFieldPanel, TEXTFIELD);

        add(c, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

}
