package airteam.projects.atarilogo.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import airteam.projects.atarilogo.commands.CMD_ED;
import airteam.projects.atarilogo.commands.CommandManager;
import airteam.projects.atarilogo.components.dialogs.popups.AddNewFunctionPopup;
import airteam.projects.atarilogo.turtle.Turtle;
import airteam.projects.atarilogo.utilities.Graphics_Utilies;
import airteam.projects.atarilogo.utilities.Log_Utilies;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

@SuppressWarnings("serial")
public class Console_Input extends JPanel {
	private static Color backgroundColor1 = new Color(215, 215, 215, 220);
	private static Color backgroundColor2 = new Color(210, 210, 210, 210);
	
	private static int shadowSize = 4;
	private static int borderSize = 1;
	private static int borderRadius = 15;
	
	private ArrayList<String> lastCommands = new ArrayList<>();
	private int lastCommandIndex = 0;
	private static JLabel consoleButton;
	
	private static ImageIcon consoleOn = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/paper-icon-full.png"), 28, 28));
	private static ImageIcon consoleOff = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/paper-icon.png"), 28, 28));
	
	public Console_Input() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBorder(new EmptyBorder(2, 15, 3, 15));
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("163px"),
				ColumnSpec.decode("default:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("3px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40px"),},
			new RowSpec[] {
				RowSpec.decode("fill:default:grow"),}));
		
		
		JLabel beforeText = new JLabel("WYSLIJ POLECENIE »");
		beforeText.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JTextField textField = new JTextField();
		textField.setOpaque(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBorder(null);
		textField.setColumns(7);
		
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String command = textField.getText();
				if(command.length() == 0) return;
				
				while(command.contains("  ")) {
					command = command.replaceAll("  ", " ");
				}
				
				if(command.charAt(0) == ' ') {
					if(command.length() == 1) {
						textField.setText("");
						return;
					}
					command = command.substring(1);
				}
				
				if(lastCommands.size() > 0) {
					if(!lastCommands.get(lastCommands.size() - 1).equals(command)) {
						lastCommands.add(command);
					}
				} else {
					lastCommands.add(command);
				}
				
				if(lastCommands.size() > 50) {
					lastCommands.remove(0);
				}
				
				lastCommandIndex = lastCommands.size();
				
				command = command.toUpperCase();
				Console_Output.addUserLog(command);
				
				String[] cmdList = command.split(" ");
				if(cmdList[0].equals("TO")) {
					new AddNewFunctionPopup(command);
				}
				else if(cmdList[0].equals("ED")) {
					CMD_ED.execute(command.split(" "));
				} else CommandManager.parse(cmdList);
				
				textField.setText("");
      }});
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==38) {
					if(lastCommandIndex > 0) {
						textField.setText(lastCommands.get(lastCommandIndex - 1));
						lastCommandIndex--;
					}
				}
				if(e.getKeyCode()==40) {
					if(lastCommandIndex < lastCommands.size() - 1) {
						textField.setText(lastCommands.get(lastCommandIndex + 1));
						lastCommandIndex++;
					}
					else {
						textField.setText("");
						lastCommandIndex = lastCommands.size();
					}
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setPreferredSize(new Dimension(0, 0));
		separator.setForeground(new Color(52, 145, 80));
		separator.setBackground(new Color(52, 145, 80));
		
		consoleButton = new JLabel(new ImageIcon(
				Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/paper-icon.png"), 28, 28) 
		));
		
		ImageIcon consoleOn = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/paper-icon-full.png"), 28, 28));
		ImageIcon consoleOff = new ImageIcon(Graphics_Utilies.getSizedImage((BufferedImage) Graphics_Utilies.getInternalIcon("icons/paper-icon.png"), 28, 28));
		
		consoleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Console_Output.changeVisibility();
				refreshConsoleButton();
				Turtles_Workspace_Area.refresh();
			}
		});
		consoleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		add(separator, "6, 1, 2, 1");
		add(textField, "3, 1, fill, fill");
		add(beforeText, "1, 1, 2, 1, center, fill");
		add(consoleButton, "8, 1, center, default");
		
	}
	
	public static void refreshConsoleButton() {
		if(Console_Output.getVisbility()) consoleButton.setIcon(consoleOn);
		else consoleButton.setIcon(consoleOff);
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
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
