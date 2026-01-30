package awt.awt_cardlayout_124_128;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame implements ItemListener {
    private final static String BP = " Card with Button ";
    private final static String TP = " Card with TextField ";
    private Panel cards;

    public MyFrame() {
        super(" CardLayout Demo ");

        Panel card1 = new Panel();
        card1.add(new Button(" Button "));
        Panel card2 = new Panel();
        card2.add(new TextField(" TextField "));

        cards = new Panel(new CardLayout());
        cards.add(card1, BP);
        cards.add(card2, TP);

        Panel choicePane = new Panel();

        Choice choice = new Choice();
        choice.add(BP);
        choice.add(TP);

        choice.addItemListener(this);

        choicePane.add(choice);
        add(choicePane, BorderLayout.PAGE_START);
        add(cards, BorderLayout.CENTER);

        pack();

        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

        });

        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) e.getItem());
    }
}
