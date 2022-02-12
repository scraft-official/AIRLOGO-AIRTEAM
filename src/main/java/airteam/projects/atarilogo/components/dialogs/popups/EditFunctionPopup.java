package airteam.projects.atarilogo.components.dialogs.popups;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import airteam.projects.atarilogo.AtariLogo;
import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.components.dialogs.CustomDialogFrame;
import airteam.projects.atarilogo.components.dialogs.CustomDialogPanel;
import airteam.projects.atarilogo.components.dialogs.popups.SavePopup.Dialog;
import airteam.projects.atarilogo.components.templates.CustomTextField;
import airteam.projects.atarilogo.components.templates.CustomTextPane;
import airteam.projects.atarilogo.components.templates.JScrollBarUI;
import airteam.projects.atarilogo.functions.FunctionManager;
import airteam.projects.atarilogo.functions.FunctionManager.TurtleFunction;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class EditFunctionPopup extends JPanel {
	private int width = 600;
	private int height = 500;

	private JTextField functionNameField;
	private JTextField functionArgsField;
	private CustomTextPane functionBodyField;
	
	public EditFunctionPopup(String name) {
		setOpaque(false);
		
		Dialog dialog = new EditFunctionPopup.Dialog();
		CustomDialogPanel panel = dialog.frame.getDialogPanel();
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("115px"),
				ColumnSpec.decode("200dlu"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"),
				RowSpec.decode("120dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("67dlu"),}));
		
		panel.setContentPanel(this);
		
		JLabel fieldTitle = new JLabel("BUDOWA FUNKCJI");
		fieldTitle.setForeground(new Color(120, 120, 120));
		fieldTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel hintText = new JLabel();
		hintText.setForeground(new Color(235, 64, 52));
		hintText.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel backPanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
				int width = getWidth();
				int height = getHeight();
				
				
				g2d.setColor(new Color(51, 50, 48));
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
		
		functionBodyField = new CustomTextPane();
		functionBodyField.setTabSize(3);
		functionBodyField.setLineWrap(true);

		JScrollPane functionScrollPane = new JScrollPane();

		JScrollBar functionScrollbar = new JScrollBar();
		functionScrollbar.setUI(new JScrollBarUI());
		functionScrollbar.setUnitIncrement(10);
		functionScrollbar.setPreferredSize(new Dimension(5, 48));
		functionScrollbar.setOpaque(false);
		functionScrollbar.setForeground(new Color(219, 135, 61));
		functionScrollbar.setBackground(new Color(57, 135, 80, 0));
		
		functionScrollPane.setBorder(null);
		functionScrollPane.getViewport().setOpaque(false);
		functionScrollPane.setOpaque(false);
		functionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		functionScrollPane.setVerticalScrollBar(functionScrollbar);
		functionScrollPane.setViewportView(functionBodyField);
		
		//TODO DODAC OPIS BUDOWY FUNKCJI
		text.setText("OPIS BUDOWANIA FUNKCJI:\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum proident, sunt in culpa qui officia deserunt mollit anim id est laborum proident, sunt in culpa qui officia deserunt mollit anim id est laborum proident, sunt in culpa qui officia deserunt mollit anim id est laborum proident, sunt in culpa qui officia deserunt mollit anim id est laborum");
		text.setFont(new Font("Tahoma", Font.BOLD, 11));
		text.setForeground(new Color(140,140,140));
		text.setEditable(false);
		text.setOpaque(false);
		
		JScrollBar helpscrollbar = new JScrollBar();
		helpscrollbar.setUI(new JScrollBarUI());
		helpscrollbar.setUnitIncrement(10);
		helpscrollbar.setPreferredSize(new Dimension(5, 48));
		helpscrollbar.setOpaque(false);
		helpscrollbar.setForeground(new Color(219, 135, 61));
		helpscrollbar.setBackground(new Color(57, 135, 80, 0));
		
		JScrollPane helpTextScrollPane = new JScrollPane();
		helpTextScrollPane.setBorder(null);
		helpTextScrollPane.getViewport().setOpaque(false);
		helpTextScrollPane.setOpaque(false);
		helpTextScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		helpTextScrollPane.setVerticalScrollBar(helpscrollbar);
		helpTextScrollPane.setViewportView(text);
		
		JButton acceptButton = panel.getAcceptButton();
		acceptButton.setText("ZAPISZ");
		acceptButton.setIcon(new ImageIcon(Graphics_Utilies.getSizedImage(Graphics_Utilies.getInternalIcon("icons/save-icon.png"), 17, 17)));
		acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	String[] functionList = functionBodyField.getText().toUpperCase().split("\n");
      	String functionName = null;
      	ArrayList<String> functionArguments = new ArrayList<>();
      	
    		String[] firstLine = functionList[0].split(" "); 		
    		if(!firstLine[0].equals("TO")) { hintText.setText("* NIE ROZPOCZĘTO FUNKCJI OD \"TO (NAZWA)\""); return; }
    		if(firstLine.length == 1) { hintText.setText("* NIE WPROWADZONO NAZWY \"TO (NAZWA)\""); return;}
    		
    		functionName = firstLine[1];
    		
    		for(String arg : Arrays.copyOfRange(firstLine, 2, firstLine.length)) {
    			if(arg.length() > 1 && arg.charAt(0) == ':') {
    				functionArguments.add(arg);
    				Log_Utilies.logError(arg.toUpperCase());
    			} else { hintText.setText("* BŁĘDNIE ROZPOCZĘTO ARGUMENT \"" + arg + "\" (BRAK \":\")"); return; }
      	}
    		
    		String lastLine = functionList[functionList.length - 1];
    		if(lastLine.length() != 3 || !lastLine.equals("END")) { hintText.setText("* NIE ZNALEZIONO \"END\" NA KOŃCU FUNKCJI"); return; }
    		if(functionList.length < 3) { hintText.setText("* BRAKUJE LISTY POLECEŃ POMIĘDZY \"TO\" I \"END\""); return; }
    		
    		String functionCommands = String.join(" ", Arrays.copyOfRange(functionList, 1, functionList.length-1));
    		
    		if(!name.equals(functionName)) FunctionManager.removeFunction(name);
    		FunctionManager.addFunction(functionName, functionArguments, functionCommands);
      	dialog.frame.dispose();
      }
    });
		
		JButton cancelButton = panel.getCancelButton();
		cancelButton.setText("ANULUJ");
		cancelButton.setIcon(new ImageIcon(Graphics_Utilies.getSizedImage(Graphics_Utilies.getInternalIcon("icons/close-icon.png"), 14, 14)));
		cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	dialog.frame.dispose();
      }
    });
		
		backPanel.add(functionScrollPane);
		
		add(hintText, "2, 2, left, fill");
		add(helpTextScrollPane, "1, 6, 2, 1, fill, fill");
		add(backPanel, "1, 4, 2, 1, fill, fill");
		add(fieldTitle, "1, 2, left, fill");
		
		TurtleFunction function = FunctionManager.getFunction(name);
		String args = "";
		if(function.args.size() > 0) {
			args = String.join(" ", function.args);
		}
		
		functionBodyField.setText("TO " + name + " " + args + "\n" + function.commands + "\nEND");
		
		dialog.frame.setVisible(true);
	}
	
	
	public class Dialog extends JPanel {
		public CustomDialogFrame frame = new CustomDialogFrame("EDYTUJ FUNKCJE", width, height, false, false);
	}
}
