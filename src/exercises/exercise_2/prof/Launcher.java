package exercises.exercise_2.prof;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Launcher {

	private static Map<String, Parameter> p;
	static {
		 p = new HashMap<>();
		 
		 Parameter p1 = new Parameter(Integer.class, "a", "alpha", "Example parameter 1");
		 p.put(p1.getShortName(), p1);
		 p.put(p1.getLongName(), p1);
		 
		 Parameter p2 = new Parameter(Float.class, "b", "beta", "Example parameter 2");
		 p.put(p2.getShortName(), p2);
		 p.put(p2.getLongName(), p2);
		 
		 Parameter p3 = new Parameter(String.class, "g", "gamma", "Example parameter 3");
		 p.put(p3.getShortName(), p3);
		 p.put(p3.getLongName(), p3);
		 
		 Parameter p4 = new Parameter(String.class, "h", "help", "Print help menu");
		 p.put(p4.getShortName(), p4);
		 p.put(p4.getLongName(), p4);
	}
	
	private static boolean readParameters(String[] args) {
		boolean readParam = false;
		String paramName = null;
		for (String arg : args) {
			if (arg.startsWith("--") || arg.startsWith("-")) {			
				if (readParam) {
					Parameter param = p.get(paramName);
					if (param.getClazz() != String.class) {
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
				if (param.getClazz() == String.class) {
					param.setValue("");
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
		
		Parameter helpParameter = p.get("help");
		if (helpParameter.hasValue()) {
			String value = (String) helpParameter.getValue();
			
			if (!p.containsKey(value) && !value.equals("")) {
				StringBuilder sb = new StringBuilder("Parameter ");
				sb.append(value);
				sb.append(" not found!");
				
				System.err.println(sb);
				
				System.exit(2);
			}
			
			
			printHelp(p.get(value));
			
			System.exit(0);
		}
		
		printParamValues();
	}

	private static void printHelp(Parameter param) {
		if (param == null) {
			List<Parameter> seen = new ArrayList<>();
			for (Entry<String, Parameter> entry : p.entrySet()) {
				if (seen.contains(entry.getValue())) {
					continue;
				}
				
				seen.add(entry.getValue());
				printHelp(entry.getValue());
			}
		} else {
			StringBuilder sb = new StringBuilder();
			
			sb.append("--");
			sb.append(param.getLongName());
			sb.append('\t');
			
			sb.append("-");
			sb.append(param.getShortName());
			sb.append('\t');
			
			sb.append(param.getDesc());
			
			System.out.println(sb.toString());
		}
	}
}
