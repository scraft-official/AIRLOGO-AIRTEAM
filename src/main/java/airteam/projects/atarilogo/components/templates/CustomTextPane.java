package airteam.projects.atarilogo.components.templates;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CustomTextPane extends JTextArea {
	public CustomTextPane() {
		setBorder(new EmptyBorder(15, 13, 15, 13));
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		setBackground(new Color(232, 231, 225));
	}
}
