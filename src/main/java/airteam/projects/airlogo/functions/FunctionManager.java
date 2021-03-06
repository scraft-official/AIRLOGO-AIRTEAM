package airteam.projects.airlogo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import airteam.projects.airlogo.commands.CommandManager;
import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtleFunctionsPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;

public class FunctionManager {
	private static HashMap<String, TurtleFunction> registeredFunctions = new HashMap<>();
	
	public static void addFunction(String name, ArrayList<String> args, String commands) {
		registeredFunctions.put(name, new TurtleFunction(args, commands, false));
		TurtleFunctionsPanel.setSelectedFunction(name, true);
	}
	
	public static void addFunction(String name, ArrayList<String> args, String commands, boolean isDefaultFunction) {
		registeredFunctions.put(name, new TurtleFunction(args, commands, isDefaultFunction));
		TurtleFunctionsPanel.setSelectedFunction(name, false);
	}
	
	public static boolean existFunction(String name) {
		return (registeredFunctions.get(name) != null);
	}
	
	public static HashMap<String, TurtleFunction> getAllFunctions() {
		return registeredFunctions;
	}
	
	public static TurtleFunction getFunction(String name) {
		return registeredFunctions.get(name);
	}
	
	public static void parseFunction(String name, String[] args) throws Exception {
		TurtleFunction function = registeredFunctions.get(name);
		if(function.args.size() > args.length - 1) { 
			throw new CommandManager.CommandException("PRAWIDLOWE UZYCIE FUNKCJI: " + name + " <" + String.join("> <", function.args) + ">", "WPROWADZONO NIEWYSTARCZAJACO ARGUMENTOW!"); 
		}
		
		String commands = function.commands;
		commands = commands.replace("\t", " ").replace("\n", "");
		while(commands.contains("  ")) {
			commands = commands.replaceAll("  ", " ");
		}
		
		if(commands.charAt(0) == ' ') {
			commands = commands.substring(1);
		}
		
		if(function.args.size() > 0) {
			for(int i = 0; i < function.args.size(); i++) {
				commands = commands.replaceAll(function.args.get(i), args[i+1]);
			}
		}
			
		String[] finalCommands = Stream.concat(Arrays.stream(commands.split(" ")), Arrays.stream(Arrays.copyOfRange(args, function.args.size() + 1, args.length))).toArray(String[]::new);
		CommandManager.parse(finalCommands);
		
	}
	
	public static void registerDefaultFunctions() {
		ArrayList<String> args = new ArrayList<>();
		args.add(":WIELKOSC");
		FunctionManager.addFunction("KWADRAT", args, "\tREPEAT 4 [FD :WIELKOSC RT 90]", true);
		
		args = new ArrayList<>();
		args.add(":WIELKOSC");
		FunctionManager.addFunction("OKRAG", args, "\tREPEAT 36 [FD :WIELKOSC RT 10]", true);
		
		args = new ArrayList<>();
		args.add(":ILOSCBOKOW");
		args.add(":DLUGOSCBOKU");
		FunctionManager.addFunction("WIELOKAT", args, "\tREPEAT :ILOSCBOKOW [FD :DLUGOSCBOKU RT 360/:ILOSCBOKOW]", true);
		
	}
	
	public static void removeFunction(String name) {
		registeredFunctions.remove(name);
		String selected = null;
		for(String n : registeredFunctions.keySet()) {
			selected = n;
		}
		TurtleFunctionsPanel.setSelectedFunction(selected, true);
	}

	public static void setFunctionsList(HashMap<String, TurtleFunction> f) {
		registeredFunctions = f;
	}
	
	public static class TurtleFunction {
		public ArrayList<String> args;
		public String commands;
		public boolean isDefaultFunction;
		
		public TurtleFunction(ArrayList<String> args, String commands, boolean isDefaultFunction) {
			this.args = args;
			this.commands = commands;
			this.isDefaultFunction = isDefaultFunction;
		}
	}
}
