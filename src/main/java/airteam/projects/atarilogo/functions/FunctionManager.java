package airteam.projects.atarilogo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import airteam.projects.atarilogo.commands.CommandManager;
import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;

public class FunctionManager {
	private static HashMap<String, TurtleFunction> registeredFunctions = new HashMap<>();
	
	public static void parseFunction(String name, String[] args) {
		TurtleFunction function = registeredFunctions.get(name);
		if(function.args.size() > args.length + 1) { 
			Console_Output.addErrorLog("PRAWIDLOWE UZYCIE FUNKCJI: " + name + String.join(" ", function.args), "WPROWADZONO NIEWYSTARCZAJACO ARGUMENTOW!"); 
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		String commands = null;
		if(function.args.size() > 0) {
			int i = 0;
			for(String arg : function.args) {
				i++;
				commands = function.commands;
				commands.replaceAll(arg, args[i]);
			}
		} else commands = function.commands; 
			
		String[] finalCommands = Stream.concat(Arrays.stream(commands.split(" ")), Arrays.stream(Arrays.copyOfRange(args, function.args.size() + 1, args.length))).toArray(String[]::new);
		CommandManager.parse(finalCommands);
		
	}
	
	public static void addFunction(String name, ArrayList<String> args, String commands) {
		registeredFunctions.put(name, new TurtleFunction(args, commands));
	}
	
	public static void removeFunction(String name) {
		registeredFunctions.remove(name);
	}
	
	public static TurtleFunction getFunction(String name) {
		return registeredFunctions.get(name);
	}
	
	public static HashMap<String, TurtleFunction> getAllFunctions() {
		return registeredFunctions;
	}
	
	public static boolean existFunction(String name) {
		return (registeredFunctions.get(name) != null);
	}

	public static class TurtleFunction {
		public ArrayList<String> args;
		public String commands;
		
		TurtleFunction(ArrayList<String> args, String commands) {
			this.args = args;
			this.commands = commands;
		}
	}
}
