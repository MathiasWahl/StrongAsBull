import java.sql.*;

public class Apparat {

	private static int apparatIDCounter = 1;
	private int apparatID;
	private String navn;
	private String beskrivelse;
	
	public Apparat(String navn, String beskrivelse) {
		this.apparatID = apparatIDCounter;
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		apparatIDCounter++;
		
		try {
			
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Apparat "
						+" (ApparatID, Navn, Beskrivelse)"
						+" VALUES(" + this.apparatID + ", '" + this.navn + "', '" + this.beskrivelse + "')";
			statement.executeUpdate(sql);
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
}
