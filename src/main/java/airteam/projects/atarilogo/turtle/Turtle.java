package airteam.projects.atarilogo.turtle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;

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
			if(turtleName.equals("KAMIL") || turtleName.equals("KB"))  { turtleIcon = (BufferedImage) Graphics_Utilies.getInternalIcon("icons/turtle_kamil.png"); }
			else { turtleIcon = (BufferedImage) Graphics_Utilies.getInternalIcon("icons/turtle.png"); }
			turtleIcon = Graphics_Utilies.getTintedImage(turtleIcon, color);
			scaledTurtleImages.put(i, Graphics_Utilies.toBufferedImage(Graphics_Utilies.getScaledImage(turtleIcon, i)));
		}
	}
	
	public void rotate(int rot) {
		turtleRotation += rot;
		turtleRotation = (turtleRotation - ((turtleRotation / 360) * 360));
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
				Turtles_Workspace_Area.getSelectedPenColor()
			));
		}
		
		turtlePosX = newX;
		turtlePosY = newY;

	}
	
	public int[] getPointWithGivenRotation(int x1, int x2, int length, int rotation) {
		int newX = 0;
		int newY = 0;
	
		newX = (int) (x1 + Math.round((Math.cos(Math.toRadians(rotation)) * length)));
		newY = (int) (x2 + Math.round((Math.sin(Math.toRadians(rotation)) * length)));

		
		return new int[] {newX, newY};
	}
	
	public int getX() {
		return turtlePosX;
	}
	
	public int getY() {
		return turtlePosY;
	}
	
	public int getMovementsCount() {
		return turtleMovementList.size();
	}
	
	public void setX(int x) {
		turtlePosX = x;
	}
	
	public void setY(int y) {
		turtlePosY = y;
	}
	
	public int getRotation() {
		return turtleRotation;
	}
	
	public String getName() {
		return turtleName;
	}
	
	public Color getTurtleColor() {
		return turtleColor;
	}
	
	public boolean getTurtleVisibility() {
		return turtleVisibility;
	}
	
	public boolean getPenVisibility() {
		return penVisibility;
	}
	
	public ArrayList<Turtle_Movement> getAllTurtleMovements() {
		return turtleMovementList;
	}
	
	public void setMovements(ArrayList<Turtle_Movement> movements) {
		turtleMovementList = movements;
	}
	
	public void setTurtleColor(Color color) {
		turtleColor = color;
		for(double i = 0.25; i <= 2; i += 0.25) {
			BufferedImage turtleIcon = (BufferedImage) Graphics_Utilies.getInternalIcon("icons/turtle.png");
			turtleIcon = Graphics_Utilies.getTintedImage(turtleIcon, color);
			scaledTurtleImages.put(i, Graphics_Utilies.toBufferedImage(Graphics_Utilies.getScaledImage(turtleIcon, i)));
		}
	}
	
	public void setPenVisibility(boolean bool) {
		penVisibility = bool;
	}
	
	public void setTurtleVisibility(boolean bool) {
		turtleVisibility = bool;
	}
	
	public void setName(String name) {
		turtleName = name;
	}
	
	public void drawTurtle(Graphics2D g) {
		if(turtleVisibility) {
			BufferedImage turtleIcon = scaledTurtleImages.get(Turtles_Workspace_Area.getScale());
			
			int w = Turtles_Workspace_Area.getBoundsWidth();
			int h = Turtles_Workspace_Area.getBoundsHeight();
			
			int x = Turtles_Workspace_Area.scaledValue(turtlePosX) + Math.round(w/2) + Turtles_Workspace_Area.scaledValue(Turtles_Workspace_Area.getCurrentX()) - Math.round(turtleIcon.getWidth() / 2);
			int y = Turtles_Workspace_Area.scaledValue(turtlePosY) + Math.round(h/2) + Turtles_Workspace_Area.scaledValue(Turtles_Workspace_Area.getCurrentY()) - Math.round(turtleIcon.getHeight() / 2);
			
			
			AffineTransform identity = new AffineTransform();
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.translate(x, y);
			trans.rotate(Math.toRadians(turtleRotation), turtleIcon.getWidth() / 2, turtleIcon.getHeight() / 2);
			
			//g.drawImage(Graphics_Utilies.getScaledImage(turtleIcon, Turtles_Workspace_Area.getScale()), trans, null);
			g.drawImage(turtleIcon, trans, null);
		}
	}
	
	public void clearMovements() {
		turtleMovementList.clear();
	}
	
	public void drawMovements(Graphics2D g, int w, int h) {
		if(turtleMovementList.size() == 0) return;
		int offsetX = Turtles_Workspace_Area.getCurrentX();
		int offsetY = Turtles_Workspace_Area.getCurrentY();
		
		for(Turtle_Movement move : turtleMovementList) {
			g.setStroke(new BasicStroke(Turtles_Workspace_Area.scaledValue(penSize)));
			g.setColor(move.penColor);
			g.drawLine(
				(int) ((w / 2) + Turtles_Workspace_Area.scaledValue(offsetX + (move.x1))),
				(int) ((h / 2) + Turtles_Workspace_Area.scaledValue(offsetY + (move.y1))),
				(int) ((w / 2) + Turtles_Workspace_Area.scaledValue(offsetX + (move.x2))),
				(int) ((h / 2) + Turtles_Workspace_Area.scaledValue(offsetY + (move.y2)))
		);
			
		}
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


