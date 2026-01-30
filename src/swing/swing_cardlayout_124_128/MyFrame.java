package swing.swing_cardlayout_124_128;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyFrame extends JFrame implements ItemListener {
    private final static String BP = " Card with Button ";
    private final static String TP = " Card with TextField ";
    private JPanel cards;

    public MyFrame() {
        super(" CardLayout Demo ");

        JPanel card1 = new JPanel();
        card1.add(new JButton(" Button "));
        JPanel card2 = new JPanel();
        card2.add(new JTextField(" TextField "));

        cards = new JPanel(new CardLayout());
        cards.add(card1, BP);
        cards.add(card2, TP);

        JPanel choicePane = new JPanel();

        JComboBox<String> choice = new JComboBox<>();
        choice.addItem(BP);
        choice.addItem(TP);

        choice.addItemListener(this);

        choicePane.add(choice);
        add(choicePane, BorderLayout.PAGE_START);
        add(cards, BorderLayout.CENTER);

        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) e.getItem());
    }
}
