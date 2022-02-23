package airteam.projects.airlogo.components;

import java.awt.BasicStroke;
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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.airlogo.components.dialogs.popups.AddNewTurtlePopup;
import airteam.projects.airlogo.components.dialogs.popups.DeleteTurtlePopup;
import airteam.projects.airlogo.components.dialogs.popups.EditTurtlePopup;
import airteam.projects.airlogo.components.templates.CustomComboBox;
import airteam.projects.airlogo.components.templates.CustomButtonUI;
import airteam.projects.airlogo.turtle.Turtle;
import airteam.projects.airlogo.utilities.GraphicsUtility;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.swing.JButton;


@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class TurtleOptionsPanel extends JPanel {
	private static CustomComboBox combobox = new CustomComboBox();
	
	private static JButton buttonHideTurtle = new JButton();
	
	private static ImageIcon showIcon = new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/show-icon.png"), 14, 14));
	private static ImageIcon hideIcon = new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/hide-icon.png"), 14, 14));
	private static JButton buttonHidePen = new JButton();
	
	private static ImageIcon penIconOn = new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/pen-on.png"), 16, 16));
	private static ImageIcon penIconOff = new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/pen-off.png"), 16, 16));
	private static JButton buttonEditTurtle = new JButton();
	
	private static ImageIcon settingsIcon = new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/settings-icon.png"), 16, 16));
	private static JButton buttonTeleport = new JButton();
	
	private static ImageIcon teleportIcon = new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/move-icon.png"), 16, 16));
	private static JButton buttonDelete = new JButton();

	
	private static ImageIcon deleteIconOn = new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/bin-icon.png"), 14, 14));
	private static ImageIcon deleteIconOff = new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/bin-icon-darker.png"), 14, 14));
	private static Turtle selectedTurtle;

	public static void refreshAll() {
		refreshSelected();
		refreshButtons();
	}
	
	public static void refreshButtons() {
		if(selectedTurtle.getTurtleVisibility()) {
			buttonHideTurtle.setText("UKRYJ ŻÓŁWIA");
			buttonHideTurtle.setIcon(hideIcon);
		} else {
			buttonHideTurtle.setText("POKAŻ ŻÓŁWIA");
			buttonHideTurtle.setIcon(showIcon);
		}
		
		if(selectedTurtle.getPenVisibility()) {
			buttonHidePen.setText("PODNIEŚ PISAK");
			buttonHidePen.setIcon(penIconOff);
		} else {
			buttonHidePen.setText("OPUŚĆ PISAK");
			buttonHidePen.setIcon(penIconOn);
		}
		if(TurtlesWorkspacePanel.getAllTurtles().get(0) == selectedTurtle) {
			CustomButtonUI ui = (CustomButtonUI) buttonDelete.getUI();
			ui.allowClick(false);
			
			buttonDelete.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			buttonDelete.setBackground(new Color(143, 64, 64));
	    buttonDelete.setForeground(new Color(173, 165, 165));
	    buttonDelete.setIcon(deleteIconOff);
	    
		}
		else {
			CustomButtonUI ui = (CustomButtonUI) buttonDelete.getUI();
			ui.allowClick(true);
			
			buttonDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			buttonDelete.setBackground(new Color(209, 85, 69));
	    buttonDelete.setForeground(new Color(255, 255, 255));
	    buttonDelete.setIcon(deleteIconOn);
		}
	}
	
	public static void refreshSelected() {
		ArrayList<String> turtleNames = new ArrayList<>();
		
		int selectedID = 0;
		int i = 0;
		
		for(Turtle t : TurtlesWorkspacePanel.getAllTurtles()) {
			if(selectedTurtle == t) { selectedID = i; }
			turtleNames.add(t.getName() + " (" + (i) + ")");
			i++;
		}
		combobox.setModel(new DefaultComboBoxModel(turtleNames.toArray()));
		combobox.setSelectedIndex(selectedID);
	}
	
	public static void setSelectedTurtle(Turtle t) {
		selectedTurtle = t;
	}
	
	private JLabel title = new JLabel(" USTAWIENIA ŻÓŁWIA");
	
	public TurtleOptionsPanel() {
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
				RowSpec.decode("15dlu"),
				RowSpec.decode("23dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:20dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:20dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:20dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:20dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:20dlu"),}));
		
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		
    combobox.setTitleText("WYBRANY ŻÓŁW");
    combobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    combobox.setMaximumRowCount(4);
    combobox.addPopupMenuListener(new PopupMenuListener() {
      @Override
      public void popupMenuCanceled(PopupMenuEvent pme) {
      }

      @Override
      public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
      	TurtlesWorkspacePanel.selectTurtle(combobox.getSelectedIndex(), false); 
    		refreshButtons();
      }
      
      @Override
      public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
      }
    });

    refreshSelected();
    
    JLabel addIcon = new JLabel("");
    addIcon.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/add-icon.png"), 18, 18)));
    addIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    addIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new AddNewTurtlePopup();
			}
		});
   
    buttonHideTurtle.setUI(new CustomButtonUI());
    buttonHideTurtle.setFont(new Font("Tahoma", Font.BOLD, 11));
    buttonHideTurtle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    buttonHideTurtle.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	selectedTurtle.setTurtleVisibility(!selectedTurtle.getTurtleVisibility());
        TurtlesWorkspacePanel.forceRefresh(true, false);
        refreshButtons();
      }
    });
    
    buttonHideTurtle.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
    		buttonHideTurtle.setBackground(new Color(225, 225, 225));
        repaint();
      }

      @Override
      public void mouseExited(MouseEvent me) {
      	buttonHideTurtle.setBackground(new Color(245, 245, 245));
        repaint();
      }
    });
    
    buttonHidePen.setUI(new CustomButtonUI());
    buttonHidePen.setFont(new Font("Tahoma", Font.BOLD, 11));
    buttonHidePen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    buttonHidePen.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	selectedTurtle.setPenVisibility(!selectedTurtle.getPenVisibility());
        TurtlesWorkspacePanel.forceRefresh(true, false);
        refreshButtons();
      }
    });
    
    buttonHidePen.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	buttonHidePen.setBackground(new Color(225, 225, 225));
        repaint();
      }

      @Override
      public void mouseExited(MouseEvent me) {
      	buttonHidePen.setBackground(new Color(245, 245, 245));
        repaint();
      }
    });
    
    buttonEditTurtle.setUI(new CustomButtonUI());
    buttonEditTurtle.setFont(new Font("Tahoma", Font.BOLD, 11));
    buttonEditTurtle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    buttonEditTurtle.setText("EDYTUJ ŻÓŁWIA");
    buttonEditTurtle.setIcon(settingsIcon);
    buttonEditTurtle.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new EditTurtlePopup(selectedTurtle);
      }
    });
    
    buttonEditTurtle.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	buttonEditTurtle.setBackground(new Color(225, 225, 225));
        repaint();
      }

      @Override
      public void mouseExited(MouseEvent me) {
      	buttonEditTurtle.setBackground(new Color(245, 245, 245));
        repaint();
      }
    });
    
    buttonTeleport.setUI(new CustomButtonUI());
    buttonTeleport.setFont(new Font("Tahoma", Font.BOLD, 11));
    buttonTeleport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    buttonTeleport.setText("PRZENIEŚ DO ŻÓŁWIA");
    buttonTeleport.setIcon(teleportIcon);
    buttonTeleport.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      	TurtlesWorkspacePanel.setPosX(selectedTurtle.getX());
      	TurtlesWorkspacePanel.setPosY(selectedTurtle.getY());
        TurtlesWorkspacePanel.forceRefresh(true, false);
      }
    });
    
    buttonTeleport.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	buttonTeleport.setBackground(new Color(225, 225, 225));
        repaint();
      }

      @Override
      public void mouseExited(MouseEvent me) {
      	buttonTeleport.setBackground(new Color(245, 245, 245));
        repaint();
      }
    });
    
    buttonDelete.setUI(new CustomButtonUI());
    buttonDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
    buttonDelete.setText("USUŃ ŻÓŁWIA");
    buttonDelete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	if(TurtlesWorkspacePanel.getAllTurtles().get(0) != selectedTurtle) {
      		new DeleteTurtlePopup(selectedTurtle);
      	}
      }
    });
    
    buttonDelete.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	if(TurtlesWorkspacePanel.getAllTurtles().get(0) != selectedTurtle) {
	      	buttonDelete.setBackground(new Color(181, 72, 58));
	        repaint();
      	}
      }

      @Override
      public void mouseExited(MouseEvent me) {
      	if(TurtlesWorkspacePanel.getAllTurtles().get(0) != selectedTurtle) {
	      	buttonDelete.setBackground(new Color(209, 85, 69));
	        repaint();
      	}
      }
    });
    
    refreshButtons();
    
    add(addIcon, "5, 2, center, bottom");
    add(title, "3, 1, 3, 1, left, default");
		add(combobox, "3, 2, 1, 2, fill, fill");
		add(buttonHideTurtle, "3, 6, 3, 1");
		add(buttonHidePen, "3, 8, 3, 1");
		add(buttonEditTurtle, "3, 10, 3, 1");
		add(buttonTeleport, "3, 12, 3, 1");
		add(buttonDelete, "3, 14, 3, 1");
		
	}
	
	@Override
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
		g2d.drawRoundRect(14, 10, w-30, h-20, 15, 15);
		
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(new Color(56, 57, 59));
		g2d.drawLine(34, 10, 44 + title.getBounds().width, 10);
	}

}
