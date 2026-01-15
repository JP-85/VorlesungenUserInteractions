package cli.exercises.exercise_1.prof;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Launcher {

	private static Map<String, Parameter> p;
	static {
		 p = new HashMap<>();
		 
		 Parameter p1 = new Parameter(Integer.class, "a", "alpha");
		 p.put(p1.getShortName(), p1);
		 p.put(p1.getLongName(), p1);
		 
		 Parameter p2 = new Parameter(Float.class, "b", "beta");
		 p.put(p2.getShortName(), p2);
		 p.put(p2.getLongName(), p2);
		 
		 Parameter p3 = new Parameter(String.class, "g", "gamma");
		 p.put(p3.getShortName(), p3);
		 p.put(p3.getLongName(), p3);
	}
	
	private static boolean readParameters(String[] args) {
		boolean readParam = false;
		String paramName = null;
		for (String arg : args) {
			if (arg.startsWith("--") || arg.startsWith("-")) {			
				if (readParam) {
					Parameter param = p.get(paramName);
					if (param.getClazz() == String.class) {
						param.setValue("");
					} else {
						StringBuilder sb = new StringBuilder("Cannot process parameter ");
						sb.append(arg);
						sb.append(" since it follows another parameter name.");
					
						System.err.println(sb.toString());
					
						return false;
					}
				}
				
				if (arg.startsWith("--")) {
					// Long
					paramName = arg.replaceFirst("--", "");
				} else {
					// Short
					paramName = arg.replaceFirst("-", "");
				}
				
				readParam = true;
			
				
			} else {
				// Value
				
				if (!readParam) {
					StringBuilder sb = new StringBuilder("Cannot process value ");
					sb.append(arg);
					sb.append(" since it is not preceeded by a parameter name.");
					
					System.err.println(sb.toString());
					
					return false;
				}
				
				if (!p.containsKey(paramName)) {
					StringBuilder sb = new StringBuilder("Cannot process value ");
					sb.append(arg);
					sb.append(" since the supplied parameter name ");
					sb.append(paramName);
					sb.append(" is unkown.");
					
					System.err.println(sb.toString());
					
					return false;
				}
				
				Parameter param = p.get(paramName);
				try {
					if (param.getClazz() == String.class) {
						param.setValue(arg);
					} else if (param.getClazz() == Integer.class) {
						param.setValue(Integer.valueOf(arg));
					} else if (param.getClazz() == Float.class) {
						param.setValue(Float.valueOf(arg));
					} else {
						StringBuilder sb = new StringBuilder("Cannot process value ");
						sb.append(arg);
						sb.append(" since the type of the supplied parameter name ");
						sb.append(paramName);
						sb.append(" (");
						sb.append(param.getClazz().getName());
						sb.append(")");
						sb.append(" is not supported.");
						
						System.err.println(sb.toString());
						
						return false;
					}
				} catch (Exception e) {
					StringBuilder sb = new StringBuilder("Cannot process value ");
					sb.append(arg);
					sb.append(" since casting the value into the type of the supplied parameter name ");
					sb.append(paramName);
					sb.append(" (");
					sb.append(param.getClazz().getName());
					sb.append(")");
					sb.append(" failed.");
					
					System.err.println(sb.toString());
					
					return false;
				}
				
				readParam = false;
			}
		}
		
		return true;
	}
	
	private static void printParamValues() {
		List<Parameter> seen = new ArrayList<>();
		for (Entry<String, Parameter> param : p.entrySet()) {
			if (!param.getValue().hasValue() || seen.contains(param.getValue())) {
				continue;
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("--");
			sb.append(param.getValue().getLongName());
			sb.append('\t');
			
			sb.append("-");
			sb.append(param.getValue().getShortName());
			sb.append('\t');
			
			sb.append(param.getValue().getValue());
			
			System.out.println(sb.toString());
			
			seen.add(param.getValue());
		}
		
	}
	
	public static void main(String[] args) {
		if (!readParameters(args)) {
			System.exit(1);
		}
		
		printParamValues();
	}
}
