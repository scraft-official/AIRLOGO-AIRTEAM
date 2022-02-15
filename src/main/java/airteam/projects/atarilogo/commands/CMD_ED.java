package airteam.projects.atarilogo.commands;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.components.dialogs.popups.EditFunctionPopup;
import airteam.projects.atarilogo.functions.FunctionManager;

public class CMD_ED {
private static int argsCount = 1;
	
	private static String syntax = "ED <NAZWA FUNKCJI>";
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDLOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJACO ARGUMENTOW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(FunctionManager.existFunction(args[1]) && FunctionManager.getFunction(args[1]).isDefaultFunction) {
			Console_Output.addErrorLog("NIE MOZESZ EDYTOWAC WBUDOWANYCH FUNKCJI!");
			return;
		}
		
		if(FunctionManager.existFunction(args[1])) {
			new EditFunctionPopup(args[1]);
		} else { Console_Output.addErrorLog("DODAJ NOWĄ FUNKCJĘ UŻYWAJĄĆ \"TO <NAZWAFUNKCJI>\"", "TA FUNKCJA NIE ISTNIEJE!"); }
	}
	
}
