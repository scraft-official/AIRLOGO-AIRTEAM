package airteam.projects.airlogo.commands;

import java.util.Arrays;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.components.dialogs.popups.EditFunctionPopup;
import airteam.projects.airlogo.functions.FunctionManager;

public class CMD_ED {
private static int argsCount = 1;
	
	public static String syntax = "ED <NAZWA PROCEDURY>";
	public static String description = "ROZPOCZYNA EDYCJĘ WSKAZANEJ PROCEDURY";
	
	public static void execute(String[] args) throws Exception {
		if(args.length < argsCount + 1) {
			throw new CommandManager.CommandException("PRAWIDŁOWE UŻYCIE KOMENDY: " + syntax, "WPROWADZONO NIEWYSTARCZAJĄCO ARGUMENTÓW!");
		}
		
		String functionNames;
		
		if(args[1].charAt(0) == '[') {
			
			functionNames = String.join(" ", Arrays.copyOfRange(args, argsCount, args.length));	
			
			int leftBracketCount = 0;
			int rightBracketCount = 0;
			int endIndex = 0;
			
			for(int i = 0; i < functionNames.length(); i++) {
				char ch = functionNames.charAt(i);
				
				if(ch == '[') {
					leftBracketCount++;
				}
				else if(ch == ']') {
					rightBracketCount++;
				}
				
				if(leftBracketCount == rightBracketCount) {
					endIndex = i;
					if(endIndex+1 < functionNames.length()) 			
						if(functionNames.charAt(endIndex+1) != ' '){
							throw new CommandManager.CommandException(
									"SPRAWDZ, CZY NIE UMIESCILES ZA DUZO ZNAKOW \"]\" LUB DODATKOWYCH ZNAKOW!",
									"ZLE ZAKONCZONO LIST INDEKSÓW ŻÓŁWI " + String.join(" ", args) + " )"
									);
						}
					functionNames = functionNames.substring(1, endIndex);
					break;
				}
					
			}
			
			if(leftBracketCount > rightBracketCount) {
				throw new CommandManager.CommandException(
						"UŻYJ ZNAKU \"]\", ABY ZAKOŃCZYĆ LISTE INDEKSÓW ŻÓŁWI!",
						"NIE ZNALEZIONO ZAKOŃCZENIA LISTY INDEKSÓW ŻÓŁWI! ( " + String.join(" ", args) + " )"
				);
			}
			
			if(functionNames.length() <= 1) {
				if(functionNames.length() == 0 || functionNames.charAt(0) == ' ') {
					throw new CommandManager.CommandException(
							"NIE WPROWADZONO ŻADNEJ LISTY INDEKSÓW ŻÓŁWI! ( " + String.join(" ", args) + " )"
					);
				}
			}
			
			
		} else { functionNames = args[1]; }
		
		if(functionNames.charAt(0) == ' ')
			functionNames = functionNames.substring(1);
		
		for(String func : functionNames.split(" ")) {
			if(FunctionManager.existFunction(func) && FunctionManager.getFunction(func).isDefaultFunction) {
				throw new CommandManager.CommandException("NIE MOŻESZ EDYTOWAĆ WBUDOWANYCH PROCEDUR!");
			}
			
			if(FunctionManager.existFunction(func)) {
				new EditFunctionPopup(func);
			} else { throw new CommandManager.CommandException("DODAJ NOWĄ PROCEDURĘ UŻYWAJĄĆ \"TO <NAZWAFUNKCJI>\"", "TA PROCEDURA NIE ISTNIEJE!"); }
		}
		
	}
	
}
