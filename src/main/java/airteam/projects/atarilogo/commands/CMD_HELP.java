package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;

public class CMD_HELP {
private static int argsCount = 0;
	
	public static String syntax = "HELP";
	public static String description = "WYŚWIETLA LISTĘ WSZYSTKICH KOMEND";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		
		//TODO WYPSIAC WSZYSTKIE komendy!
		Console_Output.addInfoLog("==============================================");
		Console_Output.addInfoLog(" - " + CMD_HELP.syntax + " - " + CMD_HELP.description);
		Console_Output.addInfoLog(" - " + CMD_ASK.syntax + " - " + CMD_ASK.description);
		Console_Output.addInfoLog(" - " + CMD_ED.syntax + " - " + CMD_ED.description);
		Console_Output.addInfoLog(" - " + CMD_ERALL.syntax + " - " + CMD_ERALL.description);
		Console_Output.addInfoLog(" - TO <NAZWA PROCEDURY> - TWORZY PROCEDURE O PODANEJ NAZWIE");
		Console_Output.addInfoLog(" - " + CMD_POTS.syntax + " - " + CMD_POTS.description);
		Console_Output.addInfoLog(" - " + CMD_EACH.syntax + " - " + CMD_EACH.description);
		Console_Output.addInfoLog(" - " + CMD_REPEAT.syntax + " - " + CMD_REPEAT.description);
		Console_Output.addInfoLog(" - " + CMD_SETPC.syntax + " - " + CMD_SETPC.description);
		Console_Output.addInfoLog(" - " + CMD_SETPN.syntax + " - " + CMD_SETPN.description);
		Console_Output.addInfoLog(" - " + CMD_SETC.syntax + " - " + CMD_SETC.description);
		Console_Output.addInfoLog(" - " + CMD_TELL.syntax + " - " + CMD_TELL.description);
		Console_Output.addInfoLog(" - " + CMD_ST.syntax + " - " + CMD_ST.description);
		Console_Output.addInfoLog(" - " + CMD_HT.syntax + " - " + CMD_HT.description);
		Console_Output.addInfoLog(" - " + CMD_PD.syntax + " - " + CMD_PD.description);
		Console_Output.addInfoLog(" - " + CMD_PU.syntax + " - " + CMD_PU.description);
		Console_Output.addInfoLog(" - " + CMD_CS.syntax + " - " + CMD_CS.description);
		Console_Output.addInfoLog(" - " + CMD_RT.syntax + " - " + CMD_RT.description);
		Console_Output.addInfoLog(" - " + CMD_LT.syntax + " - " + CMD_LT.description);
		Console_Output.addInfoLog(" - " + CMD_BK.syntax + " - " + CMD_BK.description);
		Console_Output.addInfoLog(" - " + CMD_FD.syntax + " - " + CMD_FD.description);
		
		Console_Output.addInfoLog("LISTA DOSTĘPNYCH KOMEND:");
		Console_Output.addInfoLog("==============================================");
		
		
		
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			Turtles_Workspace_Area.forceRefresh(true, true);
		}
	}
	
}
