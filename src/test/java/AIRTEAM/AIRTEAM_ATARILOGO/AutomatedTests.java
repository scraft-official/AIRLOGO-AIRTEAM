package AIRTEAM.AIRTEAM_ATARILOGO;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import java.lang.System.Logger;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sun.tools.sjavac.Log;

import airteam.projects.airlogo.AirLogo;
import airteam.projects.airlogo.commands.CommandManager;
import airteam.projects.airlogo.components.ConsoleInputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.functions.FunctionManager;
import airteam.projects.airlogo.utilities.NTSCUtility;


public class AutomatedTests {
	AirLogo app = new AirLogo(false);
	
	
	@Test
	@DisplayName("NTSC Color check")
	void NTSCcolorCheck() {
    assertEquals(Color.decode("#FCE08C"), NTSCUtility.getAtariColorFromNumber(127));
	}
	
	@Test
	@DisplayName("Commands parse check")
	void commandsParseCheck() {
		assertThrows(Exception.class, () -> CommandManager.parse("RANDOM COMMAND THAT NOT EXISTS".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("FD 100".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("FD 1000".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("FD 10000".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("FD 100000".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("BK 100".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("BK 1000".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("BK 10000".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("BK 100000".split(" ")));
		
		
		assertDoesNotThrow(() -> CommandManager.parse("LT 0".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 30".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 60".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 90".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 120".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 150".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 270".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 330".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 360".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 520".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("LT 670".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("RT 0".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 30".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 60".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 90".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 120".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 150".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 270".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 330".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 360".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 520".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("RT 670".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("CS".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("PU".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("PD".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("HT".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("ST".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("TELL 0".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("TELL 1".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("TELL 2".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("TELL 3".split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse("TELL 7".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("TELL [0 1 2 3 4 5 6 7]".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("EACH [REPEAT 4 [FD 100 RT 90]]".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("ASK [1 2 3 4 5] [REPEAT 4 [FD 100 RT 90]]".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("POTS".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse("HELP".split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 4").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 6").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 12").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 42").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 23").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 37").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 53").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 68").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 77").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 82").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 85").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 91").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETC 127").split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 0 4").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 0 6").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 1 12").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 1 42").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 2 23").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 2 37").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 1 53").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 1 68").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 2 77").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 2 82").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 0 85").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 0 91").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPC 1 127").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("SETPC 0 129").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("SETC 129").split(" ")));
		
		assertDoesNotThrow(() -> CommandManager.parse(("SETPN 0").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPN 1").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("SETPN 2").split(" ")));
		
		
		assertThrows(Exception.class, () -> CommandManager.parse(("SETPN 5").split(" ")));
		
		assertThrows(Exception.class, () -> CommandManager.parse(("FD").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("BK").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("RT").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("LT").split(" ")));
		
		assertThrows(Exception.class, () -> CommandManager.parse(("EACH").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("EACH [").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("EACH []").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("EACH []]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("EACH [[]]]").split(" ")));
		
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT [").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT []").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT []]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT [[]]]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT 1 [").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT 1 []").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT 1 []]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT 1 [[]]]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("REPEAT A [FD 100]").split(" ")));
		
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK [").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK []").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK []]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK [[]]]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK 1 [").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK 1 []").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK 1 []]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK 1 [[]]]").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("ASK [2]] [FD 100]").split(" ")));
	}
	
	@Test
	@DisplayName("Math parse check") 
	public void mathParseCheck() {
		try {
			assertEquals(6000000, CommandManager.parseMath("100*200*300", 0));
			assertEquals(2000, CommandManager.parseMath("100*(10+10)", 0));
			assertEquals(59, CommandManager.parseMath("4*8+(2*7+(4*2+(3+2)))", 0));
			assertEquals(12, CommandManager.parseMath("2*(2+2+2+2-2)", 0));
			
			assertEquals(400, CommandManager.parseMath("100*WHO", 4));
			assertEquals(500, CommandManager.parseMath("100*(WHO+1)", 4));
			assertEquals(20, CommandManager.parseMath("100/(WHO+1)", 4));
			assertEquals(25, CommandManager.parseMath("100/(WHO-1)", 5));
			
		} catch (Exception e) {}
	}
		
	@Test
	@DisplayName("Function parse check") 
	public void functionParsECheck() {
		ArrayList<String> args = new ArrayList<String>();
		args.add(":ARG");
		
		assertDoesNotThrow(() -> FunctionManager.addFunction("testFunction", args, "FD :ARG RT 90 FD :ARG RT 90"));
		assertDoesNotThrow(() -> CommandManager.parse(("testFunction 100").split(" ")));
		assertThrows(Exception.class, () -> CommandManager.parse(("testFunction").split(" "))); //throws error if no arg set
	
		assertDoesNotThrow(() -> CommandManager.parse(("OKRAG 100").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("KWADRAT 100").split(" ")));
		assertDoesNotThrow(() -> CommandManager.parse(("WIELOKAT 6 100").split(" ")));
	}
	
}
