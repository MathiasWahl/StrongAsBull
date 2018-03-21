import java.sql.*;


public class Registrering {
	
	private static int apparatIDCounter;
	private static int ovelseIDCounter;
	private static int ovelsegruppeIDCounter;
	
	public static void setCounters() {
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "SELECT MAX(ApparatID) AS MaxApparatID FROM Apparat";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				apparatIDCounter = rs.getInt("MaxApparatID") + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "SELECT MAX(OvelseID) AS MaxOvelseID FROM Ovelse";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				ovelseIDCounter = rs.getInt("MaxOvelseID") + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "SELECT MAX(OvelsegruppeID) AS MaxOvelsegruppeID FROM Ovelsegruppe";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				ovelsegruppeIDCounter = rs.getInt("MaxOvelsegruppeID") + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void registrerApparat(String navn, String beskrivelse) {
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Apparat "
						+" (ApparatID, Navn, Beskrivelse)"
						+" VALUES(" + apparatIDCounter + ", '" + navn + "', '" + beskrivelse + "')";
			statement.executeUpdate(sql);
			apparatIDCounter++;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void registrerOvelse(String navn, int ovelsegruppeID) {
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Ovelse "
						+" (OvelseID, Navn, OvelsegruppeID)"
						+" VALUES(" + ovelseIDCounter + ", '" + navn + "', '" + ovelsegruppeID + "')";
			statement.executeUpdate(sql);
			ovelseIDCounter++;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
	
	public static void registrerOvelsegruppe(String beskrivelse) {
		try {
			
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Ovelsegruppe "
						+" (OvelsegruppeID, Beskrivelse)"
						+" VALUES(" + ovelsegruppeIDCounter + ", '" + beskrivelse + "')";
			statement.executeUpdate(sql);
			ovelsegruppeIDCounter++;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
}

