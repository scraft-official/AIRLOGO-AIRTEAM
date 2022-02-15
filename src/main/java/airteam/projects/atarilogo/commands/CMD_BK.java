package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.turtle.Turtle;

public class CMD_BK {
	private static int argsCount = 1;
	
	private static String syntax = "BK <DYSTANS>";
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDLOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJACO ARGUMENTOW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		for(int id : Turtles_Workspace_Area.getSelectedTurtlesID()) {
			if(args[1] == null) {
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
			
			int distance = 0;
			try {
				distance = CommandManager.parseMath(args[1], id);
			} catch (Exception e) { return; }
			
				Turtles_Workspace_Area.getTurtle(id).move(-distance);
		}
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			Turtles_Workspace_Area.forceRefresh(true, true);
		}
	}
	
}
