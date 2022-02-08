package airteam.projects.atarilogo.components.dialogs;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.Font;

@SuppressWarnings("serial")
public class SavePopup extends JPanel {
	
	public SavePopup() {
		Dialog dialog = new SavePopup.Dialog();
		CustomDialogPanel panel = dialog.frame.getDialogPanel();
		
		setOpaque(false);
		
		
		panel.getContentPanel().setViewportView(this);	
		setLayout(new BorderLayout(0, 0));
		
		
		
		JTextPane text = new JTextPane();
		text.setFont(new Font("Tahoma", Font.PLAIN, 16));
		text.setEditable(false);
		text.setText("Gdy zmakniesz program.Gdy zmakniesz program.Gdy zmakniesz program.Gdy zmakniesz program.Gdy zmakniesz program.Gdy zmakniesz program. Gdy zmakniesz program.");
		
		StyledDocument doc = text.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		add(text);
		dialog.frame.setVisible(true);
	}
	
	
	public class Dialog extends JPanel {
		public CustomDialogFrame frame = new CustomDialogFrame("CZY CHCESZ ZAPISAĆ PLANSZĘ?");
	}
}
