package airteam.projects.atarilogo.commands;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.components.dialogs.popups.EditFunctionPopup;
import airteam.projects.atarilogo.functions.FunctionManager;

public class CMD_ED {
private static int argsCount = 1;
	
	public static String syntax = "ED <NAZWA PROCEDURY>";
	public static String description = "ROZPOCZYNA EDYCJĘ WSKAZANEJ PROCEDURY";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(FunctionManager.existFunction(args[1]) && FunctionManager.getFunction(args[1]).isDefaultFunction) {
			Console_Output.addErrorLog("NIE MOŻESZ EDYTOWAĆ WBUDOWANYCH PROCEDUR!");
			return;
		}
		
		if(FunctionManager.existFunction(args[1])) {
			new EditFunctionPopup(args[1]);
		} else { Console_Output.addErrorLog("DODAJ NOWĄ PROCEDURĘ UŻYWAJĄĆ \"TO <NAZWAFUNKCJI>\"", "TA PROCEDURA NIE ISTNIEJE!"); }
	}
	
}
