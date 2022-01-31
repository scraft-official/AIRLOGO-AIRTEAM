package airteam.projects.atarilogo.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import airteam.projects.atarilogo.utilities.Graphics_Utilies;

@SuppressWarnings("serial")
public class Console_Output extends JPanel {
	private static Color backgroundColor1 = new Color(242, 245, 247, 165);
	private static Color backgroundColor2 = new Color(237, 239, 240, 150);
	
	private static int shadowSize = 4;
	private static int borderSize = 1;
	private static int borderRadius = 15;
	
	private static boolean visibility = false;
	
	
	public Console_Output() {
		
	}
	
	public static boolean getVisbility() {
		return visibility;
	}
	
	public static void changeVisbility() {
		if(visibility) visibility = false;
		else visibility = true;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getBounds().width;
		int h = getBounds().height;
		
		if(!visibility) return;
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		
		Graphics_Utilies.drawRoundFadedBorder(g2d, new Color(0,0,0), shadowSize, 0, 0, w, h, borderRadius);
		Graphics_Utilies.setGradientPaint(g2d, backgroundColor1, backgroundColor2, 0, h);
		
		g2d.fillRoundRect(shadowSize/2 + borderSize/2, shadowSize/2 + borderSize/2, w-shadowSize-borderSize, h-shadowSize-borderSize, borderRadius, borderRadius);
		g2d.dispose();
	}
}
