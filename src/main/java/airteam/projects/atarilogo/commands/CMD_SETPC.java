package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.NTSCUtilies;

public class CMD_SETPC {
private static int argsCount = 2;
	
	private static String syntax = "SETPC <NUMER PISAKA> <NUMER KOLORU>";
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
		
		int colorNumber;
		try {
			colorNumber = Integer.valueOf(args[2]);
		} catch (Exception e) { 
			Console_Output.addErrorLog("MOŻESZ WYBIERAĆ KOLORY W PRZEDZIALE (0 -> 127)");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
	
		Turtles_Workspace_Area.setPenColor(penNumber, NTSCUtilies.getAtariColorFromNumber(colorNumber));
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			Turtles_Workspace_Area.forceRefresh(true, true);
		}
	}
	
}
