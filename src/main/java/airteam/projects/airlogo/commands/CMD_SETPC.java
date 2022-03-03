package airteam.projects.airlogo.commands;

import java.util.Arrays;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.utilities.NTSCUtility;

public class CMD_SETPC {
private static int argsCount = 2;
	
	public static String syntax = "SETPC <NUMER PISAKA> <NUMER KOLORU>";
	public static String description = "USTAWIA KOLOR WSKAZANEGO PISAKA NA PODANY KOLOR (0 -> 127)";
	
	public static void execute(String[] args) throws Exception {
		if(args.length < argsCount + 1) {
			throw new CommandManager.CommandException("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
		}
		
		int penNumber;
		try {
			penNumber = Integer.valueOf(args[1]);
		} catch (Exception e) { 
			throw new CommandManager.CommandException("MOŻESZ WYBIERAĆ TYLKO MIĘDZY PISAKAMI (0, 1, 2)");
		}
		
		if(penNumber > 2 || penNumber < 0) { 
			throw new CommandManager.CommandException("MOŻESZ WYBIERAĆ TYLKO MIĘDZY PISAKAMI (0, 1, 2)");
		}
		
		int colorNumber;
		try {
			colorNumber = Integer.valueOf(args[2]);
		} catch (Exception e) { 
			throw new CommandManager.CommandException("MOŻESZ WYBIERAĆ KOLORY W PRZEDZIALE (0 -> 127)");
		}
		
		if(colorNumber > 127 || colorNumber < 0) {
			throw new CommandManager.CommandException("MOŻESZ WYBIERAĆ KOLORY W PRZEDZIALE (0 -> 127)");
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
