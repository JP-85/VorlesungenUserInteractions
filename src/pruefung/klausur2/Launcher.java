package pruefung.klausur2;

public class Launcher {
    public static void main(String[] args) {
        View v = new View();
        Model m = new Model();
        Controller c = new Controller();

        c.initialise(m, v);
        m.initialise(0, "m");
        v.initialize(m, c);
    }
}
