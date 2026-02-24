# Java Swing Referenz (Layouts, Listener, Misc) – kompakt


## 0) Grundgerüst: JFrame + EDT + ContentPane

Man erstellt Swing-UIs im **Event Dispatch Thread (EDT)**:

```java
import javax.swing.*;
import java.awt.*;

public class App {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Swing App");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel root = new JPanel();
      frame.setContentPane(root);

      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}
```

---

## 1) Layouts (LayoutManager)

Layouts bestimmen, **wie** Komponenten platziert und skaliert werden.

### 1.1 FlowLayout (Standard bei JPanel)

Komponenten fließen von links nach rechts (mit Umbruch).

```java
JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
p.add(new JButton("A"));
p.add(new JButton("B"));
```

---

### 1.2 BorderLayout (Standard bei JFrame-ContentPane)

5 Bereiche: `NORTH`, `SOUTH`, `WEST`, `EAST`, `CENTER`.

```java
JPanel p = new JPanel(new BorderLayout(10, 10));
p.add(new JLabel("Oben"), BorderLayout.NORTH);
p.add(new JScrollPane(new JTextArea(10, 30)), BorderLayout.CENTER);
p.add(new JButton("Unten"), BorderLayout.SOUTH);
```

---

### 1.3 GridLayout

Raster; alle Zellen gleich groß.

```java
JPanel p = new JPanel(new GridLayout(2, 3, 10, 10));
for (int i = 1; i <= 6; i++) p.add(new JButton(String.valueOf(i)));
```

---

### 1.4 BoxLayout

Vertikal (`Y_AXIS`) oder horizontal (`X_AXIS`) stapeln.

```java
JPanel p = new JPanel();
p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

p.add(new JLabel("Name"));
p.add(new JTextField(15));
```

Abstände/„Spacer“:

```java
p.add(Box.createVerticalStrut(8));
p.add(Box.createHorizontalStrut(8));
p.add(Box.createVerticalGlue());
```

---

### 1.5 GridBagLayout (flexibel, für Formulare)

Sehr mächtig, aber etwas ausführlicher.

```java
JPanel p = new JPanel(new GridBagLayout());
GridBagConstraints c = new GridBagConstraints();
c.insets = new Insets(5,5,5,5);
c.fill = GridBagConstraints.HORIZONTAL;

c.gridx = 0; c.gridy = 0;
p.add(new JLabel("Name"), c);

c.gridx = 1; c.gridy = 0; c.weightx = 1.0;
p.add(new JTextField(15), c);

c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
p.add(new JButton("OK"), c);
```

---

### 1.6 CardLayout (Screens wechseln)

Ideal für „Login/Settings/…“ oder Wizard-Ansichten.

```java
JPanel cards = new JPanel(new CardLayout());
cards.add(new JLabel("Screen A"), "A");
cards.add(new JLabel("Screen B"), "B");

CardLayout cl = (CardLayout) cards.getLayout();
cl.show(cards, "B");
```

---

### 1.7 GroupLayout (typisch GUI-Builder)

Wird häufig automatisch erzeugt (z. B. NetBeans). Manuell eher selten.

---

### 1.8 SpringLayout (selten)

Constraint-basiert, in der Praxis selten nötig.

---

### 1.9 Null-Layout / Absolute Positionierung (vermeiden)

Man setzt Koordinaten per `setBounds`, was bei Fonts/DPI/OS schnell kaputt geht.

```java
panel.setLayout(null);
button.setBounds(10, 10, 120, 30);
panel.add(button);
```

---

## 2) ActionListener & Events

### 2.1 JButton: ActionListener

```java
button.addActionListener(e -> System.out.println("clicked"));
```

---

### 2.2 ActionCommand (Events unterscheiden)

```java
JButton plus = new JButton("+");
plus.setActionCommand("PLUS");

plus.addActionListener(e -> {
  if ("PLUS".equals(e.getActionCommand())) {
    // ...
  }
});
```

---

### 2.3 Ein Listener für viele Buttons

```java
ActionListener l = e -> {
  JButton b = (JButton) e.getSource();
  System.out.println(b.getText());
};

for (String s : new String[]{"1","2","3"}) {
  JButton b = new JButton(s);
  b.addActionListener(l);
  panel.add(b);
}
```

---

### 2.4 JTextField: Enter-Event

```java
field.addActionListener(e -> System.out.println(field.getText()));
```

---

### 2.5 JCheckBox / JToggleButton

```java
JCheckBox cb = new JCheckBox("Aktiv");
cb.addActionListener(e -> System.out.println(cb.isSelected()));
```

---

### 2.6 JComboBox (Auswahl)

```java
JComboBox<String> box = new JComboBox<>(new String[]{"A","B","C"});
box.addActionListener(e -> System.out.println(box.getSelectedItem()));
```

---

### 2.7 JList Selection Listener

```java
list.addListSelectionListener(e -> {
  if (!e.getValueIsAdjusting()) {
    System.out.println(list.getSelectedValue());
  }
});
```

---

### 2.8 JTable Selection Listener

```java
table.getSelectionModel().addListSelectionListener(e -> {
  if (!e.getValueIsAdjusting()) {
    System.out.println(table.getSelectedRow());
  }
});
```

---

### 2.9 DocumentListener (Textänderungen live)

Praktisch für Zeichen zählen/Validierung.

```java
field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
  void update() { System.out.println(field.getText()); }
  public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
  public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
  public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
});
```

---

## 3) Misc (Panels, Scroll, Menüs, Updates, Threads)

### 3.1 JPanel als Standard-Container

```java
JPanel panel = new JPanel(); // Default: FlowLayout
panel.add(new JLabel("Hi"));
panel.add(new JButton("OK"));
```

---

### 3.2 ContentPane sauber setzen

