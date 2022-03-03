package airteam.projects.airlogo.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.airlogo.commands.CMD_HELP;
import airteam.projects.airlogo.components.templates.CustomButtonUI;
import airteam.projects.airlogo.savemanager.SaveManager;
import airteam.projects.airlogo.utilities.GraphicsUtility;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class TurtleToolBarPanel extends JPanel {
	public TurtleToolBarPanel() {
		setBorder(new EmptyBorder(7, 11, 10, 11));
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("31px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("31px"),},
			new RowSpec[] {
				RowSpec.decode("fill:default:grow"),}));
		
		JButton buttonSave = new JButton("ZAPISZ PLANSZĘ");
		buttonSave.setUI(new CustomButtonUI());
		buttonSave.setForeground(new Color(250,250,250));
		buttonSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonSave.setBackground(new Color(77, 163, 100));
		buttonSave.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/save-icon.png"), 12, 12)));
		buttonSave.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	SaveManager.saveWorkspace();
      }
    });
    
		buttonSave.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	buttonSave.setBackground(new Color(45, 150, 73));
		    repaint();
		  }

		  @Override
		  public void mouseExited(MouseEvent me) {
		  	buttonSave.setBackground(new Color(77, 163, 100));
		    repaint();
		  }
    });
		
		add(buttonSave, "1, 1");
		
		JButton buttonImport = new JButton("IMPORTUJ PLANSZĘ");
		buttonImport.setUI(new CustomButtonUI());
		buttonImport.setForeground(new Color(250,250,250));
		buttonImport.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonImport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonImport.setBackground(new Color(27, 117, 207));
		buttonImport.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/import-icon.png"), 13, 13)));
		buttonImport.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	SaveManager.importWorkspace(null);
      }
    });
    
		buttonImport.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	buttonImport.setBackground(new Color(28, 102, 176));
		    repaint();
		  }

		  @Override
		  public void mouseExited(MouseEvent me) {
		  	buttonImport.setBackground(new Color(27, 117, 207));
		    repaint();
		  }
    });
		
		add(buttonImport, "3, 1");
		
		JButton buttonScreenShoot = new JButton("");
		buttonScreenShoot.setUI(new CustomButtonUI());
		buttonScreenShoot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonScreenShoot.setBackground(new Color(201, 129, 60));
		buttonScreenShoot.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/camera-icon.png"), 16, 16)));
		buttonScreenShoot.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	SaveManager.saveWorkspaceImage();
      }
    });
    
		buttonScreenShoot.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	buttonScreenShoot.setBackground(new Color(191, 104, 46));
		    repaint();
		  }

		  @Override
		  public void mouseExited(MouseEvent me) {
		  	buttonScreenShoot.setBackground(new Color(201, 129, 60));
		    repaint();
		  }
    });
		
		add(buttonScreenShoot, "5, 1");
		
		JButton buttonInfo = new JButton("");
		buttonInfo.setUI(new CustomButtonUI());
		buttonInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonInfo.setBackground(new Color(245, 245, 245));
		buttonInfo.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/info-icon.png"), 17, 17)));
		buttonInfo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	try { CMD_HELP.execute("HELP"); } catch(Exception ex) {}
      }
    });
    
		buttonInfo.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	buttonInfo.setBackground(new Color(225, 225, 225));
		    repaint();
		  }

		  @Override
		  public void mouseExited(MouseEvent me) {
		  	buttonInfo.setBackground(new Color(245, 245, 245));
		    repaint();
		  }
    });
		
		add(buttonInfo, "7, 1");
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		GraphicsUtility.drawRoundFadedBorder(g2d, new Color(0,0,0), 4, 0, -10, w, h+10, 10);
		
		GraphicsUtility.setGradientPaint(g2d, new Color(56, 57, 59), new Color(38, 38, 38), 0, h);
		g2d.fillRoundRect(5, -10, w-8, h+7, 10, 10);
	}
}
