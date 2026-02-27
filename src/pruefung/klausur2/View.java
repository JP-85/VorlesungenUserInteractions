package pruefung.klausur2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View extends JFrame {
    JTextField tfM;
    JTextField tfInch;
    JTextField tfFoot;
    JTextField tfYard;
    private JTextField tf;

    private Controller controller;

    private Model model;
    private JComboBox<String> cb;

    public void initialize(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;

        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));
        addComponents();

        pack();
        setVisible(true);
    }


    private void addComponents() {
        JPanel pNorth = new JPanel();
        pNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));

        tf = new JTextField();
        tf.setColumns(20);
        tf.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        pNorth.add(tf);

        cb = new JComboBox<>(new String[] {"m", "inch", "foot", "yard"});
        cb.setActionCommand("choose");
        cb.addActionListener(this.controller);
        pNorth.add(cb);

        JPanel pCenter = new JPanel();
        pCenter.setBorder(new EmptyBorder(0, 20, 0, 0));
        pCenter.setLayout(new GridLayout(4, 3, 5, 5));

        JButton btn7 = new JButton("7");
        pCenter.add(btn7);

        JButton btn8 = new JButton("8");
        pCenter.add(btn8);
        btn7.addActionListener(this.controller);
        JButton btn9 = new JButton("9");
        pCenter.add(btn9);
        btn9.addActionListener(this.controller);
        JButton btn4 = new JButton("4");
        pCenter.add(btn4);
        btn4.addActionListener(this.controller);
        JButton btn5 = new JButton("5");
        pCenter.add(btn5);
        btn5.addActionListener(this.controller);
        JButton btn6 = new JButton("6");
        pCenter.add(btn6);
        btn6.addActionListener(this.controller);
        JButton btn3 = new JButton("3");
        pCenter.add(btn3);
        btn3.addActionListener(this.controller);
        JButton btn2 = new JButton("2");
        pCenter.add(btn2);
        btn2.addActionListener(this.controller);
        JButton btn1 = new JButton("1");
        pCenter.add(btn1);
        btn1.addActionListener(this.controller);
        JButton btn0 = new JButton("0");
        pCenter.add(btn0);
        btn0.addActionListener(this.controller);
        JButton btnDot  = new JButton(".");
        pCenter.add(btnDot);
        btnDot.addActionListener(this.controller);
        JButton btnC = new JButton("C");
        pCenter.add(btnC);
        btnC.addActionListener(this.controller);


        JPanel pEast = new JPanel();
        pEast.setLayout(new BoxLayout(pEast, BoxLayout.Y_AXIS));
        tfM = addField(pEast, "m");
        tfInch = addField(pEast, "inch");
        tfFoot = addField(pEast, "foot");
        tfYard = addField(pEast, "yard");

        JPanel pSouth = new JPanel();
        JButton btnCalc = new JButton("Calculate");
        btnCalc.addActionListener(this.controller);
//        btnCalc.setActionCommand("Calculate");
        pSouth.add(btnCalc);

        add(pNorth, BorderLayout.NORTH);
        add(pCenter, BorderLayout.CENTER);
        add(pEast, BorderLayout.EAST);
        add(pSouth, BorderLayout.SOUTH);
    }

    private JTextField addField(JPanel parent, String label) {

        JLabel l = new JLabel(label);

        JTextField tf = new JTextField();
        tf.setColumns(20);
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        tf.setEnabled(false);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

        p.add(tf);
        p.add(l);

        parent.add(p);

        return tf;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        tfM.setText(Model.format(this.model.getMeter()));
        tfInch.setText(Model.format(this.model.getInch()));
        tfFoot.setText(Model.format(this.model.getFoot()));
        tfYard.setText(Model.format(this.model.getYard()));

    }

    public String getCurrentInput(){
        return tf.getText();
    }

    public void setCurrentInput(String input){
        tf.setText(input);
    }

    public String getCurrentSelection(){
        return tf.getText();
    }
    public String setCurrentSelection(String selection){
        return (String) cb.getSelectedItem();
    }
}
