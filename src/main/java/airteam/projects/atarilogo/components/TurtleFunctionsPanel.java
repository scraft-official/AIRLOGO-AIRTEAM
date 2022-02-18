package airteam.projects.atarilogo.components;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import airteam.projects.atarilogo.components.dialogs.popups.AddNewFunctionPopup;
import airteam.projects.atarilogo.components.dialogs.popups.AddNewTurtlePopup;
import airteam.projects.atarilogo.components.dialogs.popups.DeleteFunctionPopup;
import airteam.projects.atarilogo.components.dialogs.popups.DeleteTurtlePopup;
import airteam.projects.atarilogo.components.dialogs.popups.EditFunctionPopup;
import airteam.projects.atarilogo.components.dialogs.popups.EditTurtlePopup;
import airteam.projects.atarilogo.components.templates.ComboBox;
import airteam.projects.atarilogo.components.templates.CustomButtonUI;
import airteam.projects.atarilogo.components.templates.CustomTextPane;
import airteam.projects.atarilogo.components.templates.JScrollBarUI;
import airteam.projects.atarilogo.functions.FunctionManager;
import airteam.projects.atarilogo.functions.FunctionManager.TurtleFunction;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JSeparator;

public class TurtleFunctionsPanel extends JPanel {
	private static ComboBox combobox = new ComboBox();
	
	private JLabel title = new JLabel(" ZAREJESTROWANE PROCEDURY");
	private static JButton buttonEditFunction = new JButton();
	private static ImageIcon settingsIcon = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/settings-icon.png"), 16, 16));

	private static CustomTextPane functionBodyField = new CustomTextPane();
	
	private static JButton buttonDelete = new JButton();
	private static ImageIcon deleteIconOn = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/bin-icon.png"), 14, 14));
	private static ImageIcon deleteIconOff = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/bin-icon-darker.png"), 14, 14));

	private static String selectedFunction = "KWADRAT";
	
