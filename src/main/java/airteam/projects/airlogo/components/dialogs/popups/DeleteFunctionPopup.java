package airteam.projects.airlogo.components.dialogs.popups;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import airteam.projects.airlogo.components.dialogs.CustomDialogFrame;
import airteam.projects.airlogo.components.dialogs.CustomDialogPanel;
import airteam.projects.airlogo.functions.FunctionManager;
import airteam.projects.airlogo.utilities.GraphicsUtility;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class DeleteFunctionPopup extends JPanel {
	private int width = 600;
	private int height = 250;
	
	public DeleteFunctionPopup(String name) {
		setOpaque(false);
		
		Dialog dialog = new DeleteFunctionPopup.Dialog();
		CustomDialogPanel panel = dialog.frame.getDialogPanel();
		
		
		
		JTextPane text = new JTextPane();
		StyledDocument doc = text.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		text.setText("CZY CHCESZ USUNĄĆ PROCEDURĘ \"" + name + "\"? \nSPOWODUJE TO JEJ TRWAŁE USUNIĘCIE!");
		text.setFont(new Font("Tahoma", Font.PLAIN, 19));
		text.setEditable(false);
		text.setOpaque(false);
		add(text);
		
		
		JButton cancelButton = panel.getCancelButton();
		cancelButton.setText("ANULUJ");
		cancelButton.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/close-icon.png"), 14, 14)));
		cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	dialog.frame.dispose();
      }
    });
		
		
		JButton acceptButton = panel.getAcceptButton();
		acceptButton.setText("USUŃ PROCEDURE");
		acceptButton.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/bin-icon.png"), 15, 15)));
		acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	FunctionManager.removeFunction(name);
      	dialog.frame.dispose();
      }
    });
		
		panel.setContentPanel(this);
		dialog.frame.setVisible(true);
	}
	
	
	public class Dialog extends JPanel {
		public CustomDialogFrame frame = new CustomDialogFrame("CZY CHCESZ USUNĄĆ PROCEDURE?", width, height, false, false);
	}
}
