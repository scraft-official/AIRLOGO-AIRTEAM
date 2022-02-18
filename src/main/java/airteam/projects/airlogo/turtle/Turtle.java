package airteam.projects.airlogo.turtle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.utilities.GraphicsUtility;

public class Turtle {
	private String turtleName;
	private int turtleRotation;
	private int turtlePosX;
	private int turtlePosY;
	private Color turtleColor;
	
	private boolean turtleVisibility;
	private boolean penVisibility;
	
	private int penSize = 4;
	
	private	HashMap<Double, BufferedImage> scaledTurtleImages = new HashMap<>();
	
	
	private ArrayList<Turtle_Movement> turtleMovementList = new ArrayList<>();
	
	public Turtle(int x, int y, String name, Color color) {
		turtlePosX = x;
		turtlePosY = y;
		turtleRotation = 0;
		turtleVisibility = true;
		turtleName = name;
		
		penVisibility = true;

		turtleColor = color;
		
		for(double i = 0.25; i <= 2; i += 0.25) {
			BufferedImage turtleIcon;
			if(turtleName.equals("KAMIL") || turtleName.equals("KB"))  { turtleIcon = GraphicsUtility.getInternalIcon("icons/turtle_kamil.png"); }
			else { turtleIcon = GraphicsUtility.getInternalIcon("icons/turtle.png"); }
			turtleIcon = GraphicsUtility.getTintedImage(turtleIcon, color);
			scaledTurtleImages.put(i, GraphicsUtility.toBufferedImage(GraphicsUtility.getScaledImage(turtleIcon, i)));
		}
	}
	
	public void clearMovements() {
		turtleMovementList.clear();
	}
	
	public void drawMovements(Graphics2D g, int w, int h) {
		if(turtleMovementList.size() == 0) return;
		int offsetX = TurtlesWorkspacePanel.getCurrentX();
		int offsetY = TurtlesWorkspacePanel.getCurrentY();
		
		for(Turtle_Movement move : turtleMovementList) {
			g.setStroke(new BasicStroke(TurtlesWorkspacePanel.scaledValue(penSize)));
			g.setColor(move.penColor);
			g.drawLine(
				(w / 2) + TurtlesWorkspacePanel.scaledValue(offsetX + (move.x1)),
				(h / 2) + TurtlesWorkspacePanel.scaledValue(offsetY + (move.y1)),
				(w / 2) + TurtlesWorkspacePanel.scaledValue(offsetX + (move.x2)),
				(h / 2) + TurtlesWorkspacePanel.scaledValue(offsetY + (move.y2))
			);
		}
	}
	
	public void drawTurtle(Graphics2D g) {
		if(turtleVisibility) {
			BufferedImage turtleIcon = scaledTurtleImages.get(TurtlesWorkspacePanel.getScale());
			
			int w = TurtlesWorkspacePanel.getBoundsWidth();
			int h = TurtlesWorkspacePanel.getBoundsHeight();
			
			int x = TurtlesWorkspacePanel.scaledValue(turtlePosX) + Math.round(w/2) + TurtlesWorkspacePanel.scaledValue(TurtlesWorkspacePanel.getCurrentX()) - Math.round(turtleIcon.getWidth() / 2);
			int y = TurtlesWorkspacePanel.scaledValue(turtlePosY) + Math.round(h/2) + TurtlesWorkspacePanel.scaledValue(TurtlesWorkspacePanel.getCurrentY()) - Math.round(turtleIcon.getHeight() / 2);
			
			
			AffineTransform identity = new AffineTransform();
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.translate(x, y);
			trans.rotate(Math.toRadians(turtleRotation), turtleIcon.getWidth() / 2, turtleIcon.getHeight() / 2);
			
			//g.drawImage(Graphics_Utilies.getScaledImage(turtleIcon, Turtles_Workspace_Area.getScale()), trans, null);
			g.drawImage(turtleIcon, trans, null);
		}
	}
	
	public ArrayList<Turtle_Movement> getAllTurtleMovements() {
		return turtleMovementList;
	}
	
	public int getMovementsCount() {
		return turtleMovementList.size();
	}
	
	public String getName() {
		return turtleName;
	}
	
	public boolean getPenVisibility() {
		return penVisibility;
	}
	
	public int[] getPointWithGivenRotation(int x1, int x2, int length, int rotation) {
		int newX = 0;
		int newY = 0;
	
		newX = (int) (x1 + Math.round((Math.cos(Math.toRadians(rotation)) * length)));
		newY = (int) (x2 + Math.round((Math.sin(Math.toRadians(rotation)) * length)));

		
		return new int[] {newX, newY};
	}
	
	public int getRotation() {
		return turtleRotation;
	}
	
	public Color getTurtleColor() {
		return turtleColor;
	}
	
	public boolean getTurtleVisibility() {
		return turtleVisibility;
	}
	
	public int getX() {
		return turtlePosX;
	}
	
	public int getY() {
		return turtlePosY;
	}
	
	public void move(int length) {
		int[] points = getPointWithGivenRotation(turtlePosX, turtlePosY, length, turtleRotation);
		
		int newX = points[0];
		int newY = points[1];
		
		if(penVisibility) {
			turtleMovementList.add(new Turtle_Movement(
				turtlePosX, 
				turtlePosY, 
				newX, 
				newY, 
				TurtlesWorkspacePanel.getSelectedPenColor()
			));
		}
		
		turtlePosX = newX;
		turtlePosY = newY;

	}
	
	public void rotate(int rot) {
		turtleRotation += rot;
		turtleRotation = (turtleRotation - ((turtleRotation / 360) * 360));
	}
	
	public void setMovements(ArrayList<Turtle_Movement> movements) {
		turtleMovementList = movements;
	}
	
	public void setName(String name) {
		turtleName = name;
	}
	
	public void setPenVisibility(boolean bool) {
		penVisibility = bool;
	}
	
	public void setTurtleColor(Color color) {
		turtleColor = color;
		for(double i = 0.25; i <= 2; i += 0.25) {
			BufferedImage turtleIcon = GraphicsUtility.getInternalIcon("icons/turtle.png");
			turtleIcon = GraphicsUtility.getTintedImage(turtleIcon, color);
			scaledTurtleImages.put(i, GraphicsUtility.toBufferedImage(GraphicsUtility.getScaledImage(turtleIcon, i)));
		}
	}
	
	public void setTurtleVisibility(boolean bool) {
		turtleVisibility = bool;
	}
	
	public void setX(int x) {
		turtlePosX = x;
	}
	
	public void setY(int y) {
		turtlePosY = y;
	}
	
	public static class Turtle_Movement {
		public int x1, x2, y1, y2;
		public Color penColor;
		
		public Turtle_Movement(int x1, int y1, int x2, int y2, Color penColor) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.penColor = penColor;
		}
	}
}


