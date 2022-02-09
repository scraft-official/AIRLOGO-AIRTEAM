package airteam.projects.atarilogo.components.dialogs.popups;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import airteam.projects.atarilogo.AtariLogo;
import airteam.projects.atarilogo.components.dialogs.CustomDialogFrame;
import airteam.projects.atarilogo.components.dialogs.CustomDialogPanel;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SavePopup extends JPanel {
	private int width = 600;
	private int height = 250;
	
	public SavePopup() {
		setOpaque(false);
		
		Dialog dialog = new SavePopup.Dialog();
		CustomDialogPanel panel = dialog.frame.getDialogPanel();
		
		
		
		JTextPane text = new JTextPane();
		StyledDocument doc = text.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		text.setText("ZAMKNIĘCIE APLIKACJI BEZ ZAPISU PLANSZY\nBĘDZIE SKUTKOWAĆ UTRATĄ WSZELKICH DANYCH!");
		text.setFont(new Font("Tahoma", Font.PLAIN, 19));
		text.setEditable(false);
		text.setOpaque(false);
		add(text);
		
		
		JButton cancelButton = panel.getCancelButton();
		cancelButton.setText("NIE ZAPISUJ");
		cancelButton.setIcon(new ImageIcon(Graphics_Utilies.getSizedImage(Graphics_Utilies.getInternalIcon("icons/logout-icon.png"), 17, 17)));
		cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	AtariLogo.getFrame().dispose();
      	System.exit(0);
      }
    });
		
		
		JButton acceptButton = panel.getAcceptButton();
		acceptButton.setText("ZAPISZ");
		acceptButton.setIcon(new ImageIcon(Graphics_Utilies.getSizedImage(Graphics_Utilies.getInternalIcon("icons/save-icon.png"), 16, 16)));
		acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	//TODO Dodac zapisywanie planszy i automatyczn wykrywanie zmian + skrot klawiszowy ctr+s. Dodac takze ze zapisuje do wczesniej wybranej planszy lub zapisuje nowa kopie. (dodac dialog).
      	
      	AtariLogo.getFrame().dispose();
      	System.exit(0);
      }
    });
		
		panel.setContentPanel(this);
		dialog.frame.setVisible(true);
	}
	
	
	public class Dialog extends JPanel {
		public CustomDialogFrame frame = new CustomDialogFrame("CZY CHCESZ ZAPISAĆ PLANSZĘ?", width, height, false, false);
	}
}
