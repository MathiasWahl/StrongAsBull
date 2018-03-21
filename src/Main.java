import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
		DBConn.connectDB();
		


		Registrering.runSetCounter();

		Registrering.registrerApparat("Benk", "Min beste venn");
		Registrering.registrerApparat("asgd", "Misgfdenn");
		Registrering.registrerOvelsegruppe("Rygg");
		Registrering.registrerOvelse("Markløft",1, 2, 0, "Løfte");
		Registrering.registrerOvelse("Markløft",1, 1, 1, "");
		Registrering.registrerTreningssenter("Gløs", 5, 100, "Gløshaugen");
		Registrering.registrerTreningsøkt("2018-03-21", "19:01:00", 2, 10, 5, "Mye curls", 1);
		//Denne lager duplikat primærnøkkel hvis man kjører Main 2 ganger:
		//Registrering.registrerOvelseITreningsokt(1, 1, 100, 10, 3);
		
		Scanner reader = new Scanner(System.in);
		
		meny(reader);

		reader.close();
		
		System.out.println("Ferdig");
	}
	
	public static void meny(Scanner reader) {
		System.out.println("Skriv 1 for å registrere informasjon: ");
		int svar = reader.nextInt();
		if (svar == 1) {
			registrere(reader);
		}
	}
	public static void registrere(Scanner reader) {
		System.out.println("Skriv:\n1 for apparat\n2 for Ovelsegruppe\n3 for Ovelse\n4 for Treningssenter\n5 for Treningsøkt\n6 for Ovelse i treningsøkt\n7 for Apparat i treningssenter");
		int svar = reader.nextInt();
		if (svar == 1) {
			
		} else if (svar == 2) {
			
		} else if (svar == 3) {
			
		} else if (svar == 4) {
			
		} else if (svar == 5) {
			
		} else if (svar == 6) {
			
		} else if (svar == 7) {
			
		}
	}
}