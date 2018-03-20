import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Main {

	public static void main(String[] args) {
		
		List<Apparat> apparatList = new ArrayList();
		List<Ovelse> ovelseList = new ArrayList();
		List<Ovelsegruppe> ovelsegruppeList = new ArrayList();
		
		DBConn.connectDB();
		apparatList.add(new Apparat("Benk","Min beste venn"));
		ovelsegruppeList.add(new Ovelsegruppe("Rygg"));
		ovelseList.add(new Ovelse("Markløft",1));

		
	}
}