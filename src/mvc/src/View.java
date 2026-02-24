package mvc.src;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class View extends Frame {

	public final static String TITLE = "MVC Example";
	
	private static final long serialVersionUID = 1L;

	private ResourceBundle rb;
	private Controller controller;
	
	private TextField tfName;

	private Button btn;
	
	public View(ResourceBundle rb, Controller controller) {
		super(View.TITLE);
		
		this.setRb(rb);
		this.controller = controller;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		addComponents();
		
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public View() {
		this(null, null);
	}
	
	public ResourceBundle getRb() {
		return rb;
	}

	public void setRb(ResourceBundle rb) {
		if (rb == null) {
			return;
		}
		
		this.rb = rb;
		
		if (btn == null) {
			return;
		}
		
		this.btn.setLabel(rb.getString("btnSave"));
	}

	public TextField getTfName() {
		return tfName;
	}

	public void setTfName(TextField tfName) {
		this.tfName = tfName;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	private void addComponents() {
		Panel panel = new Panel();
		
		panel.add(new Label("Name:"));
		
		tfName = new TextField(20);
		panel.add(tfName);
		
		btn = new Button("Speichern");
		btn.addActionListener(controller);		
		panel.add(btn);
		
		add(panel);
	}
	
}