	public TurtleFunctionsPanel() {
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:20dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("25px"),}));
		
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		
    combobox.setTitleText("WYBRANA PROCEDURA");
    combobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    combobox.setMaximumRowCount(4);
    combobox.addPopupMenuListener(new PopupMenuListener() {
      @Override
      public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
      	int i = 0;
      	for(String n : FunctionManager.getAllFunctions().keySet()) {
      		if(i == combobox.getSelectedIndex()) {selectedFunction = n; break;} 
      		i++;
      	}
    		refreshButtons();
      }

      @Override
      public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
      }
      
      @Override
      public void popupMenuCanceled(PopupMenuEvent pme) {
      }
    });

    refreshSelected();
    
    JLabel addIcon = new JLabel("");
    addIcon.setIcon(new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/add-icon.png"), 18, 18)));
    addIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    addIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new AddNewFunctionPopup("TO (... WPROWADZ NAZWE PROCEDURY ...)\n\t(... WPROWADZ TRESC KODU PROCEDURY...)\nEND");
			}
		});
    
    
    JPanel backPanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
				int width = getWidth();
				int height = getHeight();
				
				
				g2d.setColor(new Color(113, 116, 120));
				g2d.fillRect(0, 0, width, 3);
				g2d.fillRect(0, height-3, width, height);
				
				g2d.dispose();
			}
		};
		backPanel.setBorder(new EmptyBorder(3, 0, 3, 0));
		backPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane text = new JTextPane();
		StyledDocument doc = text.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		functionBodyField.setTabSize(2);
		functionBodyField.setLineWrap(true);
		functionBodyField.setBackground(new Color(64, 66, 69));
		functionBodyField.setForeground(new Color(230,230,230));
		functionBodyField.setCaretColor(new Color(200,200,200));
		functionBodyField.setEditable(false);

		JScrollPane functionScrollPane = new JScrollPane();

		JScrollBar functionScrollbar = new JScrollBar();
		functionScrollbar.setOpaque(false);
		functionScrollbar.setUI(new JScrollBarUI());
		functionScrollbar.setUnitIncrement(10);
		functionScrollbar.setPreferredSize(new Dimension(2, 48));
		functionScrollbar.setOpaque(false);
		functionScrollbar.setForeground(new Color(189, 58, 58));
		functionScrollbar.setBackground(new Color(64, 66, 69, 0));
		
		functionScrollPane.setBorder(null);
		functionScrollPane.setVerticalScrollBar(functionScrollbar);
		functionScrollPane.setViewportView(functionBodyField);
		functionScrollPane.getViewport().setOpaque(false);
		functionScrollPane.setOpaque(false);
		functionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    
    
    buttonEditFunction.setUI(new CustomButtonUI());
    buttonEditFunction.setFont(new Font("Tahoma", Font.BOLD, 11));
    buttonEditFunction.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    buttonEditFunction.setText("EDYTUJ PROCEDURE");
    buttonEditFunction.setIcon(settingsIcon);
    buttonEditFunction.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	if(!FunctionManager.getFunction(selectedFunction).isDefaultFunction) {
      		new EditFunctionPopup(selectedFunction);
      	}
      }
    });
    
    buttonEditFunction.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	if(!FunctionManager.getFunction(selectedFunction).isDefaultFunction) {
	      	buttonEditFunction.setBackground(new Color(225, 225, 225));
	        repaint();
      	}
      }

      @Override
      public void mouseExited(MouseEvent me) {
      	if(!FunctionManager.getFunction(selectedFunction).isDefaultFunction) {
	      	buttonEditFunction.setBackground(new Color(245, 245, 245));
	        repaint();
      	}
      }
    });
    
    buttonDelete.setUI(new CustomButtonUI());
    buttonDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
    buttonDelete.setText("USUŃ PROCEDURE");
    buttonDelete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	if(!FunctionManager.getFunction(selectedFunction).isDefaultFunction) {
      		new DeleteFunctionPopup(selectedFunction);
      	}
      }
    });
    
    buttonDelete.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
      	if(!FunctionManager.getFunction(selectedFunction).isDefaultFunction) {
	      	buttonDelete.setBackground(new Color(181, 72, 58));
	        repaint();
      	}
      }

      @Override
      public void mouseExited(MouseEvent me) {
      	if(!FunctionManager.getFunction(selectedFunction).isDefaultFunction) {
	      	buttonDelete.setBackground(new Color(209, 85, 69));
	        repaint();
      	}
      }
    });
    
    refreshButtons();
    
    backPanel.add(functionScrollPane);
    
    add(addIcon, "5, 2, center, bottom");
    add(title, "3, 1, 3, 1, left, default");
		add(combobox, "3, 2, 1, 2, fill, fill");
		add(backPanel, "3, 6, 3, 1, fill, fill");
		add(buttonEditFunction, "3, 8, 3, 1, fill, fill");
		add(buttonDelete, "3, 10, 3, 1");
		
	}
	
	public static void setSelectedFunction(String name, boolean refresh) {
		selectedFunction = name;
		if(refresh) refreshAll();
	}
	
	public static void refreshButtons() {
		TurtleFunction function = FunctionManager.getFunction(selectedFunction);
		String args = "";
		if(function.args.size() > 0) {
			args = String.join(" ", function.args);
		}
		
		functionBodyField.setText("TO " + selectedFunction + " " + args + "\n" + function.commands + "\nEND");
		
		
		if(FunctionManager.getFunction(selectedFunction).isDefaultFunction) {
			CustomButtonUI ui;
			ui = (CustomButtonUI) buttonEditFunction.getUI();
			ui.allowClick(false);
			
			buttonEditFunction.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			buttonEditFunction.setBackground(new Color(171, 166, 166));
			
			ui = (CustomButtonUI) buttonDelete.getUI();
			ui.allowClick(false);
			
			buttonDelete.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			buttonDelete.setBackground(new Color(143, 64, 64));
	    buttonDelete.setForeground(new Color(173, 165, 165));
	    buttonDelete.setIcon(deleteIconOff);
	    
		}
		else {
			CustomButtonUI ui;
			ui = (CustomButtonUI) buttonEditFunction.getUI();
			ui.allowClick(false);
			
			buttonEditFunction.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			buttonEditFunction.setBackground(new Color(245, 245, 245));
			
			ui = (CustomButtonUI) buttonDelete.getUI();
			ui.allowClick(true);
			
			buttonDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			buttonDelete.setBackground(new Color(209, 85, 69));
	    buttonDelete.setForeground(new Color(255, 255, 255));
	    buttonDelete.setIcon(deleteIconOn);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void refreshSelected() {
		int selectedID = 0;
		int i = 0;
		for(String func : FunctionManager.getAllFunctions().keySet()) {
			if(func.equals(selectedFunction)) { selectedID = i; break;}
			i++;
		}
		if(FunctionManager.getAllFunctions().keySet().size() > 0) {
			combobox.setModel(new DefaultComboBoxModel(FunctionManager.getAllFunctions().keySet().toArray()));
			combobox.setSelectedIndex(selectedID);
		}
	}
	
	public static void refreshAll() {
		refreshSelected();
		refreshButtons();
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
		g2d.drawRoundRect(14, 10, w-30, h-20, 15, 15);
		
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(new Color(51, 53, 54));
		g2d.drawLine(34, 10, 44 + title.getBounds().width, 10);
	}

}
