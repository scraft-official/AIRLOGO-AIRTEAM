package airteam.projects.atarilogo.commands;

import java.util.ArrayList;
import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;

public class CMD_TELL {
	private static int argsCount = 1;
	
	private static String syntax = "TELL [LISTA INDEKSÓW ŻÓŁWI]";
	
	public static void execute(String[] args) {

		if(args.length < argsCount + 1) {
			Console_Output.addErrorLog("PRAWIDLOWE UZYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJACO ARGUMENTOW!");
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(args[1].charAt(0) != '[') {
			Console_Output.addErrorLog(
					"UZYJ ZNAKU \"[\", ABY ROZPOCZAC LISTE POWTARZNYCH POLECEN.",
					"NIE ZNALEZIONO ROZPOCZECIA POWTARZNYCH POLECEN! ( " + String.join(" ", args) + " )"
			);
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		String turtlesIDs = String.join(" ", Arrays.copyOfRange(args, argsCount, args.length));
		String restOfCommands = null;	
		
		int leftBracketCount = 0;
		int rightBracketCount = 0;
		int endIndex = 0;
		
		for(int i = 0; i < turtlesIDs.length(); i++) {
			//Log_Utilies.logInfo(repeatCommand.charAt(i));
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
							"ZLE ZAKONCZONO LISTE POWTARZANYCH POLECEN ( " + String.join(" ", args) + " )"
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
					"UZYJ ZNAKU \"]\", ABY ZAKONCZYC LISTE WYBIERANYCH ZOLWI!",
					"NIE ZNALEZIONO ZAKONCZENIA LISTY WYEBIERANYCH ZOLWI! ( " + String.join(" ", args) + " )"
			);
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(turtlesIDs.length() <= 1) {
			if(turtlesIDs.length() == 0 || turtlesIDs.charAt(0) == ' ') {
				Console_Output.addErrorLog(
						"NIE WPROWADZONO ZADNEJ LISTY POLECEN DO POWTORZENIA! ( " + String.join(" ", args) + " )"
				);
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
		}
		
		ArrayList<Integer> selectedTurtles = Turtles_Workspace_Area.getSelectedTurtlesID();
		for(int i = 0; i < Turtles_Workspace_Area.getAllTurtles().size(); i++) {
			Turtles_Workspace_Area.selectTurtle(i, false);
			if(turtlesIDs.charAt(0) == ' ')
				turtlesIDs = turtlesIDs.substring(1);
			CommandManager.parse(turtlesIDs.split(" "));
		}
		
		Turtles_Workspace_Area.selectTurtle(selectedTurtles, false);
		Turtles_Workspace_Area.forceRefresh(false, true);
		
		if(restOfCommands != null && restOfCommands.length() > 0){
			CommandManager.parse(restOfCommands.split(" "));
		}
	}
}
