package airteam.projects.atarilogo.components.dialogs.popups;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import airteam.projects.atarilogo.components.Turtles_Workspace_Area;
import airteam.projects.atarilogo.components.dialogs.CustomDialogFrame;
import airteam.projects.atarilogo.components.dialogs.CustomDialogPanel;
import airteam.projects.atarilogo.components.templates.CustomTextField;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
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
		acceptButton.setIcon(new ImageIcon(Graphics_Utilies.getSizedImage(Graphics_Utilies.getInternalIcon("icons/check-mark-icon.png"), 17, 17)));
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
	      		for(Turtle t : Turtles_Workspace_Area.getAllTurtles()) {
	      			if(t.getName().equals(nameText.toUpperCase())) {
	      				((CustomTextField) turtleNameField).setRquiredHint("* ŻÓŁW O TAKIEJ NAZWIE JUŻ ISTNIEJE!");
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
        		((CustomTextField) turtleColorField).setRquiredHint("* WPROWADZONO NIEPRAWIDŁOWY KOLOR!");
        		turtleColorField.repaint();
        		return;
      		}
      		
      		((CustomTextField) turtleColorField).showRequiredHint(false);
      		turtleColorField.repaint();
      	}
      	
      	turtle.setName(nameText.toUpperCase());
      	turtle.setTurtleColor(selectedColor);
      	Turtles_Workspace_Area.forceRefresh(true, true);
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
		
		panel.setContentPanel(this);
		
		turtleNameField = new CustomTextField("NAZWA ŻÓŁWIA");
		turtleNameField.setText(turtle.getName());
		
		turtleColorField = new CustomTextField("KOLOR ŻÓŁWIA (HEX)");
		turtleColorField.setText(String.format("#%02x%02x%02x", turtle.getTurtleColor().getRed(), turtle.getTurtleColor().getGreen(), turtle.getTurtleColor().getBlue()));
		
		add(turtleNameField, "1, 2, fill, fill");
		add(turtleColorField, "1, 4, fill, fill");
		dialog.frame.setVisible(true);
	}
	
	
	public class Dialog extends JPanel {
		public CustomDialogFrame frame = new CustomDialogFrame("EDYTUJ ŻÓŁWIA", width, height, false, false);
	}
}
