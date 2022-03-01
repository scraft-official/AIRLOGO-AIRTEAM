package airteam.projects.airlogo.components.dialogs.popups;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import airteam.projects.airlogo.AirLogo;
import airteam.projects.airlogo.components.dialogs.CustomDialogFrame;
import airteam.projects.airlogo.components.dialogs.CustomDialogPanel;
import airteam.projects.airlogo.savemanager.SaveManager;
import airteam.projects.airlogo.utilities.GraphicsUtility;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class IntroPopup extends JPanel {
	private int width = 700;
	private int height = 400;
	
	public IntroPopup() {
		setOpaque(false);
		
		Dialog dialog = new IntroPopup.Dialog();
		CustomDialogPanel panel = dialog.frame.getDialogPanel();
		
		
		
		JTextPane text = new JTextPane();
		StyledDocument doc = text.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("525px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				RowSpec.decode("150px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		text.setText("WITAJ W APLIKACJI AIRLOGO!\nWSZYSTKIE POLECENIA ZNAJDZIESZ POD KOMENDA \"HELP\"");
		text.setFont(new Font("Tahoma", Font.BOLD, 16));
		text.setForeground(new Color(50, 135, 75));
		text.setEditable(false);
		text.setOpaque(false);
		add(text, "1, 2, center, top");
		
		
		JButton acceptButton = panel.getAcceptButton();
		acceptButton.setText("ROZPOCZYNAM ZABAWĘ");
		acceptButton.setIcon(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/check-mark-icon.png"), 16, 16)));
		acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      	dialog.frame.dispose();
      }
    });
		
		panel.setContentPanel(this);
		
		JLabel moveImage = new JLabel(new ImageIcon(GraphicsUtility.getSizedImage(GraphicsUtility.getInternalIcon("icons/move-intro-icon.png"), 150, 150)));
		add(moveImage, "1, 3, center, center");
		
		JLabel subImageText = new JLabel("MOŻESZ PORUSZAĆ SIĘ PO PLANSZY PRZYTRZYMUJĄC (LPM)");
		subImageText.setFont(new Font("Tahoma", Font.BOLD, 16));
		subImageText.setForeground(new Color(50, 135, 75));
		add(subImageText, "1, 5, center, default");
		
		dialog.frame.setVisible(true);
	}
	
	
	public class Dialog extends JPanel {
		public CustomDialogFrame frame = new CustomDialogFrame("WITAJ W AIRLOGO", width, height, true, false);
	}
}
