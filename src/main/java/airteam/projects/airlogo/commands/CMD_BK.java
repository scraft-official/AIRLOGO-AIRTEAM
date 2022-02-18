package airteam.projects.airlogo.commands;

import java.util.Arrays;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;

public class CMD_BK {
	private static int argsCount = 1;
	
	public static String syntax = "BK <DYSTANS>";
	public static String description = "COFA ŻÓLWIA O PODANY <DYSTANS>";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		for(int id : TurtlesWorkspacePanel.getSelectedTurtlesID()) {
			if(args[1] == null) {
				TurtlesWorkspacePanel.forceRefresh(true, true);
				return;
			}
			
			int distance = 0;
			try {
				distance = CommandManager.parseMath(args[1], id);
			} catch (Exception e) { return; }
			
				TurtlesWorkspacePanel.getTurtle(id).move(-distance);
		}
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
	
}
