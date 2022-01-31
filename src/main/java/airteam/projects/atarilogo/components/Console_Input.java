package airteam.projects.atarilogo.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;

@SuppressWarnings("serial")
public class Console_Input extends JPanel {
	private static Color backgroundColor1 = new Color(215, 215, 215, 220);
	private static Color backgroundColor2 = new Color(210, 210, 210, 210);
	
	private static int shadowSize = 4;
	private static int borderSize = 1;
	private static int borderRadius = 15;
	
	public Console_Input() {
//		JLabel lblNewLabel = new JLabel("WYSLIJ POLECENIE »  ");
//		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
//		add(lblNewLabel);
//		
//		JTextField textField = new JTextField();
//		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		textField.setBorder(null);
//		textField.setOpaque(false);
//		textField.setColumns(7);
//		add(textField);
//		
//		textField.addActionListener(new ActionListener(){
//
//			public void actionPerformed(ActionEvent e){
//				Log_Utilies.logInfo(textField.getText());
//				
//				Turtle t = Turtles_Workspace_Area.getTurtle(0);
//				t.move(Integer.valueOf(textField.getText()));
//				t.rotate(45);
//				Turtles_Workspace_Area.refresh(true);
//				textField.setText("");
//      }});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getBounds().width;
		int h = getBounds().height;
		
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		
		Graphics_Utilies.drawRoundFadedBorder(g2d, new Color(0,0,0), shadowSize, 0, 0, w, h, borderRadius);
		Graphics_Utilies.setGradientPaint(g2d, backgroundColor1, backgroundColor2, 0, h);
		
		g2d.fillRoundRect(shadowSize/2 + borderSize/2, shadowSize/2 + borderSize/2, w-shadowSize-borderSize, h-shadowSize-borderSize, borderRadius, borderRadius);
		g2d.dispose();
	}
	
}
