package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.ConsoleOutputPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;

public class CMD_SETPN {
private static int argsCount = 1;
	
	public static String syntax = "SETPN <NUMER PISAKA>";
	public static String description = "USTAWIA PISAK ŻÓŁWIA NA WSKAZANY (0 -> 2)";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDŁOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		int penNumber;
		try {
			penNumber = Integer.valueOf(args[1]);
		} catch (Exception e) { 
			ConsoleOutputPanel.addErrorLog("MOŻESZ WYBIERAĆ TYLKO MIĘDZY PISAKAMI (0, 1, 2)");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		
		
		if(penNumber > 2 || penNumber < 0) { 
			ConsoleOutputPanel.addErrorLog("MOŻESZ WYBIERAĆ TYLKO MIĘDZY PISAKAMI (0, 1, 2)");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
	
		TurtlesWorkspacePanel.selectPenID(penNumber);
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
	
}
