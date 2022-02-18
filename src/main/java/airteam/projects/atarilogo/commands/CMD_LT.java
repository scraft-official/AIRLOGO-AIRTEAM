package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.ConsoleOutputPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;

public class CMD_LT {
	private static int argsCount = 1;
	public static String syntax = "LT <STOPNIE>";
	public static String description = "OBRACA ŻÓŁWIA O <STOPNIE> W LEWO";
	
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
			
			int stopnie = 0;
			try {
				stopnie = CommandManager.parseMath(args[1], id);
			} catch (Exception e) { return; }
		
			TurtlesWorkspacePanel.getTurtle(id).rotate(-stopnie);
		}
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
}
