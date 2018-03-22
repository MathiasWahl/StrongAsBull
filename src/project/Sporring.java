package project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sporring {
	
	public Sporring() {
		
	}
	
	public String treningsOkter() throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String treningsSporring = "SELECT TreningsoktID, Dato, Notat "
								+ "FROM Treningsokt "
								+ "ORDER BY Dato desc "
								+ "LIMIT " + Integer.toString(10); 
		ResultSet rs = statement.executeQuery(treningsSporring);
		String returnString = "ID \tDato \t\tNotat \n";
		while(rs.next()) {
			returnString += rs.getString(1) + "\t" +rs.getString("Dato") + "\t" + rs.getString("Notat") + "\n";
		}
		return returnString;
	}
	
	
	public String nSisteTreninger(int n) throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String treningsSporring = "SELECT TreningsoktID, Dato, Notat "
								+ "FROM Treningsokt "
								+ "ORDER BY TreningsoktID desc "
								+ "LIMIT " + Integer.toString(n); 
		ResultSet rs = statement.executeQuery(treningsSporring);
		String returnString = "ID \tDato \t\tNotat \n";
		while(rs.next()) {
			returnString += rs.getString(1) + "\t" +rs.getString("Dato") + "\t" + rs.getString("Notat") + "\n";
		}
		return returnString;
	}
	
	public String ovelsesGrupper() throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String gruppeSporring = "SELECT * FROM Ovelsegruppe";
		ResultSet rs = statement.executeQuery(gruppeSporring);
		String returnString = "ID \t\tBeskrivelse \n";
		while (rs.next()) {
			returnString += rs.getString("OvelsegruppeID") + "\t\t" + rs.getString("Beskrivelse") + "\n";
		}
		return returnString;
	}
	
	public String ovelser() throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String ovelseSporring = "SELECT OvelseID, Navn FROM Ovelse";
		ResultSet rs = statement.executeQuery(ovelseSporring);
		String returnString = "ID \t\tNavn \n";
		while (rs.next()) {
			returnString += rs.getString("OvelseID") + "\t\t" + rs.getString("Navn") + "\n";
		}
		return returnString;
	}
	
	public String ovelserIGruppe(int ovelseGruppe) throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String ovelserIGruppeSporring = "SELECT  OvelseID, Navn "
										+ "FROM Ovelse "
										+ "WHERE OvelsegruppeID = " + ovelseGruppe;
		ResultSet rs = statement.executeQuery(ovelserIGruppeSporring);
		String returnString = "ID\t Navn \n";
		while (rs.next()) {
			returnString += rs.getString("OvelseID") + "\t " + rs.getString("Navn") + "\n";
		}
		return returnString;
		
	}

	public String ovelseSisteNDager(int øvelseID, int dager) throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String treningsSporring = "SELECT Dato, Kilo, Repetisjoner, Sett "
								+ "FROM OvelseITreningsokt NATURAL JOIN Treningsokt "
								+ "WHERE Dato >= SUBDATE(CURDATE(),"+Integer.toString(dager)+") "
								+ "ORDER BY Dato desc";
		ResultSet rs = statement.executeQuery(treningsSporring);
		String returnString = "ID \tDato \t\tKilo \tRepetisjoner \t Sett \n";
		while(rs.next()) {
			returnString += rs.getString(1) + "\t" +rs.getString("Dato") + "\t" + rs.getString("Kilo")  + "\t"+ rs.getString("Reptisjoner")  + "\t"+ rs.getString("Sett") + "\n";
		}
		return returnString;
	}
	
	public String treningssentere() throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String senterSporring = "SELECT TreningssenterID, Navn FROM Treningssenter";
		ResultSet rs = statement.executeQuery(senterSporring);
		String returnString = "ID \t\tNavn \n";
		while (rs.next()) {
			returnString += rs.getString("TreningssenterID") + "\t\t" + rs.getString("Navn") + "\n";
		}
		return returnString;
	}
	
	public String apparatPaSenter(int treningssenterID) throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String apparatSporring = "SELECT  ApparatID, Navn "
								+ "FROM Apparat NATURAL JOIN ApparatITreningssenter "
								+ "WHERE TreningssenterID = " + treningssenterID;
		ResultSet rs = statement.executeQuery(apparatSporring);
		String returnString = "ID\t Navn \n";
		while (rs.next()) {
			returnString += rs.getString("ApparatID") + "\t " + rs.getString("Navn") + "\n";
		}
		return returnString;
	}
	
	public String oktPaSenter(int senterID) throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String oktSporring = "SELECT  TreningsoktID, Dato, Prestasjon, Form, Notat "
							+ "FROM Treningsokt "
							+ "WHERE TreningssenterID = " + senterID;
		ResultSet rs = statement.executeQuery(oktSporring);
		String returnString = "ID   Dato\t  Prestasjon    Form    Notat\n";
		while (rs.next()) {
			returnString += rs.getString(1) + "    "
						+ rs.getString(2) + "\t      "
						+ rs.getString(3) + "\t\t"
						+ rs.getString(4) + "\t"
						+ rs.getString(5) + "\n";
		}
		return returnString;
	}
}
