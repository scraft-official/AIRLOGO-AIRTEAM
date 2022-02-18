package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.ConsoleOutputPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;

public class CMD_HELP {
private static int argsCount = 0;
	
	public static String syntax = "HELP";
	public static String description = "WYŚWIETLA LISTĘ WSZYSTKICH KOMEND";
	
	public static void execute(String... args) {
		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		
		//TODO WYPSIAC WSZYSTKIE komendy!
		ConsoleOutputPanel.addInfoLog("==============================================");
		ConsoleOutputPanel.addInfoLog(" - " + CMD_HELP.syntax + " - " + CMD_HELP.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_ASK.syntax + " - " + CMD_ASK.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_ED.syntax + " - " + CMD_ED.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_ERALL.syntax + " - " + CMD_ERALL.description);
		ConsoleOutputPanel.addInfoLog(" - TO <NAZWA PROCEDURY> - TWORZY PROCEDURE O PODANEJ NAZWIE");
		ConsoleOutputPanel.addInfoLog(" - " + CMD_POTS.syntax + " - " + CMD_POTS.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_EACH.syntax + " - " + CMD_EACH.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_REPEAT.syntax + " - " + CMD_REPEAT.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_SETPC.syntax + " - " + CMD_SETPC.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_SETPN.syntax + " - " + CMD_SETPN.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_SETC.syntax + " - " + CMD_SETC.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_TELL.syntax + " - " + CMD_TELL.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_ST.syntax + " - " + CMD_ST.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_HT.syntax + " - " + CMD_HT.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_PD.syntax + " - " + CMD_PD.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_PU.syntax + " - " + CMD_PU.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_CS.syntax + " - " + CMD_CS.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_RT.syntax + " - " + CMD_RT.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_LT.syntax + " - " + CMD_LT.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_BK.syntax + " - " + CMD_BK.description);
		ConsoleOutputPanel.addInfoLog(" - " + CMD_FD.syntax + " - " + CMD_FD.description);
		
		ConsoleOutputPanel.addInfoLog("LISTA DOSTĘPNYCH KOMEND:");
		ConsoleOutputPanel.addInfoLog("==============================================");
		
		
		
		
		if(args.length > (argsCount + 1)) {
			CommandManager.parse(Arrays.copyOfRange(args, argsCount + 1, args.length));
		}
		else {
			TurtlesWorkspacePanel.forceRefresh(true, true);
		}
	}
	
}
