package airteam.projects.atarilogo.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;

public class Turtles_Workspace_Area extends JPanel {
	private static HashMap<Integer, Turtle> turtles = new HashMap<>();
	private static HashMap<Turtle, ArrayList<String>> turtlesCommands = new HashMap<>();
	private static int lastTurtleID = 0;
	
	private static int currentPosX = 0;
	private static int currentPosY = 0;
	
	private static int lastPosX = 0;
	private static int lastPosY = 0;
	
	private static double scale = 1;
	
	private static boolean pressed;
	
	public Turtles_Workspace_Area() {
		int w = getBounds().width;
		int h = getBounds().height;
		
		implementListeners();
		
	}
	
	public void implementListeners() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressed = true;
				lastPosX = e.getPoint().x;
				lastPosY = e.getPoint().y;
			}
			
			public void mouseReleased(MouseEvent e) {
				pressed = false;
				
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!pressed) return;
				
				currentPosX = currentPosX - (int) Math.round((lastPosX - e.getPoint().x) / scale);
				currentPosY = currentPosY - (int) Math.round((lastPosY - e.getPoint().y) / scale);
				lastPosX = e.getPoint().x;
				lastPosY = e.getPoint().y;
				
				Log_Utilies.logInfo(" CR X: " + currentPosX + " CR Y: " + currentPosY);
				Log_Utilies.logInfo(" P X: " + e.getPoint().x + " P Y: " + e.getPoint().y);
				repaint();
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() < 0) scale = scale + 0.25;
				else if(e.getWheelRotation() > 0) scale = scale - 0.25;
				if(scale < 0.5) scale = 0.5;
				if(scale > 2) scale = 2;
				repaint();
			}
		});
	}
	
	public int scaledValue(int value) {
		return (int) Math.round(value * scale);
	}
	
	public void paintComponent(Graphics g) {
		int w = getBounds().width;
		int h = getBounds().height;
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		
		Graphics_Utilies.setGradientPaint(g2d, new Color(225, 225, 225), new Color(231, 231, 231), w, h);
		g2d.fillRect(0, 0, w, h);
		
    g2d.setColor(new Color(173, 173, 173));
    for (int x = scaledValue(Math.floorMod(currentPosX, 10)); x < w; x += scaledValue(20)) {
        for (int y = scaledValue(Math.floorMod(currentPosY, 10)); y < h; y += scaledValue(20)) {
            g2d.fillOval(x, y, scaledValue(3), scaledValue(3));
        }
    }
    
    BufferedImage icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/airteam/projects/atarilogo/resources/icons/turtle_icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image icon2 = icon.getScaledInstance(scaledValue(icon.getWidth()), scaledValue(icon.getHeight()), Image.SCALE_SMOOTH);
    g2d.drawImage(icon2, scaledValue(currentPosX + 300), scaledValue(currentPosY + 300), null);
    
    
    
	}
	
}
