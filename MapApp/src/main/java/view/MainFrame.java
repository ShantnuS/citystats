package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	Controller controller; 
	JPanel topPanel;
	JTabbedPane tabs; 
	
	
	public MainFrame() {
		controller = Controller.getInstance();
		
		//Make frame here
	}
	
	
}
