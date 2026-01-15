package cli.exercises.exercise_1.prof;

public class Parameter {

	private Class clazz;
	
	private String shortName;
	private String longName;

	private Object value;

	public Parameter(Class clazz, String shortName, String longName) {
		this.clazz = clazz;
		this.shortName = shortName;
		this.longName = longName;
	}
	
	public Class getClazz() {
		return clazz;
	}

	public String getShortName() {
		return shortName;
	}

	public String getLongName() {
		return longName;
	}
	
	public boolean hasValue() {
		return value != null;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
