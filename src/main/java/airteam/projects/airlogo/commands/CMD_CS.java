package airteam.projects.airlogo.commands;

import java.util.Arrays;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;

public class CMD_CS {
	private static int argsCount = 0;
	
	public static String syntax = "CS";
	public static String description = "CZYŚCI CAŁĄ PLANSZĘ I USTAWIA ŻÓŁWIE W POZYCJI STARTOWEJ";
	
	public static void execute(String[] args) throws Exception {
		if(args.length < argsCount + 1) {
			throw new CommandManager.CommandException("PRAWIDŁOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
		}
		
		TurtlesWorkspacePanel.clearWorkspace();
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
}
