package airteam.projects.atarilogo;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import airteam.projects.atarilogo.components.SidebarPanel;
import airteam.projects.atarilogo.components.TurtlesWorkspacePanel;
import airteam.projects.atarilogo.components.dialogs.CustomDialogFrame;
import airteam.projects.atarilogo.components.dialogs.popups.AddNewFunctionPopup;
import airteam.projects.atarilogo.components.dialogs.popups.SavePopup;
import airteam.projects.atarilogo.functions.FunctionManager;
import airteam.projects.atarilogo.savemanager.SaveManager;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Dimension;
import com.jgoodies.forms.layout.Sizes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class AtariLogo extends JFrame {
	private static AtariLogo AtariLogo;
	private static int fps = 50;
	
	public AtariLogo() {
		setTitle("AIRLOGO - AIRTEAM");

		setIconImage(Graphics_Utilies.getSizedImage(
				(BufferedImage) Graphics_Utilies.getInternalIcon("icons/app-icon.png"), 250, 250)
		);
		
		FormLayout appLayout = new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("center:350px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("10px:grow"),});
		
		TurtlesWorkspacePanel workspacePanel = new TurtlesWorkspacePanel(fps);
		workspacePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		TurtlesWorkspacePanel.addTurtle("PODSTAWOWY", new Color(20, 186, 150));
		
		
		FunctionManager.registerDefaultFunctions();
		
		JPanel sidebarPanel = new SidebarPanel();
		
		getContentPane().setLayout(appLayout);
		getContentPane().add(sidebarPanel, "1, 1, fill, fill");
		getContentPane().add(workspacePanel, "2, 1, fill, fill");
		
		setMinimumSize(new Dimension(1280, 720));
		setPreferredSize(new Dimension(1280, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1278, 718);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
	    public void windowClosing(WindowEvent e) {
				new SavePopup();
			}
		});
	}
	
	public static AtariLogo getFrame() {
		return AtariLogo;
	}
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
//		System.setProperty("sun.java2d.ddscale", "true");
//		System.setProperty("sun.java2d.translaccel", "true");
		
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtariLogo = new AtariLogo();
					//new AddNewFunctionPopup();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
