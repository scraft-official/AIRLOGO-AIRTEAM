package airteam.projects.atarilogo.turtle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;

public class Turtle {
	private int turtleRotation;
	private int turtlePosX;
	private int turtlePosY;
	private Color turtleColor;
	private BufferedImage turtleIcon;
	private boolean turtleVisibility;
	
	private int penSize;
	private boolean penVisibility;
	private Color penColor;
	
	private ArrayList<Turtle_Movement> turtleMovementList = new ArrayList<>();
	
	
	public Turtle(int x, int y) {
		turtlePosX = x;
		turtlePosY = y;
		turtleRotation = 0;
		turtleVisibility = true;
		
		penVisibility = true;
		penColor = new Color(69, 67, 61);
		penSize = 3;
		
		turtleIcon = (BufferedImage) Graphics_Utilies.getInternalIcon("icons/turtle_icon.png");
	}
	
	public void rotate(int rot) {
		turtleRotation += rot;
		turtleRotation = (turtleRotation - ((turtleRotation / 360) * 360));
	}
	
	public void move(int length) {
		int[] points = getPointWithGivenRotation(turtlePosX, turtlePosY, length, turtleRotation);
		
		int newX = points[0];
		int newY = points[1];
	
		turtleMovementList.add(new Turtle_Movement(
				turtlePosX, 
				turtlePosY, 
				newX, 
				newY, 
				penSize, 
				penColor
		));
		
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
	
	public void setPenSize(int size) {
		penSize = size;
	}
	
	public int getRotation() {
		return turtleRotation;
	}
	
	public void setPenVisibility(boolean bool) {
		penVisibility = bool;
	}
	
	public void setTurtleVisibility(boolean bool) {
		turtleVisibility = bool;
	}
	
	public void drawTurtle(Graphics2D g) {
		if(turtleVisibility) {
			int w = Turtles_Workspace_Area.getBoundsWidth();
			int h = Turtles_Workspace_Area.getBoundsHeight();
			int x = Turtles_Workspace_Area.scaledValue(turtlePosX) + Math.round(w/2) + Turtles_Workspace_Area.scaledValue(Turtles_Workspace_Area.getCurrentX() - Math.round(turtleIcon.getWidth() / 2));
			int y = Turtles_Workspace_Area.scaledValue(turtlePosY) + Math.round(h/2) + Turtles_Workspace_Area.scaledValue(Turtles_Workspace_Area.getCurrentY() - Math.round(turtleIcon.getHeight() / 2));
			
			AffineTransform identity = new AffineTransform();
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.translate(x, y);
			trans.rotate(Math.toRadians(turtleRotation), Turtles_Workspace_Area.scaledValue(turtleIcon.getWidth() / 2), Turtles_Workspace_Area.scaledValue(turtleIcon.getHeight() / 2));
			
			g.drawImage(Graphics_Utilies.getScaledImage(turtleIcon, Turtles_Workspace_Area.getScale()), trans, null);
		}
	}
	
	public void drawMovements(Graphics2D g, int w, int h) {
		if(turtleMovementList.size() == 0) return;
		int offsetX = Turtles_Workspace_Area.getCurrentX();
		int offsetY = Turtles_Workspace_Area.getCurrentY();
		
		for(Turtle_Movement move : turtleMovementList) {
			g.setStroke(new BasicStroke(Turtles_Workspace_Area.scaledValue(move.penSize)));
			g.setColor(move.penColor);
			
			
			g.drawLine(
				(int) ((w / 2) + Turtles_Workspace_Area.scaledValue(offsetX + (move.x1))),
				(int) ((h / 2) + Turtles_Workspace_Area.scaledValue(offsetY + (move.y1))),
				(int) ((w / 2) + Turtles_Workspace_Area.scaledValue(offsetX + (move.x2))),
				(int) ((h / 2) + Turtles_Workspace_Area.scaledValue(offsetY + (move.y2)))
		);
			
		}
	}
	
	public class Turtle_Movement {
		public int x1, x2, y1, y2, penSize;
		public Color penColor;
		
		Turtle_Movement(int x1, int y1, int x2, int y2, int penSize, Color penColor) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.penSize = penSize;
			this.penColor = penColor;
		}
	}
}


