import java.sql.*;
public class DBConn {

	public static void main(String[] args) {
		
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/setup?autoReconnect=true&useSSL=false","root","root");
			
			/*
			Statement stmt = conn.createStatement();
			
			ResultSet resSet = stmt.executeQuery("select * from Apparat");
			
			while (resSet.next()) {
				System.out.println(resSet.getString("Beskrivelse"));
			}
			*/
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
}
