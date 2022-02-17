package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.utilities.NTSCUtilies;

public class CMD_SETC {
private static int argsCount = 1;
	
	public static String syntax = "SETC <NUMER KOLORU>";
	public static String description = "USTAWIA KOLOR ŻÓŁWIA NA PODANY KOLOR (0 -> 127)";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		for(int id : Turtles_Workspace_Area.getSelectedTurtlesID()) {
			if(args[1] == null) {
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
			
			int colorNumber;
			try {
				colorNumber = CommandManager.parseMath(args[1], id);
			} catch (Exception e) {
				Console_Output.addErrorLog("MOŻESZ WYBIERAĆ KOLORY W PRZEDZIALE (0 -> 127)");
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
		
			Turtles_Workspace_Area.getTurtle(id).setTurtleColor(NTSCUtilies.getAtariColorFromNumber(colorNumber));
		}
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			Turtles_Workspace_Area.forceRefresh(true, true);
		}
	}
	
}
