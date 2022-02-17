package airteam.projects.atarilogo.commands;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.components.dialogs.popups.DeleteFunctionPopup;
import airteam.projects.atarilogo.functions.FunctionManager;

public class CMD_ERALL {
private static int argsCount = 1;
	
	public static String syntax = "ERALL <NAZWA PROCEDURY>";
	public static String description = "USUWA WSKAZANĄ PROCEDURĘ";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(FunctionManager.existFunction(args[1]) && FunctionManager.getFunction(args[1]).isDefaultFunction) {
			Console_Output.addErrorLog("NIE MOŻESZ USUWAĆ WBUDOWANYCH PROCEDUR!");
			return;
		}
		
		if(FunctionManager.existFunction(args[1])) {
			new DeleteFunctionPopup(args[1]);
		} else { Console_Output.addErrorLog("DODAJ NOWĄ FUNKCJĘ UŻYWAJĄĆ \"TO <NAZWAFUNKCJI>\"", "TA FUNKCJA NIE ISTNIEJE!"); }
	}
	
}
