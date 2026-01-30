package awt.cardlayout.prof;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {

    private final static String BUTTON = "Button";
    private final static String TEXTFIELD = "Textfield";

    public MyFrame() {

        super("CardLayout");

        setLayout(new BorderLayout(5,5));

        addComponents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void addComponents() {
        Choice c = new Choice();
        c.add(BUTTON);
        c.add(TEXTFIELD);
        Panel cardPanel = new Panel();
        cardPanel.setLayout(new CardLayout());

        c.addItemListener(new CardLayoutItemlistener(cardPanel));


        Panel buttonPanel = new Panel();
        buttonPanel.add(new Button("Button"));

        Panel textFieldPanel = new Panel();
        textFieldPanel.add(new TextField(20));

        cardPanel.add(buttonPanel, BUTTON);
        cardPanel.add(textFieldPanel, TEXTFIELD);

        add(c, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

}
