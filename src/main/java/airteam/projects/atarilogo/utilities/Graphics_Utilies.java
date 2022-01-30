package airteam.projects.atarilogo.utilities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class Graphics_Utilies {
	public static void setGradientPaint(Graphics2D g, Color startColor, Color endColor, int w, int h) {
		g.setPaint(new GradientPaint(0, 0, startColor, w, h, endColor));
	}
	
	public static void drawFadedBorder(Graphics2D g, Color shadowColor, int shadowSize, int x, int y, int w, int h) {
		for (int i = 0; i < shadowSize; i++) {
      g.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), ((80 / shadowSize) * i)));
      g.drawRect(i+1, i+1, w - ((i * 2) + 1), h - ((i * 2) + 1));
		}
		g.clearRect(x + shadowSize, y + shadowSize, w - shadowSize, h - shadowSize);
	}
	
	public static void drawRoundFadedBorder(Graphics2D g, Color shadowColor, int shadowSize, int x, int y, int w, int h, int borderRadius) {
		for (int i = 0; i < shadowSize; i++) {
      g.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), ((80 / shadowSize) * i)));
      g.drawRoundRect(i+1, i+1, w - ((i * 2) + 1), h - ((i * 2) + 1), borderRadius, borderRadius);
		}
	}
	
}
