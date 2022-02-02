package airteam.projects.atarilogo.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class Sidebar_Panel extends JPanel {
	private Color gradient_color1 = new Color(56, 57, 59);
	private Color gradient_color2 = new Color(38, 38, 38);
	
	public static JPanel options = new Turtle_Options();
	public static JPanel functions = new Turtle_Functions();
	
	public static Sidebar_Panel instance;
	
	
	public Sidebar_Panel() {
		setBackground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("50px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px:grow"),}));
		
		add(options, "1, 1, fill, fill");
		add(functions, "1, 3, fill, fill");

	}
	
	public static int getBoundsWidth() {
		return instance.getBounds().width;
	}
	
	public static int getBoundsHeight() {
		return instance.getBounds().height;
	}
	
	public void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		Graphics_Utilies.setGradientPaint((Graphics2D) g, gradient_color1, gradient_color2, 0, h);
		g.fillRect(0, 0, w, h);
		
		g.setColor(new Color(255, 255, 255));
	}
}
