package airteam.projects.airlogo.commands;

import java.util.ArrayList;
import java.util.Arrays;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;

public class CMD_TELL {
	private static int argsCount = 1;
	
	public static String syntax = "TELL [LISTA INDEKSÓW ŻÓŁWI]";
	public static String description = "WSKAZUJE ŻÓLWI KTÓRZY MAJĄ WYKONYWAĆ DALSZE POLECENIA";
	
	public static void execute(String[] args) throws Exception {

		if(args.length < argsCount + 1) {
			throw new CommandManager.CommandException("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
		}
		
		String turtlesIDs;
		String restOfCommands;	
		
		if(args[1].charAt(0) == '[') {
			
			turtlesIDs = String.join(" ", Arrays.copyOfRange(args, argsCount, args.length));
			restOfCommands = null;	
			
			int leftBracketCount = 0;
			int rightBracketCount = 0;
			int endIndex = 0;
			
			for(int i = 0; i < turtlesIDs.length(); i++) {
				char ch = turtlesIDs.charAt(i);
				
				if(ch == '[') {
					leftBracketCount++;
				}
				else if(ch == ']') {
					rightBracketCount++;
				}
				
				if(leftBracketCount == rightBracketCount) {
					endIndex = i;
					if(endIndex+1 < turtlesIDs.length()) 
						
						if(turtlesIDs.charAt(endIndex+1) == ' ')
							restOfCommands = turtlesIDs.substring(endIndex+2, turtlesIDs.length());
						
						else {
							throw new CommandManager.CommandException(
									"SPRAWDZ, CZY NIE UMIESCILES ZA DUZO ZNAKOW \"]\" LUB DODATKOWYCH ZNAKOW!",
									"ZLE ZAKONCZONO LIST INDEKSÓW ŻÓŁWI " + String.join(" ", args) + " )"
									);
						}
					turtlesIDs = turtlesIDs.substring(1, endIndex);
					break;
				}
					
			}
			
			if(leftBracketCount > rightBracketCount) {
				throw new CommandManager.CommandException(
						"UŻYJ ZNAKU \"]\", ABY ZAKOŃCZYĆ LISTE INDEKSÓW ŻÓŁWI!",
						"NIE ZNALEZIONO ZAKOŃCZENIA LISTY INDEKSÓW ŻÓŁWI! ( " + String.join(" ", args) + " )"
				);
			}
			
			if(turtlesIDs.length() <= 1) {
				if(turtlesIDs.length() == 0 || turtlesIDs.charAt(0) == ' ') {
					throw new CommandManager.CommandException(
							"NIE WPROWADZONO ŻADNEJ LISTY INDEKSÓW ŻÓŁWI! ( " + String.join(" ", args) + " )"
					);
				}
			}
			
			
		} else { turtlesIDs = args[1]; restOfCommands = String.join(" ",Arrays.copyOfRange(args, 2, args.length)); }
		
		if(turtlesIDs.charAt(0) == ' ')
			turtlesIDs = turtlesIDs.substring(1);
		
		ArrayList<Integer> selectedIDs = new ArrayList<>();
		
		for(String id : turtlesIDs.split(" ")) {
			try {
				selectedIDs.add(Integer.valueOf(id));
			} catch(Exception e) {
				throw new CommandManager.CommandException("WPROWADZONO BŁĘDNY INDEKS ŻÓŁWIA  ( " + id + "!= LICZBA )");
			}
		}
		
		TurtlesWorkspacePanel.selectTurtle(selectedIDs, false);
		TurtlesWorkspacePanel.forceRefresh(false, true);
		
		if(restOfCommands != null && restOfCommands.length() > 0){
			CommandManager.parse(restOfCommands.split(" "));
		}
	}
}
