package airteam.projects.atarilogo.commands;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.functions.FunctionManager;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Log_Utilies;

public class CommandManager {
	public static ScriptEngineManager manager;
	public static ScriptEngine engine;
	
	public static void initializeMathEngine() {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("js");
	}
	
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
			else if(args[0].equals("EACH")) {
				CMD_EACH.execute(args);
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
	
	public static String parseMath(String arg, int turtleID) {
		arg = arg.replaceAll("WHO", String.valueOf(turtleID));
		
    try {
    	arg = Integer.valueOf(((Number) engine.eval(arg)).toString()).toString();
		} catch (Exception e) {
			Console_Output.addErrorLog("NIE MOZNA OBLICZYC ROWNANIA MATEMATYCZNEGO ( " + arg + " )");
			return null;
		}
		
		return arg;
	}
			
}
