package project;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import project.Sporring.*;

public class Main {

	public static void main(String[] args) throws SQLException {

		DBConn.connectDB();

		Registrering.runSetCounter();

		//Statement statement = DBConn.getConnection().createStatement();
		//statement.executeUpdate("DROP DATABASE setup");

		Scanner reader = new Scanner(System.in);

		meny(reader);

		reader.close();

		System.out.println("Velkommen tilbake! :)");
	}

	public static void meny(Scanner reader) throws SQLException {
		boolean generated = false;
		Sporring getter = new Sporring();
		int svar = -1;
		while (svar != 0) {
			System.out.println("\n-Hovedmeny-\nSkriv: "
					+ "\n0 for aa avslutte "
					+ "\n1 for aa registrere informasjon "
					+ "\n2 for aa se de de n sist utførte treningsoktene "
					+ "\n3 for aa se resultatlogg i en enkelt ovelse "
					+ "\n4 for aa se ovelser i en ovelsegruppe "
					+ "\n5 for aa sjekke ut treningssentere!"
					+ "\n6 for aa generere testdata (kan kun gjores en gang)");

			svar = reader.nextInt();
			if (svar == 1) {
				registrere(reader, getter);
			} else if (svar == 2) {
				seNOkter(reader, getter);
			} else if (svar == 3) {
				seOvelseSisteNDager(reader, getter);
			} else if (svar == 4) {
				seOvelserIOvelsegruppe(reader, getter);
			} else if (svar == 5) {
				treningssenterMeny(reader, getter);
			}	else if (svar == 6) {
				if (!generated) {
					genererData(getter);
					generated = true;
					System.out.println("Testdata generert!");
				} else {
					System.out.println("Du kan kun generere testdata en gang!");
				}
			}
		}

	}
		
		public static void genererData(Sporring getter) throws SQLException{
			Registrering.registrerTreningssenter("Golds Gym", 10, 250, "Moholt");
			Registrering.registrerApparat("Benk", "Min beste venn");
			Registrering.registrerApparatITreningssenter(1, 1); 				// Putter benken i golds gym
			Registrering.registrerApparat("Squatrack", "Min vaerste fiende");
			Registrering.registrerApparatITreningssenter(1, 2); 				// Putter squatracken i golds gym
			Registrering.registrerOvelsegruppe("Bryst"); 					//Gruppe 1 er bryst
			Registrering.registrerOvelsegruppe("Bein"); 						//Gruppe 2 er bein
			Registrering.registrerOvelsegruppe("Armer"); 					//Gruppe 3 er armer
			Registrering.registrerOvelse("Benk", 1, "fastmontert", 1, "Press stanga opp");
			Registrering.registrerOvelse("Squats", 2, "fastmontert", 2, "Prov aa overleve");
			Registrering.registrerOvelse("Curls", 3, "fri", 0, "Make your guns fire");
			
			Registrering.registrerTreningsokt("2018-01-01", "17:35:00", 46, 10, 10, "Vktig med en liten bissaøkt etter nyttårs", 1);
			Registrering.registrerOvelseITreningsokt(2, 3, 50, 10, 4);	//Curls
			
			Registrering.registrerTreningsokt("2018-03-21", "17:35:00", 117, 8, 10, "Lite squats med dårlig form, mye curls med stram gjennomføring", 1);
			Registrering.registrerOvelseITreningsokt(1, 1, 100, 10, 3);	//Benk
			Registrering.registrerOvelseITreningsokt(1, 2, 30, 6, 3);		//Squats
			Registrering.registrerOvelseITreningsokt(1, 3, 52, 8, 3);		//Curls
			
			System.out.println("Testdata generert. Disse oktene er registrert:");
			System.out.println(getter.nSisteTreninger(2));
			System.out.println("I okten som var 2018-03-21 er ovelsene benk, squats og curls utført");
			System.out.println("I okten som var 2018-01-01 er kun ovelsen curls utført");
		}

