package airteam.projects.airlogo.commands;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.functions.FunctionManager;

public class CommandManager {
	public static ScriptEngineManager manager;
	public static ScriptEngine engine;
	
	public static void initializeMathEngine() {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("js");
	}
	
	public static void parse(String[] args) throws Exception {
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
		else if(args[0].equals("SETC")) {
		  CMD_SETC.execute(args);
		}
		else if(args[0].equals("SETPN")) {
			CMD_SETPN.execute(args);
		}
		else if(args[0].equals("SETPC")) {
			CMD_SETPC.execute(args);
		}
		else if(args[0].equals("ASK")) {
			CMD_ASK.execute(args);
		}
		else if(args[0].equals("ERALL")) {
			CMD_ERALL.execute(args);
		}
		else if(args[0].equals("POTS")) {
			CMD_POTS.execute(args);
		}
		else if(args[0].equals("HELP")) {
			CMD_HELP.execute(args);
		}
		else if(args[0].equals("TELL")) {
			CMD_TELL.execute(args);
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
			throw new CommandManager.CommandException("NIE ZNALEZIONO TAKIEJ KOMENDY! ( " + String.join(" ", args) + " )");
		}
	}
	
	public static int parseMath(String arg, int turtleID) throws Exception {
		arg = arg.replaceAll("WHO", String.valueOf(turtleID));
		int calc = 0;
    try {
    	Object obj = engine.eval(arg);
    	if(obj instanceof Integer) calc = (int) engine.eval(arg);
    	else if(obj instanceof Double) calc = (int) Math.round((Double) engine.eval(arg));
    	else throw new Exception();

		} catch (Exception e) {
			ConsoleOutputPanel.addErrorLog("NIE MOŻNA OBLICZYĆ WARTOŚCI MATEMATYCZNEJ ( " + arg + " )");
			throw new Exception();
		}
		return calc;
	}
	
	public static class CommandException extends Exception {
		public CommandException(String... msgs) {
			super(String.join("\n", msgs));
		}
	}
			
}
