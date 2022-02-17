package airteam.projects.atarilogo.savemanager;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;

import org.json.JSONObject;

import airteam.projects.atarilogo.components.Console_Output;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.functions.FunctionManager;
import airteam.projects.atarilogo.functions.FunctionManager.TurtleFunction;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.turtle.Turtle.Turtle_Movement;
import airteam.projects.atarilogo.utilities.Log_Utilies;

public class SaveManager {
	public static void saveWorkspace() {
		String path = null;
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if (file == null) return;
        
        path = fileChooser.getSelectedFile().getAbsolutePath();
    } else return;
		
		
		long startTime = (System.currentTimeMillis());
		
		JSONObject json = new JSONObject();
		
		ArrayList<HashMap<String, Object>> turtlesData = new ArrayList<>();
		
		HashMap<String, Object> turtleMap;
		
		for(Turtle t : Turtles_Workspace_Area.getAllTurtles()) {
			turtleMap = new HashMap<>();
			turtleMap.put("turtleName", t.getName());
			turtleMap.put("turtleRotation", t.getRotation());
			turtleMap.put("turtlePosX", t.getX());
			turtleMap.put("turtlePosY", t.getY());
			turtleMap.put("turtleColor", String.format("#%02x%02x%02x", t.getTurtleColor().getRed(), t.getTurtleColor().getGreen(), t.getTurtleColor().getBlue()));
			turtleMap.put("turtleVisibility", t.getTurtleVisibility());
			turtleMap.put("penVisibility", t.getPenVisibility());
			
			ArrayList<HashMap<String, Object>> turteMovement = new ArrayList<>();
			for(Turtle_Movement move : t.getAllTurtleMovements()) {
				HashMap<String, Object> moveData = new HashMap<>();
				
				moveData.put("x1", move.x1);
				moveData.put("y1", move.y1);
				moveData.put("x2", move.x2);
				moveData.put("y2", move.y2);
				moveData.put("penColor", String.format("#%02x%02x%02x", move.penColor.getRed(), move.penColor.getGreen(), move.penColor.getBlue()));
				
				turteMovement.add(moveData);
			}
			
			turtleMap.put("movements", turteMovement);
			
			turtlesData.add(turtleMap);
		}
		
		ArrayList<HashMap<String, Object>> functionsData = new ArrayList<>();
		for(String name : FunctionManager.getAllFunctions().keySet()) {
			HashMap<String, Object> functionMap = new HashMap<>();
			
			TurtleFunction func = FunctionManager.getFunction(name);
			
			functionMap.put("name", name);
			functionMap.put("args", func.args);
			functionMap.put("commands", func.commands);
			functionMap.put("isDefaultFunction", func.isDefaultFunction);
			
			functionsData.add(functionMap);
		}
		
		HashMap<String, Object> workspaceData = new HashMap<>();
		
		ArrayList<String> pens = new ArrayList<>();
		for(Color p : Turtles_Workspace_Area.getAllPens()) {
			pens.add(String.format("#%02x%02x%02x", p.getRed(), p.getGreen(), p.getBlue()));
		}
		
		workspaceData.put("pens", pens);
		
		json.put("workspace", workspaceData);
		json.put("turtles", turtlesData);
		json.put("functions", functionsData);
		Log_Utilies.logInfo(json.toString(2),"ZAPISANO W CIAGU: " + ((System.currentTimeMillis()) - startTime) + "MS");
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM hh-mm-ss");
		String filePath = (path + "\\AirLogo - ZAPIS - " + dateFormat.format(startTime) + ".airlogo");
		
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (Exception e) {
			Console_Output.addErrorLog("(" + path + ")", "WYSTĄPIŁ PROBLEM Z ZAPISYWANIEM PLIKU!");
			return;
		}
		
		try (PrintWriter fileWriter = new PrintWriter(filePath)) {
			fileWriter.println(json.toString(2));
		} catch (Exception e) {
			Console_Output.addErrorLog("(" + path + ")", "WYSTĄPIŁ PROBLEM Z ZAPISYWANIEM PLIKU!");
			return;
		}
		
		Console_Output.addCustomColorLog(new Color(50, 168, 82), "(" + filePath + ")", "POMYŚLNIE ZAPISANO PLANSZĘ! MOŻESZ JĄ ZNALEŹĆ TUTAJ:");
	}
	
	@SuppressWarnings("unchecked")
	public static void importWorkspace() {
		String path = null;
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if (file == null) return;
        
        path = fileChooser.getSelectedFile().getAbsolutePath();
    } else return;
    
    
    String fileContent = null;
    try(FileInputStream inputStream = new FileInputStream(path)) {     
    	fileContent = new String(inputStream.readAllBytes());
    } catch (FileNotFoundException e) {
    	Console_Output.addErrorLog("(" + path + ")", "NIE ZNALEZIONO TAKIEGO PLIKU!");
			return;
		} catch (IOException e) { 
			Console_Output.addErrorLog("(" + path + ")", "WYSTĄPIŁ PROBLEM Z OTWIERANIEM PLIKU!");
			return;
		}
    
    try {
    	JSONObject json = new JSONObject(fileContent);
    	
    	ArrayList<HashMap<String, Object>> turtlesData = (ArrayList<HashMap<String, Object>>) json.get("turtles");
    	ArrayList<Turtle> turtlesFinalList = new ArrayList<>();
    	for(HashMap<String, Object> turtleMap : turtlesData) {
    		Turtle t = new Turtle((int) turtleMap.get("turtlePosX"),(int) turtleMap.get("turtlePosY"), (String) turtleMap.get("turtleName"), Color.decode((String) turtleMap.get("turtleColor")));
    		t.rotate((int) turtleMap.get("turtleRotation"));
    		t.setTurtleVisibility((boolean) turtleMap.get("turtleVisibility"));
    		t.setPenVisibility((boolean) turtleMap.get("penVisibility"));
    		
    		ArrayList<Turtle_Movement> turtleMovements = new ArrayList<>();
    		
    		for(HashMap<String, Object> moveData : (ArrayList<HashMap<String, Object>>) json.get("movements")) {
    			turtleMovements.add(new Turtle_Movement(
    					(int) moveData.get("x1"),
    					(int) moveData.get("y1"),
    					(int) moveData.get("x2"),
    					(int) moveData.get("y2"),
    					Color.decode((String) moveData.get("penColor"))
    					));
    	}
    			
    		t.setMovements(turtleMovements);
    	}
    	
    } catch(Exception e) {
    	Console_Output.addErrorLog("(" + path + ")", "WYBRANY PLIK NIE JEST ZAPISEM PLANSZY ŻÓŁWIA!");
			return;
    }
    
    
	}
	
	
}
