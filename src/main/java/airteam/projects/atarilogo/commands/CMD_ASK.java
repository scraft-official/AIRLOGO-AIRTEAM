package airteam.projects.atarilogo.commands;

import java.util.ArrayList;
import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.utilities.Log_Utilies;

public class CMD_ASK {
	private static int argsCount = 2;
	
	private static String syntax = "ASK [LISTA INDEKSÓW ŻÓŁWI] [LISTA KOMEND]";
	
	public static void execute(String[] args) {

		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(args[1].charAt(0) != '[') {
			Console_Output.addErrorLog(
					"UŻYJ ZNAKU \"[\", ABY ROZPOCZĄĆ LISTĘ INDEKSÓW ŻÓŁWI.",
					"NIE ZNALEZIONO ROZPOCZĘCIA LISTY INDEKSÓW ŻÓŁWI! ( " + String.join(" ", args) + " )"
			);
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		String turtlesIDs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
		String executionCommands = null;
		String restOfCommands = null;
		
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
						executionCommands = turtlesIDs.substring(endIndex+2, turtlesIDs.length());
					
					else {
						Console_Output.addErrorLog(
							"SPRAWDŹ, CZY NIE UMIESZCZONO ZA DUŻO ZNAKOW \"]\" LUB DODATKOWYCH ZNAKÓW!",
							"ŹLE ZAKOŃCZONO LISTĘ INDEKSÓW ŻÓŁWI ( " + String.join(" ", args) + " )"
						);
						Turtles_Workspace_Area.forceRefresh(true, true);
						return;
					}
				turtlesIDs = turtlesIDs.substring(1, endIndex);
 				break;
			}
				
		}
		
		if(leftBracketCount > rightBracketCount) {
			Console_Output.addErrorLog(
					"UŻYJ ZNAKU \"]\", ABY ZAKOŃCZYĆ LISTE INDEKSÓW ŻÓŁWI!",
					"NIE ZNALEZIONO ZAKOŃCZENIA LISTY INDEKSÓW ŻÓŁWI!  ( " + String.join(" ", args) + " )"
			);
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(turtlesIDs.length() <= 1) {
			if(turtlesIDs.length() == 0 || turtlesIDs.charAt(0) == ' ') {
				Console_Output.addErrorLog(
						"NIE WPROWADZONO ŻADNEJ LISTY INDEKSÓW ŻÓŁWI! ( " + String.join(" ", args) + " )"
				);
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
		}
		
		//COMMANDS EXECUTION READER
		
		if(executionCommands == null || executionCommands.charAt(0) != '[') {
			Console_Output.addErrorLog(
					"UŻYJ ZNAKU \"[\", ABY ROZPOCZĄĆ LISTĘ POWTARZNYCH POLECEŃ.",
					"NIE ZNALEZIONO ROZPOCZĘCIA POWTARZNYCH POLECEŃ! ( " + String.join(" ", args) + " )"
			);
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		leftBracketCount = 0;
		rightBracketCount = 0;
		endIndex = 0;
		
		for(int i = 0; i < executionCommands.length(); i++) {
			char ch = executionCommands.charAt(i);
			
			if(ch == '[') {
				leftBracketCount++;
			}
			else if(ch == ']') {
				rightBracketCount++;
			}
			
			if(leftBracketCount == rightBracketCount) {
				endIndex = i;
				if(endIndex+1 < executionCommands.length()) 
					
					if(executionCommands.charAt(endIndex+1) == ' ')
						restOfCommands = executionCommands.substring(endIndex+2, executionCommands.length());
					
					else {
						Console_Output.addErrorLog(
							"SPRAWDŹ, CZY NIE UMIESZCZONO ZA DUŻO ZNAKOW \"]\" LUB DODATKOWYCH ZNAKÓW!",
							"ŹLE ZAKOŃCZONO LISTĘ POWTARZNYCH POLECEŃ ( " + String.join(" ", args) + " )"
						);
						Turtles_Workspace_Area.forceRefresh(true, true);
						return;
					}
				executionCommands = executionCommands.substring(1, endIndex);
 				break;
			}
				
		}
		
		if(leftBracketCount > rightBracketCount) {
			Console_Output.addErrorLog(
					"UŻYJ ZNAKU \"]\", ABY ZAKOŃCZYĆ LISTE INDEKSÓW ŻÓŁWI!",
					"NIE ZNALEZIONO ZAKOŃCZENIA LISTY POWTARZNYCH POLECEŃ! ( " + String.join(" ", args) + " )"
			);
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(executionCommands.length() <= 1) {
			if(executionCommands.length() == 0 || executionCommands.charAt(0) == ' ') {
				Console_Output.addErrorLog(
						"NIE WPROWADZONO ŻADNEJ LISTY POWTARZNYCH POLECEŃ! ( " + String.join(" ", args) + " )"
				);
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
		}
		
		//------------------------------------------------------
		
		if(executionCommands.charAt(0) == ' ')
			executionCommands = executionCommands.substring(1);
		
		if(turtlesIDs.charAt(0) == ' ')
			turtlesIDs = turtlesIDs.substring(1);
		
		ArrayList<Integer> selectedIDs = new ArrayList<>();
		
		for(String id : turtlesIDs.split(" ")) {
			try {
				selectedIDs.add(Integer.valueOf(id));
			} catch(Exception e) {
				Console_Output.addErrorLog("WPROWADZONO BŁĘDNY INDEKS ŻÓŁWIA  ( " + id + "!= LICZBA )");
			}
		}
		
		ArrayList<Integer> alreadySelectedTurtles = Turtles_Workspace_Area.getSelectedTurtlesID();
		
		Turtles_Workspace_Area.selectTurtle(selectedIDs, false);
		CommandManager.parse(executionCommands.split(" "));
		Turtles_Workspace_Area.selectTurtle(alreadySelectedTurtles, false);
		
		
		if(restOfCommands != null && restOfCommands.length() > 0){
			CommandManager.parse(restOfCommands.split(" "));
		}
	}
}
