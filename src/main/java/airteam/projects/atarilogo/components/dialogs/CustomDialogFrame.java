package airteam.projects.atarilogo.components.dialogs;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import airteam.projects.atarilogo.AtariLogo;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class CustomDialogFrame extends JDialog {
	private CustomDialogPanel dialogPanel;
	
  public CustomDialogFrame(String name) {
  	int w = AtariLogo.getFrame().getContentPane().getBounds().width;
  	int h = AtariLogo.getFrame().getContentPane().getBounds().height;
  	
  	setLocation(AtariLogo.getFrame().getLocation().x + 8,AtariLogo.getFrame().getLocation().y + 31);
  	setSize(w, h);
  	//setSize(474, 338);
  	setUndecorated(true);
  	setResizable(false);
    setModal(true);
    setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
    setIconImage(AtariLogo.getFrame().getIconImage());
    setBackground(new Color(0, 0, 0, 170));
    
    dialogPanel = new CustomDialogPanel(this, name);
    
    JPanel borderPanel = new JPanel();
    borderPanel.setOpaque(false);
    borderPanel.setBorder(new EmptyBorder(h / 6, w/4, h / 6, w/4));
    borderPanel.setLayout(new BorderLayout(0, 0));
    borderPanel.add(dialogPanel);
    
    getContentPane().add(borderPanel);
  }
  
  public CustomDialogPanel getDialogPanel() {
  	return dialogPanel;
  }
  
}
