import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Main {

	public static void main(String[] args) {
		
		DBConn.connectDB();
		


		Registrering.runSetCounter();

		Registrering.registrerApparat("Benk", "Min beste venn");
		Registrering.registrerApparat("asgd", "Misgfdenn");
		Registrering.registrerOvelsegruppe("Rygg");
		Registrering.registrerOvelse("Markl�ft",1);
		Registrering.registrerTreningssenter("Gl�s", 5, 100, "Gl�shaugen");
		Registrering.registrerTrenings�kt("2018-03-21", "19:01:00", 2, 10, 5, "Mye curls", 1);

		System.out.println("Ferdig");
	}
}