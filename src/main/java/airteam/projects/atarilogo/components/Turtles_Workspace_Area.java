package airteam.projects.atarilogo.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.atarilogo.commands.CommandManager;
import airteam.projects.atarilogo.components.templates.JSliderUI;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;

public class Turtles_Workspace_Area extends JPanel {
	private static ArrayList<Turtle> turtles = new ArrayList<>();
	
	private static int currentPosX = 0;
	private static int currentPosY = 0;
	
	private static int lastPosX = 0;
	private static int lastPosY = 0;
	
	private static double scale = 1;
	
	private static boolean mousePressed;
	
	private static int dotSize = 3;
	private static int dotSpacing = 20;
	
	private static Turtles_Workspace_Area instance;
	
	private static boolean forceRefresh;
	
	private static JLabel coordinates;
	private static JSlider scaleSlider;
	
	private BoardInfo lastBoardInfo;
	
	private static ArrayList<Integer> selectedTurtles;
	
	private static String[] listaAutorow = {"Stanislas", "Stanisław", "Kuba", "Konrad", "Marcin"};
	
	public Turtles_Workspace_Area() {
		setDoubleBuffered(false);
		setOpaque(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Turtles_Workspace_Area.instance = this;
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("left:10dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:150dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu"),},
			new RowSpec[] {
				RowSpec.decode("10dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:200px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("8dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:0px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:85px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("30dlu"),
				RowSpec.decode("15dlu"),}));
		
		JLabel logo = new JLabel(new ImageIcon(Graphics_Utilies.getSizedImage(
				(BufferedImage) Graphics_Utilies.getInternalIcon("icons/logo.png"), 180, 180)
		));
		
		scaleSlider = new JSlider();
		scaleSlider.setUI(new JSliderUI(scaleSlider));
		scaleSlider.setForeground(new Color(52, 145, 80));
		scaleSlider.setBackground(new Color(180, 180, 180));
		scaleSlider.setValue(100);
		scaleSlider.setSnapToTicks(true);
		scaleSlider.setMinorTickSpacing(25);
		scaleSlider.setMinimum(50);
		scaleSlider.setMaximum(200);
		scaleSlider.setMajorTickSpacing(25);
		scaleSlider.setOpaque(false);
		scaleSlider.setOrientation(SwingConstants.VERTICAL);
		
		JLabel scaleTopText = new JLabel("2.0x");
		scaleTopText.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel scaleBottomText = new JLabel("0.5x");
		scaleBottomText.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel consolePanel = new Console_Input();
		
		
		coordinates = new JLabel("X: 2331    Y: 322   ");
		coordinates.setFont(new Font("Tahoma", Font.BOLD, 13));
		coordinates.setForeground(new Color(53, 103, 196));
		
		add(scaleBottomText, "10, 4, 4, 3, center, default");
		add(scaleTopText, "10, 1, 4, 2, center, default");
		
		JPanel consoleOutput = new Console_Output();
		
		add(consoleOutput, "4, 9, 9, 1, fill, fill");
		add(coordinates, "9, 12, 5, 1, right, center");
		add(scaleSlider, "10, 3, 4, 1, default, fill");
		add(consolePanel, "4, 11, 9, 1, fill, fill");
		add(logo, "2, 2, 4, 2, left, top");
		
