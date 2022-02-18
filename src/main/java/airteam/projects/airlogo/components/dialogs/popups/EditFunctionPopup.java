package airteam.projects.airlogo.components.dialogs.popups;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.airlogo.components.dialogs.CustomDialogFrame;
import airteam.projects.airlogo.components.dialogs.CustomDialogPanel;
import airteam.projects.airlogo.components.templates.CustomTextPane;
import airteam.projects.airlogo.components.templates.JScrollBarUI;
import airteam.projects.airlogo.functions.FunctionManager;
import airteam.projects.airlogo.functions.FunctionManager.TurtleFunction;
import airteam.projects.airlogo.utilities.GraphicsUtility;
import airteam.projects.airlogo.utilities.LogUtility;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class EditFunctionPopup extends JPanel {
	private int width = 600;
	private int height = 350;

	private CustomTextPane functionBodyField;
	
	public EditFunctionPopup(String name) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("325px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"),
				RowSpec.decode("125dlu"),}));
		setOpaque(false);
		
		Dialog dialog = new EditFunctionPopup.Dialog();
		CustomDialogPanel panel = dialog.frame.getDialogPanel();
		
		panel.setContentPanel(this);
		
		JLabel fieldTitle = new JLabel("PROCEDURA");
		fieldTitle.setForeground(new Color(120, 120, 120));
		fieldTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel hintText = new JLabel();
		hintText.setForeground(new Color(235, 64, 52));
		hintText.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel backPanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				
				int width = getWidth();
				int height = getHeight();
				
				Graphics2D g2d = (Graphics2D) g;
				
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);		
				g2d.setColor(new Color(51, 50, 48));
				g2d.fillRect(0, 0, width, 3);
				g2d.fillRect(0, height-3, width, height);
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
		
		JButton acceptButton = panel.getAcceptButton();
		acceptButton.setText("ZAPISZ");
		acceptButton.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/save-icon.png"), 17, 17)));
		acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	String[] functionList = functionBodyField.getText().toUpperCase().split("\n");
      	String functionName = null;
      	ArrayList<String> functionArguments = new ArrayList<>();
      	
    		String[] firstLine = functionList[0].split(" "); 		
    		if(!firstLine[0].equals("TO")) { hintText.setText("* NIE ROZPOCZĘTO PROCEDURY OD \"TO (NAZWA)\""); return; }
    		if(firstLine.length == 1) { hintText.setText("* NIE WPROWADZONO NAZWY \"TO (NAZWA)\""); return;}
    		
    		functionName = firstLine[1];
    		
    		for(String arg : Arrays.copyOfRange(firstLine, 2, firstLine.length)) {
    			if(arg.length() > 1 && arg.charAt(0) == ':') {
    				functionArguments.add(arg);
    				LogUtility.logError(arg.toUpperCase());
    			} else { hintText.setText("* BŁĘDNIE ROZPOCZĘTO ARGUMENT \"" + arg + "\" (BRAK \":\")"); return; }
      	}
    		
    		String lastLine = functionList[functionList.length - 1];
    		if(lastLine.length() != 3 || !lastLine.equals("END")) { hintText.setText("* NIE ZNALEZIONO \"END\" NA KOŃCU PROCEDURY"); return; }
    		if(functionList.length < 3) { hintText.setText("* BRAKUJE LISTY POLECEŃ POMIĘDZY \"TO\" I \"END\""); return; }
    		
    		String functionCommands = String.join(" \n", Arrays.copyOfRange(functionList, 1, functionList.length-1));
    		
    		if(!name.equals(functionName)) {
    			if(!FunctionManager.getFunction(name).isDefaultFunction)
    				FunctionManager.removeFunction(name);
    			else
    				{ hintText.setText("* NIE MOŻNA EDYTOWAĆ WBUDOWANYCH PROCEDUR"); return; }
    		}

    		FunctionManager.addFunction(functionName, functionArguments, functionCommands);
      	dialog.frame.dispose();
      }
    });
		
		JButton cancelButton = panel.getCancelButton();
		cancelButton.setText("ANULUJ");
		cancelButton.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/close-icon.png"), 14, 14)));
		cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	dialog.frame.dispose();
      }
    });
		
		backPanel.add(functionScrollPane);
		
		add(hintText, "2, 2, left, fill");
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
		public CustomDialogFrame frame = new CustomDialogFrame("EDYTUJ PROCEDURE", width, height, false, false);
	}
}
