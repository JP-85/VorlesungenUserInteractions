package java_beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Student implements Serializable, PropertyChangeEventProducer {

	private String name = "";
	private String firstname = "";
	private int age = -1;
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public Student() {}

	public Student(String name, String firstname, int age) {
		this.name = name;
		this.firstname = firstname;
		this.age = age;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		this.pcs.addPropertyChangeListener(l);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		
		pcs.firePropertyChange("name", oldValue, this.name);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		String oldValue = this.firstname;
		this.firstname = firstname;
		
		pcs.firePropertyChange("firstname", oldValue, this.firstname);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		int oldValue = this.age;
		this.age = age;
		
		pcs.firePropertyChange("age", oldValue, this.age);
	}	
}
