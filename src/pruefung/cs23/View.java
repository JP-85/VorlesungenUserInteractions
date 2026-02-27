package pruefung.cs23;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View extends JFrame {

    // Optional als Felder, falls später Events/Logik drauf soll
    private JTextField inputField;
    private JComboBox<String> unitCombo;

    private JButton[] keypadButtons;

    private JTextField outM, outInch, outFoot, outYard;
    private JButton calculateButton;

    public void initialise() {
        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Content-Panel, damit man Gaps + Außenabstand sauber setzen kann
        JPanel content = new JPanel(new BorderLayout(10, 10)); // hgap/vgap = 10
        content.setBorder(new EmptyBorder(10, 10, 10, 10));    // Rand nach außen
        setContentPane(content);

        addComponents(); // << Aufgabe verlangt: alle weiteren Änderungen hier

        pack();
        setLocationRelativeTo(null); // optional nett: zentrieren
        setVisible(true);
    }

    private void addComponents() {
        // --- Panel 1 (grün): Eingabe + Einheit ---
        JPanel panelInput = new JPanel(new BorderLayout(10, 0));
        inputField = new JTextField(20);
        unitCombo = new JComboBox<>(new String[]{"m", "inch", "foot", "yard"});
        panelInput.add(inputField, BorderLayout.CENTER);
        panelInput.add(unitCombo, BorderLayout.EAST);

        // --- Panel 2 (Mitte): Keypad 4x3 ---
        JPanel panelKeypad = new JPanel(new GridLayout(4, 3, 10, 10));
        String[] keys = {"7","8","9","4","5","6","1","2","3","0",".","C"};
        keypadButtons = new JButton[keys.length];
        for (int i = 0; i < keys.length; i++) {
            keypadButtons[i] = new JButton(keys[i]);
            panelKeypad.add(keypadButtons[i]);
        }

        // --- Panel 3 (rot): Ergebnisse rechts (Zeilen-Panels) ---
        JPanel panelResults = new JPanel(new GridLayout(4, 1, 0, 10));

        outM = createOutputField();
        outInch = createOutputField();
        outFoot = createOutputField();
        outYard = createOutputField();

        panelResults.add(createResultRow(outM, "m"));
        panelResults.add(createResultRow(outInch, "inch"));
        panelResults.add(createResultRow(outFoot, "foot"));
        panelResults.add(createResultRow(outYard, "yard"));

        // --- Panel 4 (magenta): Calculate Button ---
        JPanel panelCalc = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calculateButton = new JButton("Calculate");
        panelCalc.add(calculateButton);

        // --- Panels korrekt im Frame platzieren ---
        getContentPane().add(panelInput, BorderLayout.NORTH);
        getContentPane().add(panelKeypad, BorderLayout.CENTER);
        getContentPane().add(panelResults, BorderLayout.EAST);
        getContentPane().add(panelCalc, BorderLayout.SOUTH);
    }

    private JTextField createOutputField() {
        JTextField tf = new JTextField(10);
        tf.setEditable(false);
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setText("0");
        return tf;
    }

    private JPanel createResultRow(JTextField field, String unit) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.add(field, BorderLayout.CENTER);
        row.add(new JLabel(unit), BorderLayout.EAST);
        return row;
    }
}
