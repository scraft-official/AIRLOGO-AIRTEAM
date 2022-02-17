package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.functions.FunctionManager;
import airteam.projects.atarilogo.functions.FunctionManager.TurtleFunction;


public class CMD_POTS {
	private static int argsCount = 0;
	
	public static String syntax = "POTS";
	public static String description = "WYŚWIETLA LISTĘ ZAREJESTOWANYCH PROCEDUR";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		
		Console_Output.addInfoLog("==============================================");
		for(String name : FunctionManager.getAllFunctions().keySet()) {
			TurtleFunction function = FunctionManager.getFunction(name);
			if(function.args.size() > 0) Console_Output.addInfoLog(" - " + name + " <" + String.join("> <", function.args) + ">");
			else Console_Output.addInfoLog(" - " + name);
		}
		Console_Output.addInfoLog("LISTA DOSTĘPNYCH PROCEDUR:");
		Console_Output.addInfoLog("==============================================");
		
		
		
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			Turtles_Workspace_Area.forceRefresh(true, true);
		}
	}
	
}
