package airteam.projects.airlogo.commands;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.components.dialogs.popups.DeleteFunctionPopup;
import airteam.projects.airlogo.functions.FunctionManager;

public class CMD_ERALL {
private static int argsCount = 1;
	
	public static String syntax = "ERALL <NAZWA PROCEDURY>";
	public static String description = "USUWA WSKAZANĄ PROCEDURĘ";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		if(FunctionManager.existFunction(args[1]) && FunctionManager.getFunction(args[1]).isDefaultFunction) {
			ConsoleOutputPanel.addErrorLog("NIE MOŻESZ USUWAĆ WBUDOWANYCH PROCEDUR!");
			return;
		}
		
		if(FunctionManager.existFunction(args[1])) {
			new DeleteFunctionPopup(args[1]);
		} else { ConsoleOutputPanel.addErrorLog("DODAJ NOWĄ FUNKCJĘ UŻYWAJĄĆ \"TO <NAZWAFUNKCJI>\"", "TA FUNKCJA NIE ISTNIEJE!"); }
	}
	
}