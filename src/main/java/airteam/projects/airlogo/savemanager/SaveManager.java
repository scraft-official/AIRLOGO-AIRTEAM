package airteam.projects.airlogo.savemanager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONArray;
import org.json.JSONObject;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.functions.FunctionManager;
import airteam.projects.airlogo.functions.FunctionManager.TurtleFunction;
import airteam.projects.airlogo.turtle.Turtle;
import airteam.projects.airlogo.turtle.Turtle.Turtle_Movement;
import airteam.projects.airlogo.utilities.GraphicsUtility;

public class SaveManager {
	public static boolean importWorkspace(String path) {
		if(path == null) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
	    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        File file = fileChooser.getSelectedFile();
	        if (file == null) return false;
	        
	        path = fileChooser.getSelectedFile().getAbsolutePath();
	    } else return false;
		}
    
    
    String fileContent = null;
    try(FileInputStream inputStream = new FileInputStream(path)) {     
    	fileContent = new String(inputStream.readAllBytes());
    } catch (FileNotFoundException e) {
    	ConsoleOutputPanel.addErrorLog("(" + path + ")", "NIE ZNALEZIONO TAKIEGO PLIKU!");
			return false;
		} catch (IOException e) { 
			ConsoleOutputPanel.addErrorLog("(" + path + ")", "WYSTĄPIŁ PROBLEM Z OTWIERANIEM PLIKU!");
			return false;
		}
    
    try {
    	JSONObject json = new JSONObject(fileContent);
    	
    	ArrayList<Turtle> turtlesFinalList = new ArrayList<>();
    	
    	for(Object turtleMapObject : (JSONArray) json.get("turtles")) {
    		JSONObject turtleMap = (JSONObject) turtleMapObject;
    		Turtle t = new Turtle((int) turtleMap.get("turtlePosX"),(int) turtleMap.get("turtlePosY"), (String) turtleMap.get("turtleName"), Color.decode((String) turtleMap.get("turtleColor")));
    		t.rotate((int) turtleMap.get("turtleRotation"));
    		t.setTurtleVisibility((boolean) turtleMap.get("turtleVisibility"));
    		t.setPenVisibility((boolean) turtleMap.get("penVisibility"));
    		
    		ArrayList<Turtle_Movement> turtleMovements = new ArrayList<>();
    		
    		for(Object moveDataObject : (JSONArray) turtleMap.get("movements")) {
    			JSONObject moveData = (JSONObject) moveDataObject;
    			turtleMovements.add(new Turtle_Movement(
  					(int) moveData.get("x1"),
  					(int) moveData.get("y1"),
  					(int) moveData.get("x2"),
  					(int) moveData.get("y2"),
  					Color.decode((String) moveData.get("penColor"))
    		));}
    			
    		t.setMovements(turtleMovements);
    		turtlesFinalList.add(t);
    	}
    	
    	HashMap<String, TurtleFunction> funcionsFinalMap = new HashMap<>();
    	
    	for(Object functionMapObject : (JSONArray) json.get("functions")) {
    		JSONObject functionMap = (JSONObject) functionMapObject;
    		
    		ArrayList<String> functionArgs = new ArrayList<>();
    		for(Object arg : (JSONArray) functionMap.get("args")) {
    			functionArgs.add((String) arg);
    		}
    		
    		funcionsFinalMap.put((String) functionMap.get("name"), new TurtleFunction(
    				functionArgs, 
    				(String) functionMap.get("commands"),
    				(Boolean) functionMap.get("isDefaultFunction")
    		));
    	}
    	
    	JSONObject workspaceDataMap = (JSONObject) json.get("workspace");
    	ArrayList<Color> pensFinalList = new ArrayList<>();
    	
    	for(Object c : (JSONArray) workspaceDataMap.get("pens")) {
    		pensFinalList.add(Color.decode((String) c));
    	}
    	
    	TurtlesWorkspacePanel.clearWorkspace();
    	
    	for(int i = 0; i < pensFinalList.size(); i++) {
    		TurtlesWorkspacePanel.setPenColor(i, pensFinalList.get(i));
    	}
    	
    	TurtlesWorkspacePanel.setTurtlesList(turtlesFinalList);
    	FunctionManager.setFunctionsList(funcionsFinalMap);
    	
    	TurtlesWorkspacePanel.selectTurtle(0, false);
    	TurtlesWorkspacePanel.forceRefresh(true, true);
    	
    } catch(Exception e) {
    	ConsoleOutputPanel.addErrorLog("(" + path + ")", "WYBRANY PLIK NIE JEST ZAPISEM PLANSZY ŻÓŁWIA!");
			return false;
    }
    ConsoleOutputPanel.addCustomColorLog(new Color(50, 168, 82), "POMYŚLNIE ZIMPORTOWANO PLANSZĘ ŻÓŁWIA!");
    return true;
	}
	
	
	public static boolean saveWorkspace() {
		String path = null;
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if (file == null) return false;
        
        path = fileChooser.getSelectedFile().getAbsolutePath();
    } else return false;
		
		
		long startTime = (System.currentTimeMillis());
		
		JSONObject json = new JSONObject();
		
		ArrayList<HashMap<String, Object>> turtlesData = new ArrayList<>();
		
		HashMap<String, Object> turtleMap;
		
		for(Turtle t : TurtlesWorkspacePanel.getAllTurtles()) {
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
		for(Color p : TurtlesWorkspacePanel.getAllPens()) {
			pens.add(String.format("#%02x%02x%02x", p.getRed(), p.getGreen(), p.getBlue()));
		}
		
		workspaceData.put("pens", pens);
		
		json.put("workspace", workspaceData);
		json.put("turtles", turtlesData);
		json.put("functions", functionsData);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM hh-mm-ss");
		String filePath = (path + "\\AirLogo - ZAPIS - " + dateFormat.format(startTime) + ".airlogo");
		
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (Exception e) {
			ConsoleOutputPanel.addErrorLog("(" + path + ")", "NIE MOŻNA ZAPISAĆ PLIKU W PODANEJ LOKALIZACJI!");
			return false;
		}
		
		try (PrintWriter fileWriter = new PrintWriter(filePath)) {
			fileWriter.println(json.toString(2));
		} catch (Exception e) {
			ConsoleOutputPanel.addErrorLog("(" + path + ")", "WYSTĄPIŁ PROBLEM Z ZAPISYWANIEM PLIKU!");
			return false;
		}
		
		ConsoleOutputPanel.addCustomColorLog(new Color(50, 168, 82), "(" + filePath + ")", "POMYŚLNIE ZAPISANO PLANSZĘ! MOŻESZ JĄ ZNALEŹĆ TUTAJ:");
		return true;
	}
	
	public static void saveWorkspaceImage() {
		BufferedImage workspace = TurtlesWorkspacePanel.getWorkspaceImage();
  	BufferedImage outputImage = new BufferedImage(workspace.getWidth()+30, workspace.getHeight()+30, 2);
  	
  	Graphics2D g2d = (Graphics2D) outputImage.getGraphics();
  	g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		GraphicsUtility.setGradientPaint(g2d, new Color(250, 247, 247), new Color(232, 227, 227), outputImage.getWidth(), outputImage.getHeight());
		g2d.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
		g2d.drawImage(workspace, 15, 15, null);
		
		g2d.drawImage(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/logo.png"), 200, 200), 20, 20, null);
		
		g2d.setFont(new Font("Tahoma", Font.BOLD, 32));
		g2d.setColor(new Color(59, 53, 53));
		
		FontMetrics ft = g2d.getFontMetrics();
		g2d.drawString("AIRLOGO", outputImage.getWidth() - ft.stringWidth("AIRLOGO") - 25, outputImage.getHeight() - 25);

		
		g2d.setColor(new Color(46, 41, 41));
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(15, 15, workspace.getWidth(), workspace.getHeight(), 15, 15);
  	
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		String path = null;
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if (file == null) return;
        
        path = fileChooser.getSelectedFile().getAbsolutePath();
    } else return;
    
    long startTime = System.currentTimeMillis();
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM hh-mm-ss");
		String filePath = (path + "\\AirLogo - ZRZUT PLANSZA - " + dateFormat.format(startTime) + ".png");
		
  	try {
			ImageIO.write(outputImage, "png", new File(filePath));
		} catch (Exception ex) {
			ConsoleOutputPanel.addErrorLog("(" + path + ")", "NIE UDAŁO SIĘ ZAPISAĆ ZDJĘCIA PLANSZY W PODANEJ LOKALIZACJI!");
		}
  	ConsoleOutputPanel.addCustomColorLog(new Color(50, 168, 82), "(" + filePath + ")", "POMYŚLNIE ZROBIONO ZDJĘCIE PLANSZY! ZNAJDZIESZ JE TUTAJ");
	}
}
