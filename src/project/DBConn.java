package project;
import java.sql.*;
public class DBConn {

	public static Connection conn = null;
	
	public static void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/setup"
											+ "?autoReconnect=true&useSSL=false","root","root");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}	
	}
	
	public static Connection getConnection() {
		return conn;
	}
}
