package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.ConsoleOutputPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;
import airteam.projects.atarilogo.utilities.NTSCUtility;

public class CMD_SETPC {
private static int argsCount = 2;
	
	public static String syntax = "SETPC <NUMER PISAKA> <NUMER KOLORU>";
	public static String description = "USTAWIA KOLOR WSKAZANEGO PISAKA NA PODANY KOLOR (0 -> 127)";
	
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
		
		int colorNumber;
		try {
			colorNumber = Integer.valueOf(args[2]);
		} catch (Exception e) { 
			ConsoleOutputPanel.addErrorLog("MOŻESZ WYBIERAĆ KOLORY W PRZEDZIALE (0 -> 127)");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
	
		TurtlesWorkspacePanel.setPenColor(penNumber, NTSCUtility.getAtariColorFromNumber(colorNumber));
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
	
}
