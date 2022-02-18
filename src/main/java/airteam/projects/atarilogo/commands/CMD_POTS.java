package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.ConsoleOutputPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;
import airteam.projects.atarilogo.functions.FunctionManager;
import airteam.projects.atarilogo.functions.FunctionManager.TurtleFunction;


public class CMD_POTS {
	private static int argsCount = 0;
	
	public static String syntax = "POTS";
	public static String description = "WYŚWIETLA LISTĘ ZAREJESTOWANYCH PROCEDUR";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		
		ConsoleOutputPanel.addInfoLog("==============================================");
		for(String name : FunctionManager.getAllFunctions().keySet()) {
			TurtleFunction function = FunctionManager.getFunction(name);
			if(function.args.size() > 0) ConsoleOutputPanel.addInfoLog(" - " + name + " <" + String.join("> <", function.args) + ">");
			else ConsoleOutputPanel.addInfoLog(" - " + name);
		}
		ConsoleOutputPanel.addInfoLog("LISTA DOSTĘPNYCH PROCEDUR:");
		ConsoleOutputPanel.addInfoLog("==============================================");
		
		
		
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
	
}
