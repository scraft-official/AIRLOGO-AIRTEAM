package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.ConsoleOutputPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;

public class CMD_REPEAT {
	private static int argsCount = 2;
	
	public static String syntax = "REPEAT <ILE RAZY> [LISTA KOMEND]";
	public static String description = "POWTARZA WSKAZANE KOMENDY PODANĄ ILOŚĆ RAZY";
	
	public static void execute(String[] args) {

		if(args.length < argsCount + 1) {
			ConsoleOutputPanel.addErrorLog("PRAWIDLOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJACO ARGUMENTOW!");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		if(args[1] == null) {
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		int repeatCount = 0;
		try {
			repeatCount = Integer.valueOf(args[1]);
			if(repeatCount <= 0) {
				ConsoleOutputPanel.addErrorLog("LICZBA POWTORZEN MUSI BYC WIEKSZA OD 0! ( " + args[0] + " " + args[1] + " )");
				TurtlesWorkspacePanel.forceRefresh(true, true);
				return;
			}
		} catch(Exception e) {
			ConsoleOutputPanel.addErrorLog("WPROWADZONO NIEPRAWIDLOWA LICZBE POWTORZEN! ( " + args[0] + " " + args[1] + " != LICZBA )");
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		if(args[2].charAt(0) != '[') {
			ConsoleOutputPanel.addErrorLog(
					"UŻYJ ZNAKU \"[\", ABY ROZPOCZĄĆ LISTĘ POWTARZNYCH POLECEŃ.",
					"NIE ZNALEZIONO ROZPOCZĘCIA POWTARZNYCH POLECEŃ! ( " + String.join(" ", args) + " )"
			);
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		String repeatCommand = String.join(" ", Arrays.copyOfRange(args, argsCount, args.length));
		String restOfCommands = null;	
		
		int leftBracketCount = 0;
		int rightBracketCount = 0;
		int endIndex = 0;
		
		for(int i = 0; i < repeatCommand.length(); i++) {
			char ch = repeatCommand.charAt(i);
			
			if(ch == '[') {
				leftBracketCount++;
			}
			else if(ch == ']') {
				rightBracketCount++;
			}
			
			if(leftBracketCount == rightBracketCount) {
				endIndex = i;
				if(endIndex+1 < repeatCommand.length()) 
					
					if(repeatCommand.charAt(endIndex+1) == ' ')
						restOfCommands = repeatCommand.substring(endIndex+2, repeatCommand.length());
					
					else {
						ConsoleOutputPanel.addErrorLog(
								"SPRAWDŹ, CZY NIE UMIESZCZONO ZA DUŻO ZNAKOW \"]\" LUB DODATKOWYCH ZNAKÓW!",
								"ŹLE ZAKOŃCZONO LISTĘ POWTARZANYCH POLECEŃ ( " + String.join(" ", args) + " )"
								);
						TurtlesWorkspacePanel.forceRefresh(true, true);
						return;
					}
				repeatCommand = repeatCommand.substring(1, endIndex);
 				break;
			}
				
		}
		
		if(leftBracketCount > rightBracketCount) {
			ConsoleOutputPanel.addErrorLog(
					"UŻYJ ZNAKU \"]\", ABY ZAKOŃCZYĆ LISTE POWTARZNYCH POLECEŃ!",
					"NIE ZNALEZIONO ZAKOŃCZENIA LISTY POWTARZNYCH POLECEŃ! ( " + String.join(" ", args) + " )"
			);
			TurtlesWorkspacePanel.forceRefresh(true, true);
			return;
		}
		
		if(repeatCommand.length() <= 1) {
			if(repeatCommand.length() == 0 || repeatCommand.charAt(0) == ' ') {
				ConsoleOutputPanel.addErrorLog(
						"NIE WPROWADZONO ŻADNEJ LISTY POLECEŃ DO POWTÓRZENIA! ( " + String.join(" ", args) + " )"
				);
				TurtlesWorkspacePanel.forceRefresh(true, true);
				return;
			}
		}
		
		if(repeatCommand.charAt(0) == ' ')
			repeatCommand = repeatCommand.substring(1);
		
		for(int i = 0; i < repeatCount; i++) {
			CommandManager.parse(repeatCommand.split(" "));
		}
		
		if(restOfCommands != null && restOfCommands.length() > 0){
			CommandManager.parse(restOfCommands.split(" "));
		}
	}
}
