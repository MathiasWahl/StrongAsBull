import java.sql.Statement;

public class Ovelse {
	private static int ovelseIDCounter = 1;
	private int ovelseID;
	private String navn;
	private int ovelsegruppeID;
	
	public Ovelse(String navn, int ovelsegruppeID) {
		this.ovelseID = ovelseIDCounter;
		this.navn = navn;
		this.ovelsegruppeID = ovelsegruppeID;
		ovelseIDCounter++;
		
		try {
			
			Statement statement = DBConn.getConnection().createStatement();
			String sql = "INSERT INTO Øvelse "
						+" (ØvelseID, Navn, ØvelsegruppeID)"
						+" VALUES(" + this.ovelseID + ", '" + this.navn + "', '" + this.ovelsegruppeID + "')";
			statement.executeUpdate(sql);
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
}
