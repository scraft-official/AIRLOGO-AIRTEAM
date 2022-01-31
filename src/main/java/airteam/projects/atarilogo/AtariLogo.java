package airteam.projects.atarilogo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import airteam.projects.atarilogo.components.Console_Input;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.components.templates.JSliderUI;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Dimension;
import com.jgoodies.forms.layout.Sizes;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseWheelEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class AtariLogo extends JFrame {
	private Color gradient_color1 = new Color(56, 57, 59);
	private Color gradient_color2 = new Color(38, 38, 38);
	
	private static JPanel appPanel;
	private JTextField textField;
	
	
	public AtariLogo() {
		setIconImage(Graphics_Utilies.getSizedImage(
				(BufferedImage) Graphics_Utilies.getInternalIcon("icons/app-icon.png"), 250, 250)
		);
		setTitle("AIRTEAM - ATARILOGO");
		appPanel = new JPanel() {
			public void paintComponent(Graphics g) {		
				int w = getWidth();
				int h = getHeight();
				Graphics_Utilies.setGradientPaint((Graphics2D) g, gradient_color1, gradient_color2, 0, h);
				g.fillRect(0, 0, w, h);
		}};
		
		FormLayout appLayout = new FormLayout(new ColumnSpec[] {
				new ColumnSpec(ColumnSpec.CENTER, Sizes.bounded(Sizes.MINIMUM, Sizes.constant("20dlu", true), Sizes.constant("1000dlu", true)), 1),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(320dlu;min):grow(2)"),},
			new RowSpec[] {
				RowSpec.decode("10px:grow"),});
		
		appPanel.setLayout(appLayout);
		
		JPanel workspacePanel = new Turtles_Workspace_Area();
		workspacePanel.setName("");
		
		workspacePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		JPanel consolePanel = new Console_Component();
//		workspacePanel.add(consolePanel);
		
		appPanel.add(workspacePanel, "3, 1, fill, fill");
		
		this.getContentPane().add(appPanel);
		this.setMinimumSize(new Dimension(1280, 720));
		this.setPreferredSize(new Dimension(1280, 720));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setVisible(true);
		
		((Turtles_Workspace_Area) workspacePanel).addTurtle();
	}
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AtariLogo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
