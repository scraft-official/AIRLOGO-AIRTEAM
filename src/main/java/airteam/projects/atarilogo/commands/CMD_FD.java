package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;

public class CMD_FD {
private static int argsCount = 1;
	
	private static String syntax = "FD <DYSTANS>";
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDLOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJACO ARGUMENTOW!");
			Turtles_Workspace_Area.forceRefresh(true);
			return;
		}
		
		int distance = 0;
		try {
			distance = Integer.valueOf(args[1]);
		} catch(Exception e) {
			Console_Output.addErrorLog("WPROWADZONO NIEPRAWIDLOWA WARTOSC! ( " + args[0] + " " + args[1] + " != LICZBA )");
		}
		
		Turtles_Workspace_Area.getSelectedTurtle().move(distance);
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			Turtles_Workspace_Area.forceRefresh(true);
		}
	}
	
}
