package cli.exercises.exercise_2.prof;

public class Parameter {

	private Class clazz;
	
	private String shortName;
	private String longName;
	private String desc;

	private Object value;

	public Parameter(Class clazz, String shortName, String longName, String desc) {
		this.clazz = clazz;
		this.shortName = shortName;
		this.longName = longName;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}
}
