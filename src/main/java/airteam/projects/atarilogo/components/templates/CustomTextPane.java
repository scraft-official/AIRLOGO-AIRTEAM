package airteam.projects.atarilogo.components.templates;

import java.awt.BasicStroke;
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

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class CustomTextPane extends JTextArea {
	private boolean mouseOver = false;
	private boolean isFocused = false;
	private boolean showRequiredHint = false;
	
	private String hintRequiredText = "* To pole jest wymagane!";
	
	private Color lineColor = new Color(129, 138, 132);
	private Color lineColorHover = new Color(55, 59, 56);
	private String title;
	
	public CustomTextPane() {
		this.title = title;
		
		setBorder(new EmptyBorder(15, 13, 15, 13));
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		setBackground(new Color(232, 231, 225));
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
	
}
