package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import controller.Controller;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	boolean status = false;
	public StatusPanel(){
		this.setBackground(Color.red);
		this.setLayout(new FlowLayout());
	}
	
	public void setStatus(boolean status){
		this.status = Controller.getInstance().getStatus();
		
		if(status){
			this.setBackground(Color.green);
		}
		else{
			this.setBackground(Color.red);
		}
	}
	
}
