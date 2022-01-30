package airteam.projects.atarilogo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import airteam.projects.atarilogo.components.Console_Component;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
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
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AtariLogo extends JFrame {
	private Color gradient_color1 = new Color(56, 57, 59);
	private Color gradient_color2 = new Color(38, 38, 38);
	
	private static JPanel appPanel;
	private JTextField textField;
	
	
	public AtariLogo() {
		appPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				Log_Utilies.logInfo("Stworzono panel z gradientem!");
				
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
		
		workspacePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		JPanel consolePanel = new Console_Component();
//		workspacePanel.add(consolePanel);
		
		appPanel.add(workspacePanel, "3, 1, fill, fill");
		workspacePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("left:max(20dlu;pref)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu"),},
			new RowSpec[] {
				RowSpec.decode("10dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:0px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:0px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("30dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("10dlu"),}));
		
		JSlider slider = new JSlider();
		slider.setName("");
		slider.setPaintLabels(true);
		slider.setValue(100);
		slider.setSnapToTicks(true);
		slider.setMinorTickSpacing(25);
		slider.setMinimum(50);
		slider.setMaximum(200);
		slider.setMajorTickSpacing(25);
		slider.setOpaque(false);
		slider.setOrientation(SwingConstants.VERTICAL);
		workspacePanel.add(slider, "4, 3, 4, 1");
		
		JPanel consolePanel = new Console_Component();
		consolePanel.setBorder(new EmptyBorder(0, 15, 0, 15));
		consolePanel.setOpaque(false);
		workspacePanel.add(consolePanel, "3, 7, 4, 1, fill, fill");
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("WYSLIJ POLECENIE »  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		consolePanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBorder(null);
		textField.setOpaque(false);
		consolePanel.add(textField);
		textField.setColumns(7);
		
		this.getContentPane().add(appPanel);
		this.setMinimumSize(new Dimension(1280, 720));
		this.setPreferredSize(new Dimension(1280, 720));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
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
