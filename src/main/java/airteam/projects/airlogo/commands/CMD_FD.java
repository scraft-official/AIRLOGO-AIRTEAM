package airteam.projects.airlogo.commands;

import java.util.Arrays;

import airteam.projects.airlogo.commands.CommandManager.CommandException;
import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;

public class CMD_FD {
private static int argsCount = 1;
	
	public static String syntax = "FD <DYSTANS>";
	public static String description = "PORUSZA ŻÓŁWIA DO PRZODU O WSKAZANY DYSTANS";
	
	public static void execute(String[] args) throws Exception {
		if(args.length < argsCount + 1) {
			throw new CommandManager.CommandException("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
		}
		for(int id : TurtlesWorkspacePanel.getSelectedTurtlesID()) {
			if(args[1] == null) {
				TurtlesWorkspacePanel.forceRefresh(true, true);
				return;
			}
			
			int distance;
			try {
				distance = CommandManager.parseMath(args[1], id);
			} catch (Exception e) { return; }
		
			TurtlesWorkspacePanel.getTurtle(id).move(distance);
		}
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
	
}
