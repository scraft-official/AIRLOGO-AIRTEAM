package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;

public class CMD_LT {
	private static int argsCount = 1;
	public static String syntax = "LT <STOPNIE>";
	public static String description = "OBRACA ŻÓŁWIA O <STOPNIE> W LEWO";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		for(int id : Turtles_Workspace_Area.getSelectedTurtlesID()) {
			if(args[1] == null) {
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
			
			int stopnie = 0;
			try {
				stopnie = CommandManager.parseMath(args[1], id);
			} catch (Exception e) { return; }
		
			Turtles_Workspace_Area.getTurtle(id).rotate(-stopnie);
		}
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			Turtles_Workspace_Area.forceRefresh(true, true);
		}
	}
}
