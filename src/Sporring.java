import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sporring {
	
	
	
	
	public void nSisteTreninger(int n) throws SQLException {
		Statement statement = DBConn.getConnection().createStatement();
		String treningsSporring = "SELECT TreningsoktID, Dato, Notat"
								+ "FROM Treningsokt"
								+ "ORDER BY TreningsoktID desc"
								+ "LIMIT" + n; 
		ResultSet rs = statement.executeQuery(treningsSporring);
		while(rs.next()) {
			System.out.println(rs.getRow());
		}
	}
}
