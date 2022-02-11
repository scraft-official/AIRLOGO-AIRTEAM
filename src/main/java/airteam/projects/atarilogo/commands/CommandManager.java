package airteam.projects.atarilogo.commands;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.functions.FunctionManager;

public class CommandManager {
	public static void parse(String[] args) {
		try {
			if(args[0].equals("FD")) {
				CMD_FD.execute(args);
			}
			else if(args[0].equals("BK")) {
				CMD_BK.execute(args);
			}
			else if(args[0].equals("LT")) {
				CMD_LT.execute(args);
			}
			else if(args[0].equals("RT")) {
				CMD_RT.execute(args);
			}
			else if(args[0].equals("HT")) {
				CMD_HT.execute(args);
			}
			else if(args[0].equals("ST")) {
				CMD_ST.execute(args);
			}
			else if(args[0].equals("CS")) {
				CMD_CS.execute(args);
			}
			else if(args[0].equals("PU")) {
				CMD_PU.execute(args);
			}
			else if(args[0].equals("PD")) {
				CMD_PD.execute(args);
			}
			else if(args[0].equals("REPEAT")) {
				CMD_REPEAT.execute(args);
			}
			else if(FunctionManager.existFunction(args[0])) {
				FunctionManager.parseFunction(args[0], args);
			}
			else {
				Console_Output.addErrorLog("NIE ZNALEZIONO TAKIEJ KOMENDY! ( " + String.join(" ", args) + " )");
			}
		}
		catch(Exception e) { e.printStackTrace(); Console_Output.addErrorLog("WYSTAPIL BLAD Z WYKONYWANIEM TEJ KOMENDY! ( " + e.getMessage() + " ) ( " + String.join(" ", args) + " )"); }
	}
	
	public static String[] parseMath(String[] args) {
		return args;
	}
			
}