		implementListeners();
		CommandManager.initializeMathEngine();
	}
	
	public static Turtle addTurtle(String name, Color color) {
		int id = turtles.size();
		
		Turtle t = new Turtle(0, 0, name, color);
		
		Turtles_Workspace_Area.turtles.add(t);
		Turtles_Workspace_Area.selectTurtle(id, false);
		
		if(id != 0) forceRefresh(true, true);
		return t;
	}
	
	public static void removeTurtle(Turtle turtle) {
		Turtles_Workspace_Area.turtles.remove(turtle);
		Turtles_Workspace_Area.selectTurtle(turtles.size()-1, false);
		forceRefresh(true, true);
	}
	
	public static void removeTurtle(int id) {
		Turtles_Workspace_Area.turtles.remove(id);
		Turtles_Workspace_Area.selectTurtle(turtles.size()-1, false);
		forceRefresh(true, true);
	}
	
	public static Turtle getTurtle(int id) {
		Turtle t = Turtles_Workspace_Area.turtles.get(id);
		if(t == null) Log_Utilies.logError("POPROSZONO O ZOLWIA KTORY NIE ISTNIEJE!");
		return t;
	}
	
	public static ArrayList<Turtle> getAllTurtles() {
		return turtles;
	}
	
	public static void clearWorkspace() {
		for(Turtle t : turtles) {
			t.setX(0);
			t.setY(0);
			t.clearMovements();
			t.rotate((360 - t.getRotation()));
			t.setTurtleVisibility(true);
			t.setPenVisibility(true);
			currentPosX = 0;
			currentPosY = 0;
		}
		
	}
	
	public void implementListeners() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePressed = true;
				lastPosX = e.getPoint().x;
				lastPosY = e.getPoint().y;
			}
			
			public void mouseReleased(MouseEvent e) {
				mousePressed = false;
				
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!mousePressed) return;
				
				currentPosX = (int) (currentPosX - Math.round((lastPosX - e.getPoint().x) / scale));
				currentPosY = (int) (currentPosY - Math.round((lastPosY - e.getPoint().y) / scale));
				lastPosX = e.getPoint().x;
				lastPosY = e.getPoint().y;
				
				repaint(200);
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(mousePressed) return;
				int w = getWidth();
				int h = getHeight();
				
				int x = (int) ((w/2));
				int y = (int) ((h/2));
				
				x =+ (int) Math.round((w - e.getPoint().x - (w/2)) / scale) + currentPosX;
				y =+ (int) Math.round((h - e.getPoint().y - (h/2)) / scale) + currentPosY;
				
				coordinates.setText("X: " + -x + "    Y: "+ y +"   ");
				
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() < 0) {
					scale = scale + 0.25;
					if(scale > 2) {
						scale = 2;
						return;
					}
				
				}
				else if(e.getWheelRotation() > 0) {
					scale = scale - 0.25;
					if(scale < 0.5) {
						scale = 0.5;
						return;
					}
				}
				
				scaleSlider.setValue((int) (scale * 100));
				
				int w = getWidth();
				int h = getHeight();
				
				int x = (int) ((w/2));
				int y = (int) ((h/2));
				
				x =+ (int) Math.round((w - e.getPoint().x - (w/2)) / scale) + currentPosX;
				y =+ (int) Math.round((h - e.getPoint().y - (h/2)) / scale) + currentPosY;
				
				coordinates.setText("X: " + -x + "    Y: "+ y +"   ");
			}
		});
		
		scaleSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
      	if(Math.floorMod(scaleSlider.getValue(), 25) == 0)
      		scale = (scaleSlider.getValue()/100d);
      		refresh();
      }
    });
	}
	
	
	public static int scaledValue(int value) {
		return (int) Math.round(value * scale);
	}
	
	public static void refresh() {
		instance.repaint();
	}
	
	public static void forceRefresh(boolean repaintWorkspace, boolean refreshSidebar) {
		forceRefresh = true;
		if(repaintWorkspace) instance.repaint();
		if(refreshSidebar) {
			Turtle_Options.refreshAll();
		}
	}
	
	public static void selectTurtle(ArrayList<Integer> ids, boolean refreshOptions) {
		for(int id : ids) {
			if(id > (turtles.size()-1)) {
				for(int i = turtles.size(); i <= id; i++) {
					Random randomName = new Random();
					int index = randomName.nextInt(listaAutorow.length-1);
					String name = listaAutorow[index];
					
					Random randomIdentifier = new Random();
					
					while(existTurtle(name)) {
						name = (listaAutorow[index] + "-" + String.valueOf(randomIdentifier.nextInt(9999)));
					}
					
					//TODO poprawic nazwy powtarzajacego sie zolwia
					
					Random randomColor = new Random();
					final float hue = randomColor.nextFloat();
					final float saturation = (randomColor.nextInt(2000) + 5000) / 10000f;
					final float luminance = 0.65f;
					Color color = Color.getHSBColor(hue, saturation, luminance);
					
					Turtles_Workspace_Area.addTurtle(listaAutorow[index], color);
				}
			}
		}
		
		selectedTurtles = new ArrayList<>();
		selectedTurtles.addAll(ids);
		
		Turtle_Options.setSelectedTurtle(turtles.get(ids.get(0)));
		
		if(refreshOptions) {
			Turtle_Options.refreshSelected();
		}
	}
	
	public static void selectTurtle(int id, boolean refreshOptions) {
		if(id > (turtles.size()-1)) {
			for(int i = turtles.size(); i <= id; i++) {
				Random randomName = new Random();
				int index = randomName.nextInt(listaAutorow.length-1);
				String name = listaAutorow[index];
				
				Random randomIdentifier = new Random();
				
				while(existTurtle(name)) {
					name = (listaAutorow[index] + "-" + String.valueOf(randomIdentifier.nextInt(9999)));
				}
				
				Random randomColor = new Random();
				final float hue = randomColor.nextFloat();
				final float saturation = (randomColor.nextInt(2000) + 5000) / 10000f;
				final float luminance = 0.65f;
				Color color = Color.getHSBColor(hue, saturation, luminance);
				
				Turtles_Workspace_Area.addTurtle(listaAutorow[index], color);
			}
		}
		
		selectedTurtles = new ArrayList<>();
		selectedTurtles.add(id);
		
		Turtle_Options.setSelectedTurtle(turtles.get(id));
		
		if(refreshOptions) {
			Turtle_Options.refreshSelected();
		}
	}
	
	public static boolean existTurtle(String name) {
		for(Turtle t : turtles) {
			if(t.getName().equals(name)) return true;
		}
		return false;
	}
	
	public void paintComponent(Graphics g) {
		//Log_Utilies.logInfo("ZRESETOWANO WORKSPACE ZOLWIA: " + System.currentTimeMillis());
		int w = getWidth();
		int h = getHeight();
		
		int drawingCount = 0;
		for(Turtle t : turtles) {
			drawingCount += t.getMovementsCount();
		}
		
		if(!forceRefresh) {
			if(lastBoardInfo != null && lastBoardInfo.equals(w, h, currentPosX, currentPosY, scale, drawingCount)) {
				//Log_Utilies.logInfo("Nie wykryto zmian na planszy, zatrzymuje renderowanie!");
				g.drawImage(lastBoardInfo.getBufferedImage(), 0, 0, null);
				return;
			}
		} else forceRefresh = false;
		
		BufferedImage tmpImage = new BufferedImage(w, h, 2);
		Graphics2D tmpGraphics = (Graphics2D) tmpImage.getGraphics();
		
		tmpGraphics.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		
		Graphics_Utilies.setGradientPaint(tmpGraphics, new Color(225, 225, 225), new Color(231, 231, 231), w, h);
		tmpGraphics.fillRect(0, 0, w, h);
		
		tmpGraphics.setColor(new Color(173, 173, 173));
    
    for (int x = scaledValue(Math.floorMod(currentPosX, dotSpacing + (dotSize/2))); x < w; x += scaledValue(dotSpacing)) {
        for (int y = scaledValue(Math.floorMod(currentPosY, dotSpacing + (dotSize/2))); y < h; y += scaledValue(dotSpacing)) {
        	tmpGraphics.fillOval(x  - (dotSize/2), y  - (dotSize/2), scaledValue(dotSize), scaledValue(dotSize));
        }
    }

    for(Turtle t : turtles) {
    	t.drawMovements(tmpGraphics, w, h);
    }
    for(Turtle t : turtles) {
    	t.drawTurtle(tmpGraphics);
    }
    
    tmpGraphics.dispose();
    g.drawImage(tmpImage, 0, 0, null);
    lastBoardInfo = new BoardInfo(w, h, currentPosX, currentPosY, scale, drawingCount, tmpImage);
	}
	
	public static void setPosX(int x) {
		currentPosX = -x;
	}
	
