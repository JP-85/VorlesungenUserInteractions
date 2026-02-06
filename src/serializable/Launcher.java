package serializable;

import java.io.*;

public class Launcher {
    static void main(String[] args) throws IOException {
        Student s = new Student("Max Mustermann", 1.0);

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(s.getName());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(s.getName());
            ObjectInputStream ois = new ObjectInputStream(fis);
            s = (Student) ois.readObject();
            ois.close();
            System.out.println(s.getName());
            System.out.println(s.getGpa());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
