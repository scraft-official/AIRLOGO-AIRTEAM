package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.turtle.Turtle;

public class CMD_HELP {
private static int argsCount = 0;
	
	private static String syntax = "HELP";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		
		//TODO WYPSIAC WSZYSTKIE komendy!
		Console_Output.addInfoLog("==============================================");
		Console_Output.addInfoLog(" - " + CMD_BK.syntax + " - " + CMD_BK.description);
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