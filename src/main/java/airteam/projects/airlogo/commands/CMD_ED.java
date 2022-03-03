package airteam.projects.airlogo.commands;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.components.dialogs.popups.EditFunctionPopup;
import airteam.projects.airlogo.functions.FunctionManager;

public class CMD_ED {
private static int argsCount = 1;
	
	public static String syntax = "ED <NAZWA PROCEDURY>";
	public static String description = "ROZPOCZYNA EDYCJĘ WSKAZANEJ PROCEDURY";
	
	public static void execute(String[] args) throws Exception {
		if(args.length < argsCount + 1) {
			throw new CommandManager.CommandException("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
		}
		
		if(FunctionManager.existFunction(args[1]) && FunctionManager.getFunction(args[1]).isDefaultFunction) {
			throw new CommandManager.CommandException("NIE MOŻESZ EDYTOWAĆ WBUDOWANYCH PROCEDUR!");
		}
		
		if(FunctionManager.existFunction(args[1])) {
			new EditFunctionPopup(args[1]);
		} else { throw new CommandManager.CommandException("DODAJ NOWĄ PROCEDURĘ UŻYWAJĄĆ \"TO <NAZWAFUNKCJI>\"", "TA PROCEDURA NIE ISTNIEJE!"); }
	}
	
}