	public static void registrere(Scanner reader, Sporring getter) throws SQLException {


		System.out.println("Skriv:"
				+ "\n0 for aa avslutte"
				+ "\n1 for apparat"
				+ "\n2 for Ovelsegruppe"
				+ "\n3 for Ovelse"
				+ "\n4 for Treningssenter"
				+ "\n5 for Treningsokt"
				+ "\n6 for Ovelse i treningsokt"
				+ "\n7 for Apparat i treningssenter");
		int svar = reader.nextInt();
		if (svar == 0) {
			//continue
		} else if (svar == 1) {
			//Apparat
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn apparatets navn:");
			String name = reader.nextLine();

			System.out.println("Skriv inn apparatets beskrivelse:");
			String description = reader.nextLine();

			// if apparat not in apparater:
			Registrering.registrerApparat(name, description);

		} else if (svar == 2) {
			//Ovelsegruppe
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn gruppens beskrivelse:");
			String description = reader.nextLine();
			Registrering.registrerOvelsegruppe(description);

		} else if (svar == 3) {
			//Ovelse
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn ovelsens navn:");
			String name = reader.nextLine();


			System.out.println("Skriv inn ovelsegruppeID. Du har disse aa velge mellom:");
			String choices = getter.ovelsesGrupper();
			System.out.println(choices);
			int groupID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn type(fri/fastmontert):");
			String type = reader.nextLine();
			while (!(type.equals("fri")) && !(type.equals("fastmontert"))) {
				System.out.println("Skriv inn enten fri eller fastmontert:");
				type = reader.nextLine();
			}
			int gearID = 0;
			if (type.equals("fastmontert")) {
				gearID = reader.nextInt();
				reader.nextLine(); // Consume empty nextLine
			}

			System.out.println("Skriv inn ovelsens beskrivelse:");
			String description = reader.nextLine();

			Registrering.registrerOvelse(name, groupID, type, gearID,  description);

			//if groupID

		} else if (svar == 4) {
			//Treningssenter
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn senterets navn:");
			String name = reader.nextLine();

			System.out.println("Skriv inn senterets rangering (1-10):");
			int rating = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn senterets storelse (i kvm):");
			int size = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn senterets beliggenhet:");
			String location = reader.nextLine();

			Registrering.registrerTreningssenter(name, rating, size, location);

		} else if (svar == 5) {
			//Treninsokt
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn oktens dato (YYYY-MM-DD):");
			String date = reader.nextLine();

			System.out.println("Skriv inn oktens tidspunkt (HHMM):");
			String time = reader.nextLine();

			System.out.println("Skriv inn oktens varighet (i minutter):");
			int duration = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn oktens formrangering (1-10):");
			int form = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn oktens prestasjonsivaa (1-10):");
			int quality = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn eventuelle notater til okten:");
			String notes = reader.nextLine();

			System.out.println("Skriv inn oktens treningssenters ID:");
			int centerID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			Registrering.registrerTreningsokt(date, time, duration, form,
		    		quality, notes, centerID);
		} else if (svar == 6) {
			//Ovelse i treningsokt
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn treningsoktens ID (viser opptil de 10 siste):");
			System.out.println(getter.treningsOkter());
			int oktID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn ovelsens ID, du kan velge mellom disse:");
			System.out.println(getter.ovelser());
			int exerciseID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn antall kilo (per loft):");
			int kg = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn antall repetisjoner (per sett):");
			int reps = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn antall sett:");
			int sett = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			Registrering.registrerOvelseITreningsokt(oktID, exerciseID, kg, reps,  sett);

		} else if (svar == 7) {
			//Apparat i treningssenter
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn treningssenterID:");
			int treningssenterID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn apparatID:");
			int apparatID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			Registrering.registrerApparatITreningssenter(treningssenterID, apparatID);

		}
	}

	public static void seNOkter(Scanner reader, Sporring getter) throws SQLException {
		System.out.println("Skriv inn hvor mange av de siste gjennomforte oktene du vil se info om:");
		int n = reader.nextInt();
		String info = getter.nSisteTreninger(n);
		System.out.println(info);
	}

	public static void seOvelseSisteNDager(Scanner reader,Sporring getter) throws SQLException {
		System.out.println("Skriv inn ovelseID, du har disse aa velge mellom:");
		System.out.println(getter.ovelser());
		int exercizeID = reader.nextInt();
		System.out.println("Skriv inn hvor mange dager bakover du vil se okter for:");
		int n = reader.nextInt();
		String info = getter.ovelseSisteNDager(exercizeID, n);
		System.out.println(info);
	}

	public static void seOvelserIOvelsegruppe(Scanner reader,Sporring getter) throws SQLException {
		System.out.println("Velg en OvelsegruppeID: ");
		System.out.println(getter.ovelsesGrupper());
		int ovelsegruppeID = reader.nextInt();
		System.out.println(getter.ovelserIGruppe(ovelsegruppeID));
	}

	public static void treningssenterMeny(Scanner reader, Sporring getter) throws SQLException {
		System.out.println("Skriv: "
				+ "\n0 for aa avslutte "
				+ "\n1 for aa se alle treningssentere "
				+ "\n2 for aa se alle apparater paa et treningssenter "
				+ "\n3 for aa se treningsokter paa et treningssenter ");
		int svar = reader.nextInt();
		if (svar == 1) {
			System.out.println("Treningssentere:\n"
			+ getter.treningssentere());
		} else if (svar == 2) {
			System.out.println("Velg treningssenterID:\n"
					+ getter.treningssentere());
			int svar1 = reader.nextInt();
			System.out.println(getter.apparatPaSenter(svar1));
		} else if (svar == 3) {
			System.out.println("Velg treningssenterID:\n"
					+ getter.treningssentere());
			int svar2 = reader.nextInt();
			System.out.println(getter.oktPaSenter(svar2));
		}
	}
}
