package airteam.projects.atarilogo.turtle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import airteam.projects.atarilogo.utilities.Log_Utilies;

public class Turtle {
	private int ROTATION;
	private int POS_X;
	private int POS_Y;
	
	private int PEN_SIZE;
	private boolean PEN_SHOWN;
	private Color PEN_COLOR;
	
	private boolean TURTLE_SHOWN;
	private Color TURTLE_COLOR;
	
	private BufferedImage TURTLE_ICON;
	
	public Turtle(int x, int y) {
		this.POS_X = x;
		this.POS_Y = y;
		this.ROTATION = 0;
		this.TURTLE_SHOWN = true;
		this.PEN_SHOWN = true;
		this.PEN_COLOR = new Color(69, 67, 61);
		this.PEN_SIZE = 3;
		
		try{
			TURTLE_ICON = ImageIO.read(getClass().getResource("/airteam/atarilogo/resources/icons/turtle_icon.png"));
	    } catch(IOException e) { e.printStackTrace(); }
	      catch(Exception e) { e.printStackTrace(); }
	}
	
	public void ROTATE(int rot) {
		ROTATION += rot;
		ROTATION = (ROTATION - ((ROTATION / 360) * 360));
	}
	
//	public void MOVE(int length) {
//		Graphics2D g = TURTLE_WORKSPACE_AREA.AREA_IMAGE_G2D;
//		
//		int[] points = GET_POINTS_WITH_ROTATION(POS_X, POS_Y, length, this.ROTATION);
//		
//		int newX = points[0];
//		int newY = points[1];
//		
//		if(PEN_SHOWN) {
//			g.setColor(PEN_COLOR);
//			g.setStroke(new BasicStroke(PEN_SIZE));
//			g.drawLine(POS_X, POS_Y, newX, newY);
//			g.rotate(0);
//		}
//		
//		if(newY > TURTLE_WORKSPACE_AREA.HEIGHT || newY < 0 || newX < 0 || newX > TURTLE_WORKSPACE_AREA.WIDTH) {
//			do {
//				if(newY > TURTLE_WORKSPACE_AREA.HEIGHT) {
//					newY = newY - TURTLE_WORKSPACE_AREA.HEIGHT;
//					POS_Y = POS_Y - TURTLE_WORKSPACE_AREA.HEIGHT;
//				}
//				if(newY < 0) {
//					newY = newY + TURTLE_WORKSPACE_AREA.HEIGHT;
//					POS_Y = POS_Y + TURTLE_WORKSPACE_AREA.HEIGHT;
//				}
//				if(newX < 0) {
//					newX = newX + TURTLE_WORKSPACE_AREA.WIDTH;
//					POS_X = POS_X + TURTLE_WORKSPACE_AREA.WIDTH;
//				}
//				if(newX > TURTLE_WORKSPACE_AREA.WIDTH) {
//					newX = newX - TURTLE_WORKSPACE_AREA.WIDTH;
//					POS_X = POS_X - TURTLE_WORKSPACE_AREA.WIDTH;
//				}
//				g.setColor(PEN_COLOR);
//				g.setStroke(new BasicStroke(PEN_SIZE));
//				g.drawLine(POS_X, POS_Y, newX, newY);
//				g.rotate(0);
//			
//				LOGGING_UTIL.debug(String.valueOf("POS_X " + POS_X + " POS_Y " + POS_Y + " NEW_X " + newX + " NEW_Y" + newY));
//			
//			} while(newY > TURTLE_WORKSPACE_AREA.HEIGHT || newY < 0 || newX < 0 || newX > TURTLE_WORKSPACE_AREA.WIDTH);
//				//
//				//LOGGING_UTIL.debug("PORUSZAM ZNOWU " + String.valueOf((int) Math.round(length - Math.sqrt((newY * newY) + (newX * newX)))));
//				//MOVE((int) Math.round(length - Math.sqrt((newY * newY) + (newX * newX))));
//		}
//		POS_X = newX;
//		POS_Y = newY;
//
//	}
	
	public int[] GET_POINTS_WITH_ROTATION(int x1, int x2, int length, int rotation) {
		int newX = 0;
		int newY = 0;
	
		newX = (int) (x1 + Math.round((Math.cos(Math.toRadians(rotation)) * length)));
		newY = (int) (x2 + Math.round((Math.sin(Math.toRadians(rotation)) * length)));

		
		return new int[] {newX, newY};
	}
	
	public int GET_X() {
		return POS_X;
	}
	
	public int GET_Y() {
		return POS_Y;
	}
	
	public void SET_X(int x) {
		POS_X = x;
	}
	
	public void SET_Y(int y) {
		POS_Y = y;
	}
	
	public void SET_PEN_SIZE(int size) {
		PEN_SIZE = size;
	}
	
	public int GET_ROTATION() {
		return ROTATION;
	}
	
	public void CHANGE_PEN_VISIBILITY(boolean bool) {
		PEN_SHOWN = bool;
	}
	
	public void CHANGE_VISIBILITY(boolean bool) {
		TURTLE_SHOWN = bool;
	}
	
	public void DRAW_TURTLE(Graphics2D g) {
		Log_Utilies.logInfo(String.valueOf(POS_X), String.valueOf(POS_Y));
		if(TURTLE_SHOWN) {
			Log_Utilies.logInfo(String.valueOf(ROTATION));
			AffineTransform identity = new AffineTransform();
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.translate(POS_X - TURTLE_ICON.getWidth() / 2, POS_Y - TURTLE_ICON.getWidth() / 2);
			trans.rotate(Math.toRadians(ROTATION), TURTLE_ICON.getWidth() / 2, TURTLE_ICON.getHeight() / 2);
			
			g.drawImage(TURTLE_ICON, trans, null);
		}
	}
	
}

