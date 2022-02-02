package airteam.projects.atarilogo.utilities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	public static BufferedImage getScaledImage(BufferedImage image, double scale) {
		BufferedImage tmpImage = new BufferedImage((int) Math.round(image.getWidth() * scale), (int) Math.round(image.getHeight() * scale), 2);
		tmpImage.getGraphics().drawImage(image.getScaledInstance((int) Math.round(image.getWidth() * scale), (int) Math.round(image.getHeight() * scale), Image.SCALE_SMOOTH), 0, 0, null);
		tmpImage.getGraphics().dispose();
		return tmpImage;
	}
	
	public static Image getSizedImage(BufferedImage image, int width, int height) {
		final Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		final BufferedImage resized = new BufferedImage(width, height, 2);
		final Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(temp, 0, 0, null);
		g2d.dispose();
		return resized;
	}
	
	public static BufferedImage getInternalIcon(String imagePath) {
		try{
			return ImageIO.read(Graphics_Utilies.class.getResource("/airteam/projects/atarilogo/resources/" + imagePath));
	  } catch(Exception e) { Log_Utilies.logError("Wystapil blad przy pobieraniu ikonki: " + e.getMessage(), "Path do ikonki: " + "/airteam/projects/atarilogo/resources/" + imagePath); }
		
		Log_Utilies.logError("Zwrocono pustwa ikonke! ( /airteam/projects/atarilogo/resources/" + imagePath + " )");
		return null;
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    return bimage;
	}
	
}
