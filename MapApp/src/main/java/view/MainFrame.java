package view;

import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame {

	Controller controller; 
	
	public MainFrame() {
		controller = Controller.getInstance();
		
		//Make frame here
	}
	
	
}
