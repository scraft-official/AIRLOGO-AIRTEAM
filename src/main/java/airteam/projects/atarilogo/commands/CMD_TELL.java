package airteam.projects.atarilogo.commands;

import java.util.ArrayList;
import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;

public class CMD_TELL {
	private static int argsCount = 1;
	
	public static String syntax = "TELL [LISTA INDEKSÓW ŻÓŁWI]";
	public static String description = "WSKAZUJE ŻÓLWI KTÓRZY MAJĄ WYKONYWAĆ DALSZE POLECENIA";
	
	public static void execute(String[] args) {

		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
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
							Console_Output.addErrorLog(
									"SPRAWDZ, CZY NIE UMIESCILES ZA DUZO ZNAKOW \"]\" LUB DODATKOWYCH ZNAKOW!",
									"ZLE ZAKONCZONO LIST INDEKSÓW ŻÓŁWI " + String.join(" ", args) + " )"
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
						"NIE ZNALEZIONO ZAKOŃCZENIA LISTY INDEKSÓW ŻÓŁWI! ( " + String.join(" ", args) + " )"
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
			
			
		} else { turtlesIDs = args[1]; restOfCommands = String.join(" ",Arrays.copyOfRange(args, 2, args.length)); }
		
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
		
		Turtles_Workspace_Area.selectTurtle(selectedIDs, false);
		Turtles_Workspace_Area.forceRefresh(false, true);
		
		if(restOfCommands != null && restOfCommands.length() > 0){
			CommandManager.parse(restOfCommands.split(" "));
		}
	}
}
