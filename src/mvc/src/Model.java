package mvc.src;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PropertyChangeSupport pcs;
	
	private int counter;
	private String name;

	public Model() {
		this.pcs = new PropertyChangeSupport(this);
		this.counter = 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		this.pcs.firePropertyChange("name", oldName, this.name);
		
		this.setCounter(this.counter + 1);
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		int oldCounter = this.counter;
		this.counter = counter;
		this.pcs.firePropertyChange("counter", oldCounter, this.counter);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		this.pcs.addPropertyChangeListener(l);
	}
}
