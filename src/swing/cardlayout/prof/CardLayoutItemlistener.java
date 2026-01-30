package swing.cardlayout.prof;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CardLayoutItemlistener implements ItemListener {
    private final JPanel cardPanel;

    public CardLayoutItemlistener(JPanel cardPanel) {
        this.cardPanel = cardPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String newSlelection = (String)e.getItem();

        CardLayout cl  = (CardLayout)cardPanel.getLayout();
        cl.show(cardPanel, newSlelection);
        System.out.println(newSlelection);
    }
}
