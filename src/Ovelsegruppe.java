import java.sql.Statement;

public class Ovelsegruppe {
	private static int ovelsegruppeIDCounter = 1;
	private int ovelsegruppeID;
	private String beskrivelse;
	
	public Ovelsegruppe(String beskrivelse) {
		this.ovelsegruppeID = ovelsegruppeIDCounter;
		this.beskrivelse = beskrivelse;
		ovelsegruppeIDCounter++;
		
		try {
			
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Øvelsegruppe "
						+" (ØvelsegruppeID, Beskrivelse)"
						+" VALUES(" + this.ovelsegruppeID + ", '" + this.beskrivelse + "')";
			statement.executeUpdate(sql);
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
}
