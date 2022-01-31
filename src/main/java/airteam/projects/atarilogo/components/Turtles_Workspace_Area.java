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
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

import airteam.projects.atarilogo.components.templates.JSliderUI;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.Dimension;

public class Turtles_Workspace_Area extends JPanel {
	private static HashMap<Integer, Turtle> turtles = new HashMap<>();
	private static int lastTurtleID = 0;
	
	private static int currentPosX = 0;
	private static int currentPosY = 0;
	
	private static int lastPosX = 0;
	private static int lastPosY = 0;
	
	private static int lastHeight = 0;
	private static int lastWidth = 0;
	
	private static double scale = 1;
	
	private static boolean mousePressed;
	
	private static int drawingBoardOffsetX, drawingBoardOffsetY;
	private static BufferedImage drawingBoardImage;
	private static Graphics2D drawingBoardGraphics;
	
	private static int dotSize = 3;
	private static int dotSpacing = 20;
	
	private static Turtles_Workspace_Area instance;
	
	private static boolean needToRefresh;
	
	private static JLabel coordinates;
	private static JSlider scaleSlider;
	
	
	public Turtles_Workspace_Area() {
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
		consolePanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		consolePanel.setBorder(new EmptyBorder(2, 15, 3, 15));
		consolePanel.setOpaque(false);
		consolePanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("163px"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("3px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40px"),},
			new RowSpec[] {
				RowSpec.decode("fill:default:grow"),}));
		
		
		JLabel lblNewLabel = new JLabel("WYSLIJ POLECENIE »  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		consolePanel.add(lblNewLabel, "1, 1, 2, 1, left, fill");
		
		JTextField textField = new JTextField();
		textField.setOpaque(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBorder(null);
		textField.setColumns(7);
		consolePanel.add(textField, "3, 1, fill, fill");
		
		textField.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				Log_Utilies.logInfo(textField.getText());
				
				Turtle t = Turtles_Workspace_Area.getTurtle(0);
				t.move(Integer.valueOf(textField.getText()));
				t.rotate(45);
				Turtles_Workspace_Area.refresh(true);
				textField.setText("");
      }});
		
		
		coordinates = new JLabel("X: 2331    Y: 322   ");
		coordinates.setFont(new Font("Tahoma", Font.BOLD, 13));
		coordinates.setForeground(new Color(53, 103, 196));
		
		add(scaleBottomText, "10, 4, 4, 3, center, default");
		add(scaleTopText, "10, 1, 4, 2, center, default");
		
		JPanel consoleOutput = new Console_Output();
		consoleOutput.setOpaque(false);
		add(consoleOutput, "4, 9, 9, 1, fill, fill");
		add(coordinates, "9, 12, 5, 1, right, center");
		add(scaleSlider, "10, 3, 4, 1, default, fill");
		add(consolePanel, "4, 11, 9, 1, fill, fill");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setPreferredSize(new Dimension(0, 0));
		separator.setForeground(new Color(52, 145, 80));
		separator.setBackground(new Color(52, 145, 80));
		consolePanel.add(separator, "4, 1, 2, 1");
		
		JLabel consoleButton = new JLabel(new ImageIcon(
				Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/paper-icon.png"), 28, 28) 
		));
		
		ImageIcon consoleOn = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/paper-icon-full.png"), 28, 28));
		ImageIcon consoleOff = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/paper-icon.png"), 28, 28));
		
		consoleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Log_Utilies.logInfo("KLIKNIETO");
				Console_Output.changeVisbility();
				if(Console_Output.getVisbility()) consoleButton.setIcon(consoleOn);
				else consoleButton.setIcon(consoleOff);
				consoleOutput.repaint();
			}
		});
		consoleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		consolePanel.add(consoleButton, "6, 1, center, default");
		add(logo, "2, 2, 4, 2, left, top");
		
		implementListeners();
	}
	
	public Turtle addTurtle() {
		Turtle t = new Turtle(0, 0);
		Turtles_Workspace_Area.turtles.put(lastTurtleID++, t);
		return t;
	}
	
	public static Turtle getTurtle(int id) {
		Turtle t = Turtles_Workspace_Area.turtles.get(id);
		if(t == null) Log_Utilies.logError("POPROSZONO O ZOLWIA KTORY NIE ISTNIEJE!");
		return t;
	}
	
	public void createDrawingBoard() {
		int w = getBounds().width;
		int h = getBounds().height;
		
		Turtles_Workspace_Area.drawingBoardImage = new BufferedImage(w, h, 2);
		Turtles_Workspace_Area.drawingBoardGraphics = drawingBoardImage.createGraphics();
		
		drawingBoardOffsetX = drawingBoardOffsetY = 0;
		
		Log_Utilies.logInfo("drawingBoardOffsetX " + drawingBoardOffsetX, "drawingBoardOffsetY " + drawingBoardOffsetY);
	}
	
	public static void resizeDrawingBoard(int addWidth, int addHeight) {
		Log_Utilies.logInfo("Zwiekszono border mapy: X " + addWidth + " Y " + addHeight);
		
		BufferedImage image = new BufferedImage(Math.abs(addWidth) + drawingBoardImage.getWidth(), Math.abs(addHeight) + drawingBoardImage.getHeight(), 2);
		Graphics2D tmpGraphics = image.createGraphics();
		int x = 0;
		int y = 0;
		
		if(addWidth < 0) {
			x = Math.abs(addWidth);
			drawingBoardOffsetX =- x;
		}
		if(addHeight < 0) {
			y = Math.abs(addHeight);
			drawingBoardOffsetY =- y;
		}
		tmpGraphics.drawImage(drawingBoardImage, x, y, null);
		
		Turtles_Workspace_Area.drawingBoardImage = image;
		Turtles_Workspace_Area.drawingBoardGraphics = image.createGraphics();
				
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
				
				refresh(true);
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
				int w = getBounds().width;
				int h = getBounds().height;
				
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
				
				int w = getBounds().width;
				int h = getBounds().height;
				
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
      		refresh(true);
      }
    });
	}
	
	
	public static int scaledValue(int value) {
		return (int) Math.round(value * scale);
	}
	
	public static void refresh(boolean doRepaint) {
		needToRefresh = true;
		if(doRepaint) instance.repaint();
	}
	
	public void paintComponent(Graphics g) {
		//Log_Utilies.logInfo("ZRESETOWANO WORKSPACE ZOLWIA");
		int w = getBounds().width;
		int h = getBounds().height;
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		
		Graphics_Utilies.setGradientPaint(g2d, new Color(225, 225, 225), new Color(231, 231, 231), w, h);
		g2d.fillRect(0, 0, w, h);
		
    g2d.setColor(new Color(173, 173, 173));
    
    for (int x = scaledValue(Math.floorMod(currentPosX, dotSpacing + (dotSize/2))); x < w; x += scaledValue(dotSpacing)) {
        for (int y = scaledValue(Math.floorMod(currentPosY, dotSpacing + (dotSize/2))); y < h; y += scaledValue(dotSpacing)) {
            g2d.fillOval(x  - (dotSize/2), y  - (dotSize/2), scaledValue(dotSize), scaledValue(dotSize));
        }
    }
    
//    if(drawingBoardImage == null) {
//    	createDrawingBoard();
//    }
//    
//    drawingBoardGraphics.dispose();
//    drawingBoardGraphics = drawingBoardImage.createGraphics();  
//    g2d.drawImage(Graphics_Utilies.getScaledImage(drawingBoardImage, scale), scaledValue(currentPosX + drawingBoardOffsetX), scaledValue(currentPosY + drawingBoardOffsetY), null);

    for(Turtle t : turtles.values()) {
    	t.drawMovements(g2d, w, h);
    	t.drawTurtle(g2d);
    }
    
	}
	
	public static Graphics2D getDrawingGraphics() {
		return drawingBoardGraphics;
	}
	
	public static BufferedImage getDrawingImage() {
		return drawingBoardImage;
	}
	
	public static int getDrawingHeight() {
		return drawingBoardImage.getHeight();
	}
	
	public static int getDrawingWidth() {
		return drawingBoardImage.getWidth();
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
	
	public static int getOffsetX() {
		return drawingBoardOffsetX;
	}
	
	public static int getOffsetY() {
		return drawingBoardOffsetY;
	}
	
	public static Turtles_Workspace_Area getInstance() {
		return instance;
	}
	
}
