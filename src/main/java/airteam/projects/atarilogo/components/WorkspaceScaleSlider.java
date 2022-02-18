package airteam.projects.atarilogo.components;

import java.awt.Color;

import javax.swing.JSlider;
import javax.swing.SwingConstants;

import airteam.projects.atarilogo.components.templates.JSliderUI;

@SuppressWarnings("serial")
public class WorkspaceScaleSlider extends JSlider {
	
	public WorkspaceScaleSlider() {
		setUI(new JSliderUI(this));
		setForeground(new Color(52, 145, 80));
		setBackground(new Color(180, 180, 180));
		setValue(100);
		setSnapToTicks(true);
		setMinorTickSpacing(25);
		setMinimum(50);
		setMaximum(200);
		setMajorTickSpacing(25);
		setOpaque(false);
		setOrientation(SwingConstants.VERTICAL);
	}
	
	
}
