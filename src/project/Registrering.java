package project;
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
	}
	
	
	private static int setCounter(String entitet) {
		int counter = 0;
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "SELECT MAX(" + entitet + "ID) AS Max" + entitet + "ID FROM " + entitet;
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				counter = rs.getInt("Max" + entitet + "ID") + 1;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return counter;
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
	
	public static void registrerOvelse(String navn, int ovelsegruppeID, String type, int apparatID, String beskrivelse) {
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Ovelse "
						+" (OvelseID, Navn, OvelsegruppeID)"
						+" VALUES(" + ovelseIDCounter + ", '" + navn + "', '" + ovelsegruppeID + "')";
			statement.executeUpdate(sql);
			
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		if (type.equals("fastmontert")) {
			try {
				Statement statement = DBConn.getConnection().createStatement();
				String sql = "INSERT INTO Fastmontert "
							+" (FastmontertID, ApparatID)"
							+"VALUES(" + ovelseIDCounter + ", " + apparatID + ")";
				statement.executeUpdate(sql);
				ovelseIDCounter++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Statement statement = DBConn.getConnection().createStatement();
				String sql = "INSERT INTO Fri "
							+" (FriID, Beskrivelse)"
							+"VALUES(" + ovelseIDCounter + ", '" + beskrivelse + "')";
				statement.executeUpdate(sql);
				ovelseIDCounter++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
	
	public static void registrerTreningsokt(String dato, String tidspunkt, int varighet, int form,
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
	
	public static void registrerOvelseITreningsokt(int treningsoktID, int ovelseID, int kilo, int repetisjoner, int sett) {
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO OvelseITreningsokt "
						+" (TreningsoktID, OvelseID, Kilo, Repetisjoner, Sett)"
						+"VALUES(" + treningsoktID + ", " + ovelseID + ", " + kilo + ", " + repetisjoner + ", " + sett + ")";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void registrerApparatITreningssenter(int treningssenterID, int apparatID) {
		try {
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO ApparatITreningssenter "
						+" (TreningssenterID, ApparatID)"
						+"VALUES(" + treningssenterID + ", " + apparatID + ")";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

