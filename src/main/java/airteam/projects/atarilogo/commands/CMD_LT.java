package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.turtle.Turtle;

public class CMD_LT {
	private static int argsCount = 1;
	private static String syntax = "LT <STOPNIE>";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDLOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJACO ARGUMENTOW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		for(int id : Turtles_Workspace_Area.getSelectedTurtlesID()) {
			args[1] = CommandManager.parseMath(args[1], id);
			if(args[1] == null) {
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
			
			int stopnie = 0;
			try {
				stopnie = Integer.valueOf(args[1]);
			} catch(Exception e) {
				Console_Output.addErrorLog("WPROWADZONO NIEPRAWIDLOWY WARTOSC! ( " + args[0] + " " + args[1] + " != LICZBA )");
			}
		
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
