package airteam.projects.atarilogo.components.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import airteam.projects.atarilogo.AirLogo;

@SuppressWarnings("serial")
public class CustomDialogFrame extends JDialog {
	private CustomDialogPanel dialogPanel;
	
  public CustomDialogFrame(String name, int dialogWidth, int dialogHeight, boolean onlyAcceptButton, boolean addDefaultIcons) {
  	int w = AirLogo.getFrame().getContentPane().getBounds().width;
  	int h = AirLogo.getFrame().getContentPane().getBounds().height;
  	
  	dialogPanel = new CustomDialogPanel(this, name, onlyAcceptButton, addDefaultIcons);
  	
  	setLocation(AirLogo.getFrame().getLocation().x + 8,AirLogo.getFrame().getLocation().y + 31);
  	setSize(w, h);
  	setUndecorated(true);
  	setResizable(false);
    setModal(true);
    setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
    setIconImage(AirLogo.getFrame().getIconImage());
    setBackground(new Color(0, 0, 0, 170));
    
    JPanel borderPanel = new JPanel();
    borderPanel.setOpaque(false);
    borderPanel.setBorder(new EmptyBorder((h- dialogHeight)/2, (w - dialogWidth)/2, (h - dialogHeight)/2, (w - dialogWidth)/2));
    borderPanel.setLayout(new BorderLayout(0, 0));
    borderPanel.add(dialogPanel);

    getContentPane().add(borderPanel);
  }
  
  public CustomDialogPanel getDialogPanel() {
  	return dialogPanel;
  }
  
}
