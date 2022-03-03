package airteam.projects.airlogo.commands;

import java.util.ArrayList;
import java.util.Arrays;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;

public class CMD_EACH {
	private static int argsCount = 1;
	
	public static String syntax = "EACH [LISTA KOMEND]";
	public static String description = "WYSYŁA POLECENIA DO WSZYSTKICH ŻÓŁWI";
	
	public static void execute(String[] args) throws Exception {

		if(args.length < argsCount + 1) {
			throw new CommandManager.CommandException("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
		}
		
		if(args[1].charAt(0) != '[') {
			throw new CommandManager.CommandException(
					"UŻYJ ZNAKU \"[\", ABY ROZPOCZĄĆ LISTĘ POWTARZNYCH POLECEŃ.",
					"NIE ZNALEZIONO ROZPOCZĘCIA POWTARZNYCH POLECEŃ! ( " + String.join(" ", args) + " )"
			);
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
						throw new CommandManager.CommandException(
							"SPRAWDŹ, CZY NIE UMIESZCZONO ZA DUŻO ZNAKOW \"]\" LUB DODATKOWYCH ZNAKÓW!",
							"ŹLE ZAKOŃCZONO LISTĘ POWTARZANYCH POLECEŃ ( " + String.join(" ", args) + " )"
						);
					}
				repeatCommand = repeatCommand.substring(1, endIndex);
 				break;
			}
				
		}
		
		if(leftBracketCount > rightBracketCount) {
			throw new CommandManager.CommandException(
					"UŻYJ ZNAKU \"]\", ABY ZAKOŃCZYĆ LISTE POWTARZNYCH POLECEŃ!",
					"NIE ZNALEZIONO ZAKOŃCZENIA LISTY POWTARZNYCH POLECEŃ! ( " + String.join(" ", args) + " )"
			);
		}
		
		if(repeatCommand.length() <= 1) {
			if(repeatCommand.length() == 0 || repeatCommand.charAt(0) == ' ') {
				throw new CommandManager.CommandException(
						"NIE WPROWADZONO ŻADNEJ LISTY POLECEŃ DO POWTÓRZENIA! ( " + String.join(" ", args) + " )"
				);
			}
		}
		
		if(repeatCommand.charAt(0) == ' ')
			repeatCommand = repeatCommand.substring(1);
		
		ArrayList<Integer> alreadySelectedTurtles = TurtlesWorkspacePanel.getSelectedTurtlesID();
		for(int i = 0; i < TurtlesWorkspacePanel.getAllTurtles().size(); i++) {
			TurtlesWorkspacePanel.selectTurtle(i, false);
			CommandManager.parse(repeatCommand.split(" "));
		}
		
		TurtlesWorkspacePanel.selectTurtle(alreadySelectedTurtles, false);
		TurtlesWorkspacePanel.forceRefresh(false, true);
		
		if(restOfCommands != null && restOfCommands.length() > 0){
			CommandManager.parse(restOfCommands.split(" "));
		}
	}
}
