package pruefung.klausur2;

import java.text.DecimalFormat;

public class Model {
	private double meter;
	private double inch;
	private double foot;
	private double yard;
	
	private String unit;
	private StringBuilder builder;

	public void initialise(double value, String unit) {
		this.unit = unit;
		this.builder = new StringBuilder();
		
		calculate(value);
	}
	
	public double getMeter() {
		return meter;
	}

	public void setMeter(double meter) {
		this.meter = meter;
		inch = 1. / 0.0254 * meter;
		foot = 1. / 0.3048 * meter;
		yard = 1. / 0.9144 * meter;
	}

	public double getInch() {
		return inch;
	}

	public void setInch(double inch) {
		this.inch = inch;
		
		meter = 0.0254 * inch;
		foot = 1. / 12. * inch;
		yard = 1. / 36. * inch;
	}

	public double getFoot() {
		return foot;
	}

	public void setFoot(double foot) {
		this.foot = foot;
		meter = 0.3048 * foot;
		inch = 12 * foot;
		yard = 1. / 3. * foot;
	}

	public double getYard() {
		return yard;
	}

	public void setYard(double yard) {
		this.yard = yard;
		meter = 0.9144 * yard;
		inch = 36 * yard;
		foot = 3 * yard;
	}

	public void setUnit(String unit) {
		double value = 0;
		
		switch (this.unit) {
			case "m":
				value = getMeter();
				break;
			case "inch":
				value = getInch();
				break;				
			case "foot":
				value = getFoot();
				break;
			case "yard":
				value = getYard();
				break;
		}
		
		this.unit = unit;
		calculate(value);
	}

	public StringBuilder getStringBuilder() {
		return builder;
	}

	public void calculate(double value) {
		switch (unit) {
			case "m":
				setMeter(value);
				break;
			case "inch":
				setInch(value);
				break;				
			case "foot":
				setFoot(value);
				break;
			case "yard":
				setYard(value);
				break;
		}
	}
	
	public static String format(double value) {
		return new DecimalFormat("#.####").format(value);
	}
}
