package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	Controller controller; 
	StatusPanel statusPanel;
	JTabbedPane tabs; 
	MapPanel mapPanel;
	JPanel dataPanel;
	
	
	public MainFrame() {
		controller = Controller.getInstance();
		
		this.setTitle("MapApp - CityStats");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1920/2, 1080/2);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.init();
	}
	
	public void init(){
		//Top status panel
		statusPanel = new StatusPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		mapPanel = new MapPanel();
		dataPanel = new JPanel();
		dataPanel.setBackground(Color.black);
		tabbedPane.add("Map", mapPanel);
		tabbedPane.add("Data", dataPanel);
		
		this.add(tabbedPane, BorderLayout.CENTER);
		this.add(statusPanel, BorderLayout.NORTH);
		
		this.addWindowListener(new CSWindowListener());
		
		this.setVisible(true);
		this.repaint();
	}
	
	public MapPanel getMapPanel(){
		return this.mapPanel;
	}
	
	public StatusPanel getStatusPanel(){
		return this.statusPanel;
	}
}