```java
frame.setContentPane(panel);
```

---

### 3.3 Padding / Ränder

```java
panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
```

---

### 3.4 pack() vs setSize()

`pack()` passt an Inhalte an; `setSize()` setzt fest.

```java
frame.pack();
frame.setSize(400, 300);
```

---

### 3.5 Dynamische UI-Änderungen: revalidate/repaint

Nach `add/remove` zur Laufzeit:

```java
panel.add(new JButton("Neu"));
panel.revalidate();
panel.repaint();
```

---

### 3.6 JScrollPane (Scrollen)

Für `JTextArea`, `JList`, `JTable` fast immer.

```java
JTextArea area = new JTextArea(10, 30);
JScrollPane sp = new JScrollPane(area);
panel.add(sp);
```

---

### 3.7 JOptionPane Dialoge

```java
JOptionPane.showMessageDialog(frame, "Hallo!");

String name = JOptionPane.showInputDialog(frame, "Name?");

int choice = JOptionPane.showConfirmDialog(
  frame,
  "Wirklich löschen?",
  "Confirm",
  JOptionPane.YES_NO_OPTION
);
```

---

### 3.8 Menüleiste (JMenuBar)

```java
JMenuBar bar = new JMenuBar();
JMenu file = new JMenu("Datei");
JMenuItem exit = new JMenuItem("Beenden");

exit.addActionListener(e -> System.exit(0));
file.add(exit);
bar.add(file);

frame.setJMenuBar(bar);
```

---

### 3.9 Timer (UI-Updates)

```java
new javax.swing.Timer(1000, e -> label.setText("tick")).start();
```

---

### 3.10 SwingWorker (lange Aufgaben ohne UI-Block)

Man führt lange Tasks nicht direkt im Listener aus.

```java
SwingWorker<String, Void> worker = new SwingWorker<>() {
  @Override
  protected String doInBackground() throws Exception {
    Thread.sleep(2000);
    return "fertig";
  }

  @Override
  protected void done() {
    try { label.setText(get()); }
    catch (Exception ignored) {}
  }
};
worker.execute();
```

---

### 3.11 Key Bindings (besser als KeyListener)

Man bindet Shortcuts zuverlässig an eine Komponente.

```java
JComponent c = panel;

c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
 .put(KeyStroke.getKeyStroke("ESCAPE"), "quit");

c.getActionMap().put("quit", new AbstractAction() {
  @Override
  public void actionPerformed(java.awt.event.ActionEvent e) {
    System.exit(0);
  }
});
```

---

## 4) Quick Patterns

### 4.1 Formular (Label links, Field rechts) – GridBagLayout Mini

```java
JPanel p = new JPanel(new GridBagLayout());
GridBagConstraints c = new GridBagConstraints();
c.insets = new Insets(5,5,5,5);
c.fill = GridBagConstraints.HORIZONTAL;

c.gridx=0; c.gridy=0;
p.add(new JLabel("Name"), c);

c.gridx=1; c.gridy=0; c.weightx=1;
p.add(new JTextField(15), c);
```

---

### 4.2 Screen wechseln – CardLayout Mini

```java
CardLayout cl = new CardLayout();
JPanel cards = new JPanel(cl);

cards.add(new JPanel(), "A");
cards.add(new JPanel(), "B");

cl.show(cards, "B");
```

---

## 5) Häufige Fehler (und wie man sie vermeidet)

* **UI nicht im EDT gebaut** → immer `SwingUtilities.invokeLater(...)` verwenden.
* **`null`-Layout / absolute Positionierung** → LayoutManager nutzen (DPI/Fonts/Resize!).
* **Lange Arbeit im Listener** → UI friert ein → `SwingWorker`/Threading verwenden.
* **Nach `add/remove` kein Update** → `revalidate()` + `repaint()` auf dem Container.
* **`pack()` vergessen** → bevorzugt `pack()` statt blindem `setSize()`.
* **Direkt in `JFrame` wild adden** → lieber ein zentrales `JPanel root` als ContentPane verwenden.

  Erklärung:
  Technisch kann man Komponenten direkt mit `frame.add(...)` zum `JFrame` hinzufügen. Intern werden diese zur `ContentPane` des Frames hinzugefügt. Problematisch wird es, wenn man später Layouts wechselt, verschachtelte Panels braucht oder das UI erweitert.

  ❌ Weniger sauber:

  ```java
  JFrame frame = new JFrame();
  frame.setLayout(new BorderLayout());
  frame.add(new JButton("A"), BorderLayout.NORTH);
  frame.add(new JButton("B"), BorderLayout.CENTER);
  ```

  ✅ Besser strukturiert:

  ```java
  JFrame frame = new JFrame();

  JPanel root = new JPanel(new BorderLayout());
  root.add(new JButton("A"), BorderLayout.NORTH);
  root.add(new JButton("B"), BorderLayout.CENTER);

  frame.setContentPane(root);
  ```

  Vorteile der `root`-Panel-Variante:

    * klare Struktur (ein zentrales Hauptpanel)
    * bessere Erweiterbarkeit (z. B. verschachtelte Panels)
    * leichter austauschbar (z. B. bei CardLayout)
    * sauberer MVC-/UI-Aufbau

  Faustregel:
  Man behandelt `JFrame` als Fenster-Container und baut die gesamte UI in einem oder mehreren `JPanel`-Containern auf.

---

## 6) Empfehlung (für 90% der Aufgaben)

* Layouts: **BorderLayout, FlowLayout, GridLayout, BoxLayout, GridBagLayout, CardLayout**
* Events: **ActionListener**, plus bei Bedarf: **DocumentListener**, **ListSelectionListener**
* Misc: **JScrollPane**, **JOptionPane**, **Timer**, **SwingWorker**, **revalidate/repaint**
