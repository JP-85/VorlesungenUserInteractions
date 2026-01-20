package self_exercises.calculator;

import java.awt.*;
import java.awt.event.*;

public class MainFrame extends Frame {

    private final Button[] BUTTONS = new Button[16];
    private final TextField display = new TextField("0");
    private final Panel buttonPanel = new Panel(new GridLayout(4, 4, 5, 5));

    private double storedValue = 0.0;
    private String pendingOp = null;
    private boolean startNewNumber = true;
    private boolean errorState = false;

    public MainFrame() {
        super("Calculator");

        setSize(220, 360);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        addComponents();
        registerActionListeners();

        setVisible(true);
    }

    private void addComponents() {
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.BOLD, 22));
        add(display, BorderLayout.NORTH);

        String[] labels = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", "C", "=", "/"
        };

        for (int i = 0; i < labels.length; i++) {
            Button btn = new Button(labels[i]);
            buttonPanel.add(btn);
            BUTTONS[i] = btn;
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void registerActionListeners() {

        ActionListener numberListener = e -> {
            String digit = e.getActionCommand();

            if (errorState) clearAll();

            if (startNewNumber) {
                display.setText(digit);
                startNewNumber = false;
            } else {
                if ("0".equals(display.getText())) display.setText(digit);
                else display.setText(display.getText() + digit);
            }
        };

        ActionListener operatorListener = e -> {
            String op = e.getActionCommand();

            if (errorState) return;

            double current = parseDisplay();

            if (pendingOp == null) {
                storedValue = current;
            } else if (!startNewNumber) {
                storedValue = applyOp(storedValue, current, pendingOp);
                if (errorState) return;
                display.setText(format(storedValue));
            }

            pendingOp = op;
            startNewNumber = true;
        };

        ActionListener equalListener = e -> {
            if (errorState) return;
            if (pendingOp == null) return;

            double current = parseDisplay();
            double result = applyOp(storedValue, current, pendingOp);
            if (errorState) return;

            display.setText(format(result));

            storedValue = result;
            pendingOp = null;
            startNewNumber = true;
        };

        ActionListener cancelListener = e -> clearAll();

        for (int i = 3; i < 16; i += 4) {
            BUTTONS[i].addActionListener(operatorListener);
        }

        for (int i = 0; i <= 12; i++) {
            if (i % 4 != 3) {
                BUTTONS[i].addActionListener(numberListener);
            }
        }

        BUTTONS[13].addActionListener(cancelListener); // "C"
        BUTTONS[14].addActionListener(equalListener);  // "="
    }

    private double parseDisplay() {
        try {
            return Double.parseDouble(display.getText());
        } catch (NumberFormatException ex) {
            setError();
            return 0.0;
        }
    }

    private double applyOp(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0.0) {
                    setError();
                    yield 0.0;
                }
                yield a / b;
            }
            default -> a;
        };
    }

    private void clearAll() {
        storedValue = 0.0;
        pendingOp = null;
        startNewNumber = true;
        errorState = false;
        display.setText("0");
    }

    private void setError() {
        errorState = true;
        pendingOp = null;
        startNewNumber = true;
        display.setText("Error");
    }

    private String format(double x) {
        // 10.0 -> "10"
        if (x == (long) x) return Long.toString((long) x);
        return Double.toString(x);
    }
}
