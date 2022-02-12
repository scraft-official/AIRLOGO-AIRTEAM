package airteam.projects.atarilogo.commands;

import java.util.Arrays;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.turtle.Turtle;

public class CMD_EACH {
	private static int argsCount = 1;
	
	private static String syntax = "EACH [LISTA KOMEND]";
	
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
		
		String repeatCommand = String.join(" ", Arrays.copyOfRange(args, argsCount, args.length));
		String restOfCommands = null;	
		
		int leftBracketCount = 0;
		int rightBracketCount = 0;
		int endIndex = 0;
		
		for(int i = 0; i < repeatCommand.length(); i++) {
			//Log_Utilies.logInfo(repeatCommand.charAt(i));
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
						Console_Output.addErrorLog(
							"SPRAWDZ, CZY NIE UMIESCILES ZA DUZO ZNAKOW \"]\" LUB DODATKOWYCH ZNAKOW!",
							"ZLE ZAKONCZONO LISTE POWTARZANYCH POLECEN ( " + String.join(" ", args) + " )"
								);
						Turtles_Workspace_Area.forceRefresh(true, true);
						return;
					}
				repeatCommand = repeatCommand.substring(1, endIndex);
 				break;
			}
				
		}
		
		if(leftBracketCount > rightBracketCount) {
			Console_Output.addErrorLog(
					"UZYJ ZNAKU \"]\", ABY ZAKONCZYC LISTE POWTARZNYCH POLECEN!",
					"NIE ZNALEZIONO ZAKONCZENIA POWTARZNYCH POLECEN! ( " + String.join(" ", args) + " )"
			);
			Turtles_Workspace_Area.forceRefresh(true, true);
			return;
		}
		
		if(repeatCommand.length() <= 1) {
			if(repeatCommand.length() == 0 || repeatCommand.charAt(0) == ' ') {
				Console_Output.addErrorLog(
						"NIE WPROWADZONO ZADNEJ LISTY POLECEN DO POWTORZENIA! ( " + String.join(" ", args) + " )"
				);
				Turtles_Workspace_Area.forceRefresh(true, true);
				return;
			}
		}
		
		int selectedTurtle = Turtles_Workspace_Area.getSelectedTurtleID();
		for(int i = 0; i < Turtles_Workspace_Area.getAllTurtles().size(); i++) {
			if(repeatCommand.charAt(0) == ' ')
				repeatCommand = repeatCommand.substring(1);
			CommandManager.parse(repeatCommand.split(" "));
			Turtles_Workspace_Area.selectTurtle(i, false);
		}
		
		Turtles_Workspace_Area.selectTurtle(selectedTurtle, false);
		Turtles_Workspace_Area.forceRefresh(false, true);
		
		if(restOfCommands != null && restOfCommands.length() > 0){
			CommandManager.parse(restOfCommands.split(" "));
		}
	}
}
