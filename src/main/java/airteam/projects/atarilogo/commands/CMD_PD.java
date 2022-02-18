package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.ConsoleOutputPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;
import airteam.projects.atarilogo.turtle.Turtle;

public class CMD_PD {
	private static int argsCount = 0;
	
	public static String syntax = "PD";
	public static String description = "OPUSZCZA PISAK";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		for(Turtle t : TurtlesWorkspacePanel.getSelectedTurtles()) {
			t.setPenVisibility(true);
		}
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
	
}
