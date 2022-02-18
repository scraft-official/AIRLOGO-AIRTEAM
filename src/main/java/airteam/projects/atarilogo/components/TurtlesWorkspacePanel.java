package airteam.projects.atarilogo.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.atarilogo.commands.CommandManager;
import airteam.projects.atarilogo.functions.FunctionManager;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.GraphicsUtility;
import airteam.projects.atarilogo.utilities.LogUtility;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class TurtlesWorkspacePanel extends JPanel {
	private static ArrayList<Turtle> turtles = new ArrayList<>();
	private static int cameraPosX = 0;
	
	private static int cameraPosY = 0;
	private static int lastPosX = 0;
	
	private static int lastPosY = 0;
	
	private static double scale = 1;
	private static int mouseCoordsX;
	private static int mouseCoordsY;
	
	private static boolean mousePressed;
	private static Color[] pens = {new Color(43, 43, 42), new Color(207, 88, 70), new Color(48, 121, 161)};
	
	private static int selectedPenID = 0;
	private static int dotSize = 3;
	
	private static int dotSpacing = 20;
	
	private static TurtlesWorkspacePanel instance;
	private static boolean forceRefresh;
	
	private static JSlider scaleSlider = new WorkspaceScaleSlider();
	
	private static BoardInfo lastBoardInfo;
	
	private static ArrayList<Integer> selectedTurtles;
	
	private static String[] authorsList = {"Stanislas", "Stanisław", "Kuba", "Konrad", "Marcin"};
	
	public static Turtle addTurtle(String name, Color color) {
		int id = turtles.size();
		
		Turtle t = new Turtle(0, 0, name, color);
		TurtlesWorkspacePanel.turtles.add(t);
		TurtlesWorkspacePanel.selectTurtle(id, false);
		
		if(id != 0) forceRefresh(true, true);
		return t;
	}
	
	public static void clearWorkspace() {
		for(Turtle t : turtles) {
			t.setX(0);
			t.setY(0);
			t.clearMovements();
			t.rotate((360 - t.getRotation()));
			t.setTurtleVisibility(true);
			t.setPenVisibility(true);
			cameraPosX = 0;
			cameraPosY = 0;
		}
	}
	
	public static boolean existTurtle(String name) {
		for(Turtle t : turtles) {
			if(t.getName().equals(name)) return true;
		}
		return false;
	}
	
	public static void forceRefresh(boolean repaintWorkspace, boolean refreshSidebar) {
		if(repaintWorkspace) forceRefresh = true;
		if(refreshSidebar) TurtleOptionsPanel.refreshAll();
		if(refreshSidebar) TurtleFunctionsPanel.refreshAll();
	}
	
	public static Color[] getAllPens() {
		return pens;
	}
	
	public static ArrayList<Turtle> getAllTurtles() {
		return turtles;
	}
	
	public static int getBoundsHeight() {
		return instance.getBounds().height;
	}
	
	public static int getBoundsWidth() {
		return instance.getBounds().width;
	}
	
	public static int getCurrentX() {
		return cameraPosX;
	}
	
	public static int getCurrentY() {
		return cameraPosY;
	}
	
	public static TurtlesWorkspacePanel getInstance() {
		return instance;
	}
	
	public static double getScale() {
		return scale;
	}
	
	public static Color getSelectedPenColor() {
		return pens[selectedPenID];
	}
	
	public static ArrayList<Turtle> getSelectedTurtles() {
		ArrayList<Turtle> selection = new ArrayList<>();
		
		for(int id : selectedTurtles) {
			selection.add(turtles.get(id));
		}
		
		return selection;
	}
	
	public static ArrayList<Integer> getSelectedTurtlesID() {
		return selectedTurtles;
	}
	
	public static Turtle getTurtle(int id) {
		Turtle t = TurtlesWorkspacePanel.turtles.get(id);
		if(t == null) LogUtility.logError("POPROSZONO O ZOLWIA KTORY NIE ISTNIEJE!");
		return t;
	}
	
	public static BufferedImage getWorkspaceImage() {
		return lastBoardInfo.getBufferedImage();
	}
	
	public static void removeTurtle(int id) {
		TurtlesWorkspacePanel.turtles.remove(id);
		TurtlesWorkspacePanel.selectTurtle(turtles.size()-1, false);
		forceRefresh(true, true);
	}
	
	public static void removeTurtle(Turtle turtle) {
		TurtlesWorkspacePanel.turtles.remove(turtle);
		TurtlesWorkspacePanel.selectTurtle(turtles.size()-1, false);
		forceRefresh(true, true);
	}
	
	
	public static int scaledValue(int value) {
		return (int) Math.round(value * scale);
	}
	
	public static void selectPenID(int id) {
		selectedPenID = id;
	}
	
	public static void selectTurtle(ArrayList<Integer> ids, boolean refreshOptions) {
		for(int id : ids) {
			if(id > (turtles.size()-1)) {
				for(int i = turtles.size(); i <= id; i++) {
					Random randomName = new Random();
					int index = randomName.nextInt(authorsList.length);
					String name = authorsList[index].toUpperCase() + "-" + i;
					
					Random randomIdentifier = new Random();
					
					while(existTurtle(name)) {
						name = name + String.valueOf(randomIdentifier.nextInt(9999));
					}
					
					Random randomColor = new Random();
					final float hue = randomColor.nextFloat();
					final float saturation = (randomColor.nextInt(2000) + 5000) / 10000f;
					final float luminance = 0.65f;
					Color color = Color.getHSBColor(hue, saturation, luminance);
					
					TurtlesWorkspacePanel.addTurtle(name, color);
				}
			}
		}
		
		selectedTurtles = new ArrayList<>();
		selectedTurtles.addAll(ids);
		
		TurtleOptionsPanel.setSelectedTurtle(turtles.get(ids.get(0)));
		
		if(refreshOptions) {
			TurtleOptionsPanel.refreshSelected();
		}
	}
	
	public static void selectTurtle(int id, boolean refreshOptions) {
		if(id > (turtles.size()-1)) {
			for(int i = turtles.size(); i <= id; i++) {
				Random randomName = new Random();
				int index = randomName.nextInt(authorsList.length);
				String name = authorsList[index].toUpperCase() + "-" + i;
				
				Random randomIdentifier = new Random();
				
				while(existTurtle(name)) {
					name = name + String.valueOf(randomIdentifier.nextInt(9999));
				}
				
				Random randomColor = new Random();
				final float hue = randomColor.nextFloat();
				final float saturation = (randomColor.nextInt(2000) + 5000) / 10000f;
				final float luminance = 0.65f;
				Color color = Color.getHSBColor(hue, saturation, luminance);
				
				TurtlesWorkspacePanel.addTurtle(name, color);
			}
		}
		
		selectedTurtles = new ArrayList<>();
		selectedTurtles.add(id);
		
		TurtleOptionsPanel.setSelectedTurtle(turtles.get(id));
		
		if(refreshOptions) {
			TurtleOptionsPanel.refreshSelected();
		}
	}
	
	public static void setPenColor(int id, Color color) {
		pens[id] = color;
	}
	
	public static void setPosX(int x) {
		cameraPosX = -x;
	}
	
	public static void setPosY(int y) {
			cameraPosY = -y;
	}
	
	public static void setTurtlesList(ArrayList<Turtle> t) {
		turtles = t;
	}
	
	public TurtlesWorkspacePanel(int fps) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setDoubleBuffered(false);
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("left:10dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:100dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("2dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(200dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu"),},
			new RowSpec[] {
				RowSpec.decode("15px"),
				RowSpec.decode("6px"),
				RowSpec.decode("25px"),
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
		
		FunctionManager.registerDefaultFunctions();
		CommandManager.initializeMathEngine();
		TurtlesWorkspacePanel.instance = this;
		
		JLabel scaleTopText = new JLabel("2.0x");
		scaleTopText.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel scaleBottomText = new JLabel("0.5x");
		scaleBottomText.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		add(scaleBottomText, "14, 6, 4, 3, center, default");
		add(scaleTopText, "14, 1, 4, 2, center, default");
		add(scaleSlider, "14, 3, 4, 3, default, fill");
		
		add(new ConsoleOutputPanel(), "4, 11, 13, 1, fill, fill");
		add(new ConsoleInputPanel(), "4, 13, 13, 1, fill, fill");
		add(new TurtleToolBarPanel(), "9, 1, 1, 3, fill, fill");
		add(new JLabel(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/logo.png"), 180, 180))), "2, 2, 4, 4, left, top");
		
		addTurtle("PODSTAWOWY", new Color(20, 186, 150));
		
		implementListeners();
		startRendering(fps);
	}
	
	public void drawCoordsText(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setFont(new Font("Tahoma", Font.BOLD, 12));
		g2d.setColor(new Color(53, 103, 196));
		
		FontMetrics ft = g2d.getFontMetrics();
	  g2d.drawString("X: " + mouseCoordsX + " Y: " + mouseCoordsY, getBounds().width - ft.stringWidth("X: " + mouseCoordsX + " Y: " + mouseCoordsY) - 10, getBounds().height - 10);
	}
	
	public void implementListeners() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePressed = true;
				lastPosX = e.getPoint().x;
				lastPosY = e.getPoint().y;
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed = false;
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!mousePressed) return;
				
				cameraPosX = (int) (cameraPosX - Math.round((lastPosX - e.getPoint().x) / scale));
				cameraPosY = (int) (cameraPosY - Math.round((lastPosY - e.getPoint().y) / scale));
				lastPosX = e.getPoint().x;
				lastPosY = e.getPoint().y;
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(mousePressed) return;
				int w = getWidth();
				int h = getHeight();
				
				int x = ((w/2));
				int y = ((h/2));
				
				x =+ (int) Math.round((w - e.getPoint().x - (w/2)) / scale) + cameraPosX;
				y =+ (int) Math.round((h - e.getPoint().y - (h/2)) / scale) + cameraPosY;
				
				mouseCoordsX = -x;
				mouseCoordsY = y;
				
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			@Override
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
				
				int x = ((w/2));
				int y = ((h/2));
				
				x =+ (int) Math.round((w - e.getPoint().x - (w/2)) / scale) + cameraPosX;
				y =+ (int) Math.round((h - e.getPoint().y - (h/2)) / scale) + cameraPosY;
				
				mouseCoordsX = -x;
				mouseCoordsY = y;
				
			}
		});
		
		scaleSlider.addChangeListener(new ChangeListener() {
      @Override
			public void stateChanged(ChangeEvent e) {
      	if(Math.floorMod(scaleSlider.getValue(), 25) == 0)
      		scale = (scaleSlider.getValue()/100d);
      }
    });
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		
		int drawingCount = 0;
		for(Turtle t : turtles) {
			drawingCount += t.getMovementsCount();
		}
		
		if(!forceRefresh) {
			if(lastBoardInfo != null && lastBoardInfo.equals(w, h, cameraPosX, cameraPosY, scale, drawingCount)) {
				g.drawImage(lastBoardInfo.getBufferedImage(), 0, 0, null);
				drawCoordsText((Graphics2D) g); 
				return;
			}
		} else forceRefresh = false;
		
		BufferedImage tmpImage = new BufferedImage(w, h, 2);
		Graphics2D tmpGraphics = (Graphics2D) tmpImage.getGraphics();
		
		tmpGraphics.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		
		GraphicsUtility.setGradientPaint(tmpGraphics, new Color(225, 225, 225), new Color(231, 231, 231), w, h);
		tmpGraphics.fillRect(0, 0, w, h);
		
		tmpGraphics.setColor(new Color(173, 173, 173));
    
    for (int x = scaledValue(Math.floorMod(cameraPosX, dotSpacing + (dotSize/2))); x < w; x += scaledValue(dotSpacing)) {
        for (int y = scaledValue(Math.floorMod(cameraPosY, dotSpacing + (dotSize/2))); y < h; y += scaledValue(dotSpacing)) {
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
    drawCoordsText((Graphics2D) g);
    lastBoardInfo = new BoardInfo(w, h, cameraPosX, cameraPosY, scale, drawingCount, tmpImage);
	}
	
	public void startRendering(int fps) {
		Thread thread = new Thread(new Runnable() {
    	@Override
			public void run() {
        long delay = 1000/fps;
        boolean render = true;
        while(render) {
        	try { Thread.sleep(delay); } catch(InterruptedException e) { render = false; break;}
        	repaint();
    }}});
		
    thread.setPriority(Thread.MAX_PRIORITY);
    thread.start();
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
