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
		Registrering.registrerOvelse("Markløft",1);
		Registrering.registrerTreningssenter("Gløs", 5, 100, "Gløshaugen");
		Registrering.registrerTreningsøkt("2018-03-21", "19:01:00", 2, 10, 5, "Mye curls", 1);

		System.out.println("Ferdig");
	}
}