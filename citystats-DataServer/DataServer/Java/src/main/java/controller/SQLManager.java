package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.CSData;

public class SQLManager {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://csvps.shantnu.me/citystats";

	public static void putIntoDatabase(CSData data) throws IOException{
		String USER = SQLKeyGetter.getUsername();
		String PASS = SQLKeyGetter.getPassword();

		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			String sql = "INSERT INTO csdata(devID,time,temperature,humidity,light,pressure,altitude,tilt,voltage) " 
							+ "VALUES (?,?,?,?,?,?,?,?,?)";
			
			stmt = conn.prepareStatement(sql);
			
		    stmt.setString(1, data.getDevice());
		    stmt.setString(2, data.getDate());
		    stmt.setString(3, data.getTemperature());
		    stmt.setString(4, data.getHumidity());
		    stmt.setString(5, data.getLight());
		    stmt.setString(6, data.getPressure());
		    stmt.setString(7, data.getAltitude());
		    stmt.setString(8, data.getTilt());
		    stmt.setString(9, data.getVoltage());
		    
		    stmt.executeUpdate();
		    
		    System.out.println("Put into database: " + data.getDevice() + " " + data.getDate());
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
	}
}
