package airteam.projects.atarilogo.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import airteam.projects.atarilogo.components.templates.ComboBox;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Turtle_Functions extends JPanel {
	private JLabel title = new JLabel("ZAREJESTROWANE FUNKCJE");
	
	@SuppressWarnings("unchecked")
	public Turtle_Functions() {
		setOpaque(false);
		setBorder(null);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("15dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu"),},
			new RowSpec[] {
				RowSpec.decode("20dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("23dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
    
    
    add(title, "3, 1, 3, 1, left, default");

	}
	
	public void paintComponent(Graphics g) {
		int w = (int) getBounds().getWidth();
		int h = (int) getBounds().getHeight();
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		g2d.setColor(new Color(255, 255, 255));
		g2d.setStroke(new BasicStroke(2));
		g2d.drawRoundRect(14, 16, w-30, h-30, 15, 15);
		
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(new Color(36, 47, 49));
		g2d.drawLine(34, 16, 49 + title.getBounds().width, 16);
	}

}
