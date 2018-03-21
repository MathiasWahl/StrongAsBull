import java.sql.*;


public class Registrering {
	
	private static int apparatIDCounter;
	private static int ovelseIDCounter;
	private static int ovelsegruppeIDCounter;
	private static int treningsoktIDCounter;
	private static int treningssenterIDCounter;
	
	public static void runSetCounter() {
		apparatIDCounter = setCounter("Apparat");
		ovelseIDCounter = setCounter("Ovelse");
		ovelsegruppeIDCounter = setCounter("Ovelsegruppe");
		treningsoktIDCounter = setCounter("Treningsokt");
		treningssenterIDCounter = setCounter("Treningssenter");
		System.out.println(treningssenterIDCounter);
	}
	
	private static int setCounter(String entitet) {
		int counter = 0;
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "SELECT MAX(" + entitet + "ID) AS Max" + entitet + "ID FROM " + entitet;
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt("Max" + entitet + "ID"));
				counter = rs.getInt("Max" + entitet + "ID") + 1;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		return counter;
	}
	
//	public static void setCounters() {
//		try {
//			Statement statement = DBConn.getConnection().createStatement();
//			String sql = "SELECT MAX(ApparatID) AS MaxApparatID FROM Apparat";
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()){
//				apparatIDCounter = rs.getInt("MaxApparatID") + 1;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			Statement statement = DBConn.getConnection().createStatement();
//			String sql = "SELECT MAX(OvelseID) AS MaxOvelseID FROM Ovelse";
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()){
//				ovelseIDCounter = rs.getInt("MaxOvelseID") + 1;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			Statement statement = DBConn.getConnection().createStatement();
//			String sql = "SELECT MAX(OvelsegruppeID) AS MaxOvelsegruppeID FROM Ovelsegruppe";
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()){
//				ovelsegruppeIDCounter = rs.getInt("MaxOvelsegruppeID") + 1;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			Statement statement = DBConn.getConnection().createStatement();
//			String sql = "SELECT MAX(TreningsoktID) AS MaxTreningsoktID FROM Treningsokt";
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()){
//				treningsoktIDCounter = rs.getInt("MaxTreningsoktID") + 1;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			Statement statement = DBConn.getConnection().createStatement();
//			String sql = "SELECT MAX(OvelsegruppeID) AS MaxOvelsegruppeID FROM Ovelsegruppe";
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()){
//				ovelsegruppeIDCounter = rs.getInt("MaxOvelsegruppeID") + 1;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
	

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
	
	public static void registrerTreningsøkt(String dato, String tidspunkt, int varighet, int form,
    		int prestasjon, String notat, int treningssenterID) {
		try {
			
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Treningsokt "
						+" (TreningsoktID, Dato, Tidspunkt, Varighet, Form, Prestasjon, Notat, TreningssenterID)"
						+" VALUES(" + treningsoktIDCounter + ", '" + dato + "', '" + tidspunkt + "', " + varighet + ", " + form + ", " + prestasjon + ", '" + notat + "', " + treningssenterID + ")";
			statement.executeUpdate(sql);
			treningsoktIDCounter++;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
	
	public static void registrerTreningssenter(String navn, int rangering, int storrelse, String sted) {
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Treningssenter "
						+" (TreningssenterID, Navn, Rangering, Storrelse, Sted)"
						+" VALUES(" + treningssenterIDCounter + ", '" + navn + "', " + rangering + ", " + storrelse + ", '" + sted + "')";
			statement.executeUpdate(sql);
			treningssenterIDCounter++;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
}

