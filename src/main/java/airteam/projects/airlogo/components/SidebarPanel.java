package airteam.projects.airlogo.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.airlogo.utilities.GraphicsUtility;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {
	public static JPanel options = new TurtleOptionsPanel();
	public static JPanel functions = new TurtleFunctionsPanel();
	
	public static SidebarPanel instance;
	public static int getBoundsHeight() {
		return instance.getBounds().height;
	}
	
	public static int getBoundsWidth() {
		return instance.getBounds().width;
	}
	
	public SidebarPanel() {
		setBackground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("275px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px:grow"),}));
		
		add(options, "1, 1, fill, fill");
		add(functions, "1, 3, fill, fill");
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		GraphicsUtility.setGradientPaint((Graphics2D) g, new Color(56, 57, 59), new Color(38, 38, 38), 0, h);
		g.fillRect(0, 0, w, h);
	}
}
