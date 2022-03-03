package airteam.projects.airlogo;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.airlogo.components.ConsoleOutputPanel;
import airteam.projects.airlogo.components.SidebarPanel;
import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.components.dialogs.popups.IntroPopup;
import airteam.projects.airlogo.components.dialogs.popups.SavePopup;
import airteam.projects.airlogo.utilities.GraphicsUtility;

import java.awt.Dimension;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class AirLogo extends JFrame {
	private static AirLogo AtariLogo;
	private static int fps = 50;
	
	public AirLogo(boolean showApp) {
		setIconImage(GraphicsUtility.getSizedImage((BufferedImage) GraphicsUtility.getInternalIcon("icons/app-icon.png"), 250, 250));
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("center:350px"),
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				RowSpec.decode("10px:grow"),}));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
	    public void windowClosing(WindowEvent e) {
				new SavePopup();
		}});
		setPreferredSize(new Dimension(1280, 720));
		setMinimumSize(new Dimension(1280, 720));
		setBounds(100, 100, 1278, 718);
		setTitle("AIRLOGO - AIRTEAM");
		
		getContentPane().add(new TurtlesWorkspacePanel(fps), "2, 1, fill, fill");
		getContentPane().add(new SidebarPanel(), "1, 1, fill, fill");	
		
		ConsoleOutputPanel.addUserLog("===============================================","MIŁEJ ZABAWY!","LISTĘ WSZYSTKICH POLECEŃ ZNAJDZIESZ POD KOMENDĄ \"HELP\"","WITAJ W APLIKACJI AIRLOGO OD AIRTEAM!","===============================================");
		
		setVisible(showApp);
	}
	
	public static AirLogo getFrame() {
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
				try { AtariLogo = new AirLogo(true); new IntroPopup();} 
				catch (Exception e) { e.printStackTrace();}
			}
		});
	}
}
