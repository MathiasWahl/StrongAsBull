import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Main {

	public static void main(String[] args) {
		
		DBConn.connectDB();
		
		Registrering.setCounters();
		Registrering.registrerApparat("Benk", "Min beste venn");
		Registrering.registrerApparat("asgd", "Misgfdenn");
		Registrering.registrerOvelsegruppe("Rygg");
		Registrering.registrerOvelse("Markløft",1);

		System.out.println("Ferdig");
	}
}