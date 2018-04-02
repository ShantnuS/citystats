package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.Controller;
import model.TTNDevice;

public class DataPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	String[] views = {"Bar graph", "Scatter graph", "Pie chart", "Table view", "All views"};
	
	JComboBox<String> viewBox;
	JComboBox<String> deviceBox;
	JComboBox<String> variableBox;
	
	JPanel settingsPanel;
	JPanel graphPanel;
	
	public DataPanel(){
		deviceBox = new JComboBox<String>(Controller.getInstance().getDeviceIDs());
		variableBox = new JComboBox<String>(Controller.getInstance().getVariables());
		viewBox = new JComboBox<String>(views);
		
		deviceBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	TTNDevice device = Controller.getInstance().getDevice((String) deviceBox.getSelectedItem());
		    	generateGraphPanel(device, (String) variableBox.getSelectedItem(),(String) viewBox.getSelectedItem());
		    }
		});
		
		variableBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        generateGraphPanel((String) variableBox.getSelectedItem(),(String) viewBox.getSelectedItem());
		    }
		});
		
		viewBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        generateGraphPanel((String) viewBox.getSelectedItem());
		    }
		});
		
		settingsPanel = new JPanel();
		graphPanel = new JPanel();
		
		settingsPanel.setLayout(new FlowLayout());
		settingsPanel.add(deviceBox);
		settingsPanel.add(variableBox);
		settingsPanel.add(viewBox);
		
		graphPanel.setBackground(Color.orange);
		
		this.setLayout(new BorderLayout());
		this.add(settingsPanel, BorderLayout.NORTH);
		this.add(graphPanel, BorderLayout.CENTER);
		
		TTNDevice device = Controller.getInstance().getDevice((String) deviceBox.getSelectedItem());
    	generateGraphPanel(device, (String) variableBox.getSelectedItem(),(String) viewBox.getSelectedItem());
		
		this.repaint();
	}
	
	public void generateGraphPanel(TTNDevice device, String variable, String view){
		System.out.println("Generating from device, variable and view");
	}
	
	public void generateGraphPanel(String variable, String view){
		System.out.println("Generating from variable and view");
	}
	
	public void generateGraphPanel(String view){
		System.out.println("Generating from view");
	}
}
