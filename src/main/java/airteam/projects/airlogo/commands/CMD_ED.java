package airteam.projects.airlogo.commands;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.components.dialogs.popups.EditFunctionPopup;
import airteam.projects.airlogo.functions.FunctionManager;

public class CMD_ED {
private static int argsCount = 1;
	
	public static String syntax = "ED <NAZWA PROCEDURY>";
	public static String description = "ROZPOCZYNA EDYCJĘ WSKAZANEJ PROCEDURY";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		if(FunctionManager.existFunction(args[1]) && FunctionManager.getFunction(args[1]).isDefaultFunction) {
			ConsoleOutputPanel.addErrorLog("NIE MOŻESZ EDYTOWAĆ WBUDOWANYCH PROCEDUR!");
			return;
		}
		
		if(FunctionManager.existFunction(args[1])) {
			new EditFunctionPopup(args[1]);
		} else { ConsoleOutputPanel.addErrorLog("DODAJ NOWĄ PROCEDURĘ UŻYWAJĄĆ \"TO <NAZWAFUNKCJI>\"", "TA PROCEDURA NIE ISTNIEJE!"); }
	}
	
}
