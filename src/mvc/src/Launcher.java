package mvc.src;

import java.util.Locale;
import java.util.ResourceBundle;

public class Launcher {

	public static void main(String[] args) {
		
		Locale l = new Locale.Builder()
		.setLanguage("en")
		.setRegion("GB")
		.build();
		ResourceBundle rb = ResourceBundle.getBundle("Messages", l);
		
		
		Model m = new Model();
		Controller c = new Controller();
		View v = new View(rb, c);
		
		c.setModel(m);
		c.setView(v);
		
		v.setRb(rb);
	}

}
