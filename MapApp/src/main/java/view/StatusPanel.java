package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JComboBox<String> comboBox;
	JLabel lastData;
	
	public StatusPanel(){
		//Combobox
		/*
		comboBox = new JComboBox<String>(Controller.getInstance().getVariables());
		comboBox.setLightWeightPopupEnabled(false);
		
		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        Controller.getInstance().setCurrentVariable((String)comboBox.getSelectedItem());
		    }
		});
		*/
		
		lastData = new JLabel("...");
		
		this.setStatus(false); //as initially it should be red
		this.setLayout(new FlowLayout());
		
		//this.add(comboBox); //Combobox has been dicontinued
		this.add(lastData);
	}
	
	public void setStatus(boolean status){
		if(status){
			this.setBackground(Color.green);
		}
		else{
			this.setBackground(Color.red);
		}
	}
	
	public void setLastDataLabel(String text){
		lastData.setText(text);
	}
	
}
