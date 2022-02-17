package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;

public class CMD_SETPN {
private static int argsCount = 1;
	
	public static String syntax = "SETPN <NUMER PISAKA>";
	public static String description = "USTAWIA PISAK ŻÓŁWIA NA WSKAZANY (0 -> 2)";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		int penNumber;
		try {
			penNumber = Integer.valueOf(args[1]);
		} catch (Exception e) { 
			Console_Output.addErrorLog("MOŻESZ WYBIERAĆ TYLKO MIĘDZY PISAKAMI (0, 1, 2)");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		
		
		if(penNumber > 2 || penNumber < 0) { 
			Console_Output.addErrorLog("MOŻESZ WYBIERAĆ TYLKO MIĘDZY PISAKAMI (0, 1, 2)");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
	
		Turtles_Workspace_Area.selectPenID(penNumber);
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			Turtles_Workspace_Area.forceRefresh(true, true);
		}
	}
	
}
