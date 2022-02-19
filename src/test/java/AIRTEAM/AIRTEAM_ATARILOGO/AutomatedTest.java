package AIRTEAM.AIRTEAM_ATARILOGO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import airteam.projects.airlogo.commands.CommandManager;
import airteam.projects.airlogo.utilities.NTSCUtility;


public class AutomatedTest {
	
	@Test
	@DisplayName("NTSC Color checking")
	void NTSCcolorTest() {
    assertEquals(Color.decode("#FCE08C"), NTSCUtility.getAtariColorFromNumber(127), 
    						 "NTSC Color checking not correct.");  
	}
}
