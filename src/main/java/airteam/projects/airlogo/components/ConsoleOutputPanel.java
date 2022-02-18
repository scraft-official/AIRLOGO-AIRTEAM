package airteam.projects.airlogo.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import airteam.projects.airlogo.components.templates.JScrollBarUI;
import airteam.projects.airlogo.utilities.GraphicsUtility;

import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class ConsoleOutputPanel extends JPanel {
	private static Color backgroundColor1 = new Color(242, 245, 247, 165);
	private static Color backgroundColor2 = new Color(237, 239, 240, 150);
	
	private static int shadowSize = 4;
	private static int borderSize = 1;
	private static int borderRadius = 15;
	
	private static boolean visibility = false;
	private static JPanel linesContainer = new JPanel();
	private static JScrollPane scrollComponent = new JScrollPane();
	private static ConsoleOutputPanel instance;
	
	private static Color infoColor = new Color(48, 48, 48);
	private static Color errorColor = new Color(230, 60, 60);
	private static Color userColor = new Color(60, 105, 230);
	
	private static Font font = new Font("Tahoma", Font.BOLD, 14);
	
	private static String logPrefix = "   >>   ";
	
	
	public ConsoleOutputPanel() {
		instance = this;
		
		setOpaque(false);
		setBorder(new EmptyBorder(8, 6, 5, 6));
		setVisible(visibility);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.setUI(new JScrollBarUI());
		scrollbar.setUnitIncrement(10);
		scrollbar.setPreferredSize(new Dimension(11, 48));
		scrollbar.setOpaque(false);
		scrollbar.setForeground(new Color(52, 145, 80));
		scrollbar.setBackground(new Color(57, 135, 80, 200));
	
		
		scrollComponent.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollComponent.getViewport().setOpaque(false);
		scrollComponent.setViewportView(linesContainer);
		scrollComponent.setOpaque(false);
		scrollComponent.setBorder(null);
		scrollComponent.setVerticalScrollBar(scrollbar);

		linesContainer.setOpaque(false);
		linesContainer.setLayout(new MigLayout("", "[grow,leading]", "[]"));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(scrollComponent);
	}
	
	public static void addInfoLog(String... messages) {
		for(String msg : messages) {
			JLabel line = new JLabel(logPrefix + msg);
			line.setForeground(infoColor);
			line.setOpaque(false);
			line.setFont(font);
			linesContainer.add(line, "cell 0 " + String.valueOf(9999 - linesContainer.getComponents().length));
		}
		if(!visibility) {
			changeVisibility();
			ConsoleInputPanel.refreshConsoleButton();
		}
	}
	
	public static void addErrorLog(String... messages) {
		for(String msg : messages) {
			JLabel line = new JLabel(logPrefix + msg);
			line.setForeground(errorColor);
			line.setOpaque(false);
			line.setFont(font);
			linesContainer.add(line, "cell 0 " + String.valueOf(9999 - linesContainer.getComponents().length));
		}
		if(!visibility) {
			changeVisibility();
			ConsoleInputPanel.refreshConsoleButton();
		}
		instance.revalidate();
		instance.repaint();
	}
	
	public static void addUserLog(String... messages) {
		for(String msg : messages) {
			JLabel line = new JLabel(logPrefix + msg);
			line.setForeground(userColor);
			line.setOpaque(false);
			line.setFont(font);
			linesContainer.add(line, "cell 0 " + String.valueOf(9999 - linesContainer.getComponents().length));
		}
		if(visibility) {
			instance.revalidate();
			instance.repaint();
		}
	}
	
	public static void addCustomColorLog(Color color, String... messages) {
		for(String msg : messages) {
			JLabel line = new JLabel(logPrefix + msg);
			line.setForeground(color);
			line.setOpaque(false);
			line.setFont(font);
			linesContainer.add(line, "cell 0 " + String.valueOf(9999 - linesContainer.getComponents().length));
		}
		if(!visibility) {
			changeVisibility();
			ConsoleInputPanel.refreshConsoleButton();
		}
		instance.revalidate();
		instance.repaint();
	}
	
	public static void changeVisibility() {
		if(visibility) visibility = false;
		else visibility = true;
		instance.setVisible(visibility);
	}
	
	public static boolean getVisbility() {
		return visibility;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getBounds().width;
		int h = getBounds().height;
		
		if(!visibility) return;
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		
		GraphicsUtility.drawRoundFadedBorder(g2d, new Color(0,0,0), shadowSize, 0, 0, w, h, borderRadius);
		GraphicsUtility.setGradientPaint(g2d, backgroundColor1, backgroundColor2, 0, h);
		
		g2d.fillRoundRect(shadowSize/2 + borderSize/2, shadowSize/2 + borderSize/2, w-shadowSize-borderSize, h-shadowSize-borderSize, borderRadius, borderRadius);
		g2d.dispose();
	}
}
