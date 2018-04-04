package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import controller.Controller;
import controller.ETCHelper;
import controller.SQLManager;
import model.CSData;
import model.TTNDevice;

public class DataPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	String[] views = {"Bar graph", "Line graph", "Pie chart", "Table view", "All views"};
	
	JComboBox<String> viewBox;
	JComboBox<String> deviceBox;
	JComboBox<String> variableBox;
	
	JPanel settingsPanel;
	JPanel graphPanel;
	
	JFreeChart barChart;
	JFreeChart pieChart;
	JPanel allPanel;
	
	boolean initialised = false;
	
	public DataPanel(){
		deviceBox = new JComboBox<String>(Controller.getInstance().getDeviceIDs());
		variableBox = new JComboBox<String>(Controller.getInstance().getVariables());
		viewBox = new JComboBox<String>(views);
		settingsPanel = new JPanel();
		graphPanel = new JPanel();
		
		deviceBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	TTNDevice device = Controller.getInstance().getDevice((String) deviceBox.getSelectedItem());
		    	generateGraphPanel(device, (String) variableBox.getSelectedItem(),(String) viewBox.getSelectedItem());
		    }
		});
		
		variableBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        generateGraphPanel((String) viewBox.getSelectedItem());
		    }
		});
		
		viewBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        generateGraphPanel((String) viewBox.getSelectedItem());
		    }
		});	
		settingsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		settingsPanel.add(deviceBox);
		settingsPanel.add(variableBox);
		settingsPanel.add(viewBox);
		
		graphPanel.setBackground(Color.orange);
		graphPanel.setLayout(new BorderLayout());
		
		this.setLayout(new BorderLayout());
		this.add(settingsPanel, BorderLayout.NORTH);
		this.add(graphPanel, BorderLayout.CENTER);
		
		TTNDevice device = Controller.getInstance().getDevice((String) deviceBox.getSelectedItem());
    	generateGraphPanel(device, (String) variableBox.getSelectedItem(),(String) viewBox.getSelectedItem());
		
		this.repaint();
		initialised = true;
	}
	
	//Readies the dataset
	public void generateGraphPanel(TTNDevice device, String variable, String view){
		System.out.println("Generating from device, variable and view");
		
		//Fill data history from SQL database
		new Thread(){
		    public void run() {
				try {
					SQLManager.getDeviceData(device);
					generateGraphPanel(view);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}.start();
	}
	
	//Displays the dataset in graph
	public void generateGraphPanel(String view){
		System.out.println("Generating from view");
		graphPanel.removeAll();
		if(view.equals(views[0])){
			System.err.println("Creating bar chart");
			barChart = ChartFactory.createBarChart(
			         "Average vs Latest",           
			         (String) variableBox.getSelectedItem(),            
			         (String) deviceBox.getSelectedItem(),            
			         createBarDataset(),          
			         PlotOrientation.VERTICAL,           
			         true, true, false);
			
			ChartPanel chartPanel = new ChartPanel(barChart);    
			graphPanel.add(chartPanel, BorderLayout.CENTER);
			chartPanel.setPreferredSize(graphPanel.getSize());  
			graphPanel.setBackground(Color.black);
			Controller.getInstance().getMainFrame().repaint();
		}
		
		else if(view.equals(views[1])){
			System.err.println("Creating line graph");
			
			JFreeChart lineChart = ChartFactory.createTimeSeriesChart(
					"Time Series Graph - " + (String) variableBox.getSelectedItem(), // Chart
					"Date", // X-Axis Label
					(String) variableBox.getSelectedItem(), // Y-Axis Label
					createLineDataset());

			//Changes background color
			XYPlot plot = (XYPlot)lineChart.getPlot();
			plot.setBackgroundPaint(new Color(255,228,196));

			ChartPanel chartPanel = new ChartPanel(lineChart);
   
			graphPanel.add(chartPanel, BorderLayout.CENTER);
			chartPanel.setPreferredSize(graphPanel.getSize());  
			graphPanel.setBackground(Color.black);
			Controller.getInstance().getMainFrame().repaint();
		}
		
		else if(view.equals(views[2])){
			System.err.println("Creating pie chart");
			pieChart = ChartFactory.createPieChart(      
			         "Amount of data received",   
			         createPieDataset(),             
			         true,               
			         true, 
			         false);
			
			ChartPanel chartPanel = new ChartPanel(pieChart);    
			graphPanel.add(chartPanel, BorderLayout.CENTER);
			chartPanel.setPreferredSize(graphPanel.getSize());  
			graphPanel.setBackground(Color.pink);
			Controller.getInstance().getMainFrame().repaint();
		}
		
		else if(view.equals(views[3])){
			System.err.println("Creating table view ");
			graphPanel.setBackground(Color.green);
			Controller.getInstance().getMainFrame().repaint();
		}
		
		else if(view.equals(views[4])){
			System.err.println("Show all graphs");
			graphPanel.setBackground(Color.black);
			allPanel = new JPanel();
			allPanel.setLayout(new GridLayout(2,2));
			
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			JPanel panel3 = new JPanel();
			JPanel panel4 = new JPanel();
			
			panel1.setLayout(new BorderLayout());
			panel2.setLayout(new BorderLayout());
			panel3.setLayout(new BorderLayout());
			panel4.setLayout(new BorderLayout());
			
			panel1.setBackground(Color.BLUE);
			panel2.setBackground(Color.yellow);
			panel3.setBackground(Color.red);
			panel4.setBackground(Color.GREEN);
			
			//Bar Chart 
			barChart = ChartFactory.createBarChart(
			         "Average vs Latest",           
			         (String) variableBox.getSelectedItem(),            
			         (String) deviceBox.getSelectedItem(),            
			         createBarDataset(),          
			         PlotOrientation.VERTICAL,           
			         true, true, false);	
			ChartPanel barChartPanel = new ChartPanel(barChart);

			pieChart = ChartFactory.createPieChart(      
			         "Amount of data received",   // chart title 
			         createPieDataset(),          // data    
			         true,             // include legend   
			         true, 
			         false);
			
			ChartPanel pieChartPanel = new ChartPanel(pieChart);
			
			panel1.add(barChartPanel, BorderLayout.CENTER);
			panel2.add(pieChartPanel, BorderLayout.CENTER);
			//panel3.add(barChartPanel);
			//panel4.add(barChartPanel);	
			
			allPanel.add(panel1);
			allPanel.add(panel2);
			allPanel.add(panel3);
			allPanel.add(panel4);
		
			//barChartPanel.setPreferredSize(panel1.getSize());
			allPanel.setPreferredSize(graphPanel.getSize());
			graphPanel.add(allPanel, BorderLayout.CENTER);
			Controller.getInstance().getMainFrame().repaint();
		}
	}
	
	 private CategoryDataset createBarDataset( ) {
		 final String average = "Average";        
	     final String latest = "Latest";  
	     final String avg = "";        
	     final String ltst = ""; 
	     final DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	     
	     String variable = (String) variableBox.getSelectedItem();           
         TTNDevice device = Controller.getInstance().getDevice((String) deviceBox.getSelectedItem());
         
         double latestDouble = 0;
	     dataset.addValue(ETCHelper.getAverageValue(device, variable), average, avg);
	     
	     if(device.getLatestData()!=null)
	    	 latestDouble = ETCHelper.getValueFromName(device, variable);
	    	 
	     dataset.addValue(latestDouble, latest, ltst);
	     return dataset;
	 }
	 
	 private PieDataset createPieDataset(){
		 DefaultPieDataset dataset = new DefaultPieDataset( );
		 HashMap<String, TTNDevice> devices = Controller.getInstance().getAllDevices();
		 
		 for(String e:devices.keySet()){
			 dataset.setValue(e, devices.get(e).getAllData().size());
		 }
		 
		 return dataset;
	}
	 
	 private XYDataset createLineDataset() {
		 TimeSeriesCollection dataset = new TimeSeriesCollection();
		 String variable = (String) variableBox.getSelectedItem();           
         TTNDevice device = Controller.getInstance().getDevice((String) deviceBox.getSelectedItem());
         TimeSeries series = new TimeSeries(variable);
         int second,minute,hour,day,month,year;
         String[] parts;
         double value;
         for(CSData d: device.getAllData()){
        	 //Extract time and date in correct format
        	 parts = d.getDate().split("@");
        	 second = Integer.parseInt(parts[0].split(":")[2]);
        	 minute = Integer.parseInt(parts[0].split(":")[1]);
        	 hour = Integer.parseInt(parts[0].split(":")[0]);
        	 day = Integer.parseInt(parts[1].split("-")[2]);
        	 month = Integer.parseInt(parts[1].split("-")[1]);
        	 year = Integer.parseInt(parts[1].split("-")[0]);
        	 
        	 value = ETCHelper.getValueFromName(d, variable);
        	 if(value != 0)
        		 series.add(new Second(second,minute,hour,day,month,year), value);
         }
		 
         dataset.addSeries(series);
		 return dataset;
	 }
	 
	 public void refresh(){
		 if(initialised)
			 generateGraphPanel((String) viewBox.getSelectedItem());
	 }
}