public static void setPosY(int y) {
		currentPosY = -y;
	}
	
	
	public static int getCurrentX() {
		return currentPosX;
	}
	
	public static int getCurrentY() {
		return currentPosY;
	}
	
	public static int getBoundsWidth() {
		return instance.getBounds().width;
	}
	
	public static int getBoundsHeight() {
		return instance.getBounds().height;
	}
	
	public static double getScale() {
		return scale;
	}
	
	public static ArrayList<Integer> getSelectedTurtlesID() {
		return selectedTurtles;
	}
	
	public static ArrayList<Turtle> getSelectedTurtles() {
		ArrayList<Turtle> selection = new ArrayList<>();
		
		for(int id : selectedTurtles) {
			selection.add(turtles.get(id));
		}
		
		return selection;
	}
	
	public static Turtles_Workspace_Area getInstance() {
		return instance;
	}
	
	public class BoardInfo {
		private int offsetX;
		private int offsetY;
		private int drawingCount;
		private double scale;
		private int height;
		private int width;
		private BufferedImage bufferedImage;
		
		public BoardInfo(int width, int height, int offsetX, int offsetY, double scale, int drawingCount, BufferedImage image) {
			this.height = height;
			this.width = width;
			this.offsetX = offsetX;
			this.offsetY = offsetY;
			this.scale = scale;
			this.drawingCount = drawingCount;
			
			this.bufferedImage = image;

		}
		
		public boolean equals(int width, int height, int offsetX, int offsetY, double scale, int drawingCount) {
			if(this.height == height && 
				this.width == width && 
				this.offsetX == offsetX &&
				this.offsetY == offsetY &&
				this.scale == scale &&
				this.drawingCount == drawingCount) 
				return true;
			return false;
		}
		
		public BufferedImage getBufferedImage() {
			return bufferedImage;
		}
		
	}
	
}
