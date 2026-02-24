package java_beans;

public class Launcher {

	public static void main(String[] args) {
		Student s = new Student();
		
		new Editor(s);
		new Editor(s);
	}
}
