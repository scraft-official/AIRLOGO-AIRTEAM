package airteam.projects.atarilogo.commands;

import airteam.projects.atarilogo.components.ConsoleOutputPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;
import airteam.projects.atarilogo.components.dialogs.popups.EditFunctionPopup;
import airteam.projects.atarilogo.functions.FunctionManager;

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
