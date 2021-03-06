package airteam.projects.airlogo.components.dialogs.popups;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.airlogo.components.TurtlesWorkspacePanel;
import airteam.projects.airlogo.components.dialogs.CustomDialogFrame;
import airteam.projects.airlogo.components.dialogs.CustomDialogPanel;
import airteam.projects.airlogo.components.templates.CustomTextField;
import airteam.projects.airlogo.turtle.Turtle;
import airteam.projects.airlogo.utilities.GraphicsUtility;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EditTurtlePopup extends JPanel {
	private int width = 600;
	private int height = 350;

	private JTextField turtleNameField;
	private JTextField turtleColorField;
	
	public EditTurtlePopup(Turtle turtle) {
		setOpaque(false);
		
		Dialog dialog = new EditTurtlePopup.Dialog();
		CustomDialogPanel panel = dialog.frame.getDialogPanel();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("250dlu:grow"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("fill:50px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),}));
		
		JButton acceptButton = panel.getAcceptButton();
		acceptButton.setText("ZAPISZ");
		acceptButton.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/check-mark-icon.png"), 17, 17)));
		acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	String nameText = turtleNameField.getText();
      	String colorText = turtleColorField.getText();
      	
      	
      	// WHITE SPACE REMOVER  --------------------------------------
      	
      	while(nameText.contains("  ")) {   
      		nameText = nameText.replaceAll("  ", " ");
				}
      	if(nameText.length() > 0 && nameText.charAt(0) == ' ') nameText = nameText.substring(1);
      	
      	while(colorText.contains("  ")) {     
      		colorText = colorText.replaceAll("  ", " ");
				}
      	if(colorText.length() > 0 && colorText.charAt(0) == ' ') colorText = colorText.substring(1);
      	
      	//------------------------------------------------------------
      	
      	if(nameText.length() == 0) {
      		((CustomTextField) turtleNameField).setRquiredHint("* TO POLE JEST WYMAGANE!");
      		((CustomTextField) turtleNameField).showRequiredHint(true);
      		turtleNameField.setText("");
      		turtleNameField.repaint();
      		return;	
      	} else {
      		if(!turtle.getName().equals(nameText.toUpperCase()))
	      		for(Turtle t : TurtlesWorkspacePanel.getAllTurtles()) {
	      			if(t.getName().equals(nameText.toUpperCase())) {
	      				((CustomTextField) turtleNameField).setRquiredHint("* ??????W O TAKIEJ NAZWIE JU?? ISTNIEJE!");
	          		((CustomTextField) turtleNameField).showRequiredHint(true);
	          		turtleNameField.repaint();
	      				return;
	      			}
	      		}
      		((CustomTextField) turtleNameField).showRequiredHint(false);
      		turtleNameField.repaint();
      	}
      	
      	Color selectedColor;
      	
      	if(colorText.length() == 0) {
      		((CustomTextField) turtleColorField).showRequiredHint(true);
      		((CustomTextField) turtleColorField).setRquiredHint("* TO POLE JEST WYMAGANE!");
      		turtleColorField.setText("");
      		turtleColorField.repaint();
      		return;
      	} else {
      		try { selectedColor = Color.decode(colorText);} 
      		catch(Exception ex) {
      			((CustomTextField) turtleColorField).showRequiredHint(true);
        		((CustomTextField) turtleColorField).setRquiredHint("* WPROWADZONO NIEPRAWID??OWY KOLOR!");
        		turtleColorField.repaint();
        		return;
      		}
      		
      		((CustomTextField) turtleColorField).showRequiredHint(false);
      		turtleColorField.repaint();
      	}
      	
      	turtle.setName(nameText.toUpperCase());
      	turtle.setTurtleColor(selectedColor);
      	TurtlesWorkspacePanel.forceRefresh(true, true);
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
		
		panel.setContentPanel(this);
		
		turtleNameField = new CustomTextField("NAZWA ??????WIA");
		turtleNameField.setText(turtle.getName());
		
		turtleColorField = new CustomTextField("KOLOR ??????WIA (HEX)");
		turtleColorField.setText(String.format("#%02x%02x%02x", turtle.getTurtleColor().getRed(), turtle.getTurtleColor().getGreen(), turtle.getTurtleColor().getBlue()));
		
		add(turtleNameField, "1, 2, fill, fill");
		add(turtleColorField, "1, 4, fill, fill");
		dialog.frame.setVisible(true);
	}
	
	
	public class Dialog extends JPanel {
		public CustomDialogFrame frame = new CustomDialogFrame("EDYTUJ ??????WIA", width, height, false, false);
	}
}
