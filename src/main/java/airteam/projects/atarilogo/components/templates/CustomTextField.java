package airteam.projects.atarilogo.components.templates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CustomTextField extends JTextField {
	private boolean mouseOver = false;
	private boolean isFocused = false;
	private boolean showRequiredHint = false;
	
	private String hintRequiredText = "* To pole jest wymagane!";
	
	private Color lineColor = new Color(129, 138, 132);
	private Color lineColorHover = new Color(55, 59, 56);
	private String title;
	
	public CustomTextField(String title) {
		this.title = title;
		
		setOpaque(false);
		setBorder(new EmptyBorder(20, 3, 10, 3));
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		addMouseListener( new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				mouseOver = true;
				repaint();
			}
			
			public void mouseExited(MouseEvent e) {
				mouseOver = false;
				repaint();
			}
		});
		
		addFocusListener(new FocusAdapter( ) {
			public void focusGained(FocusEvent e) {
				isFocused = true;
				repaint();
			}
			
			public void focusLost(FocusEvent e) {
				isFocused = false;
				repaint();
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
		int width = getWidth();
		int height = getHeight();
		if(mouseOver || isFocused) {
			g2d.setColor(lineColorHover);
			g2d.fillRect(2, height - 3, width - 4, height);
		} else {
			g2d.setColor(lineColor);
			g2d.fillRect(2, height - 2, width - 4, height);
		}
		
		drawHintText(g2d);
		g2d.dispose();
	}
	
	public void showRequiredHint(boolean show) {
		showRequiredHint = show;
	}
	
	public void setRquiredHint(String text) {
		hintRequiredText = text;
	}
	
	public void drawHintText(Graphics2D g2d) {
		Insets in = getInsets();
    g2d.setColor(new Color(120, 120, 120));
 
    double height = getHeight() - in.top - in.bottom;
		double textY;
		if(isFocused || !getText().equals("")) {
			g2d.setFont(new Font("Tahoma", Font.BOLD, 11));
			textY = (height - 18) / 2;
		}
    else {
    	g2d.setFont(new Font("Tahoma", Font.BOLD, 13));
    	textY = height;
    }
		
    FontMetrics ft = g2d.getFontMetrics();
    g2d.drawString(title, in.right, (int) (in.top + textY + ft.getAscent() - 18));
    
    if(showRequiredHint) {
    	g2d.setColor(new Color(209, 69, 67));
    	int posX;
    	if(isFocused || !getText().equals("")) {
    		posX = in.right + ft.stringWidth(title) + 10;
    	}  else {
    		posX = in.right;
    	}
			g2d.drawString(hintRequiredText, posX, (int) (in.top + ((height - 18) / 2) + ft.getAscent() - 18));
    }
	}
	 
	
}
