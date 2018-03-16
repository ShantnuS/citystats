package view;

import java.awt.Color;
import java.awt.FlowLayout;

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
		comboBox = new JComboBox<String>(Controller.getInstance().getVariables());
		comboBox.setLightWeightPopupEnabled(false);
		
		lastData = new JLabel("N/A");
		
		this.setStatus(false); //as initially it should be red
		this.setLayout(new FlowLayout());
		
		this.add(comboBox);
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
