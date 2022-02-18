package airteam.projects.airlogo.commands;

import java.util.Arrays;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.utilities.NTSCUtility;

public class CMD_SETC {
private static int argsCount = 1;
	
	public static String syntax = "SETC <NUMER KOLORU>";
	public static String description = "USTAWIA KOLOR ŻÓŁWIA NA PODANY KOLOR (0 -> 127)";
	
	public static void execute(String[] args) {
		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDŁOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		for(int id : TurtlesWorkspacePanel.getSelectedTurtlesID()) {
			if(args[1] == null) {
				TurtlesWorkspacePanel.forceRefresh(true, true);
				return;
			}
			
			int colorNumber;
			try {
				colorNumber = CommandManager.parseMath(args[1], id);
			} catch (Exception e) {
				ConsoleOutputPanel.addErrorLog("MOŻESZ WYBIERAĆ KOLORY W PRZEDZIALE (0 -> 127)");
				TurtlesWorkspacePanel.forceRefresh(true, true);
				return;
			}
		
			TurtlesWorkspacePanel.getTurtle(id).setTurtleColor(NTSCUtility.getAtariColorFromNumber(colorNumber));
		}
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
	
}
