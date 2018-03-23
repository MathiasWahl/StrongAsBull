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

		Registrering.registrerApparat("Benk", "Min beste venn");
		Registrering.registrerApparat("Knebøy", "Min værste fiende");
		Registrering.registrerOvelsegruppe("Rygg");
		Registrering.registrerOvelse("Markløft", 1, "fri", 0, "Løfte");
		Registrering.registrerOvelse("Markløft", 1, "fastmontert", 1, "");
		Registrering.registrerTreningssenter("Gløs", 5, 100, "Gløshaugen");
		Registrering.registrerTreningsokt("2018-03-21", "19:01:00", 2, 10, 5, "Mye curls", 1);
		// Denne lager duplikat primærnøkkel hvis man kjører Main 2 ganger:
		// Registrering.registrerOvelseITreningsokt(1,1,50,12,3);
		// Registrering.registrerOvelseITreningsokt(1, 1, 100, 10, 3);

		Scanner reader = new Scanner(System.in);

		meny(reader);

		reader.close();

		System.out.println("Velkommen tilbake! :)");
	}

	public static void meny(Scanner reader) throws SQLException {
		Sporring getter = new Sporring();
		int svar = -1;
		while (svar != 0) {
			System.out.println("Skriv: "
					+ "\n0 for å avslutte "
					+ "\n1 for å registrere informasjon "
					+ "\n2 for å se de de siste treningsøktene "
					+ "\n3 for å se resultatlogg i en enkelt øvelse "
					+ "\n4 for å se øvelse i en øvelsegruppe "
					+ "\n5 for å sjekke ut treningssentere!");
			svar = reader.nextInt();
			if (svar == 1) {
				registrere(reader, getter);
			} else if (svar == 2) {
				seNØkter(reader, getter);
			} else if (svar == 3) {
				seOvelseSisteNDager(reader, getter);
			} else if (svar == 4) {
				seOvelserIOvelsegruppe(reader, getter);
			} else if (svar == 5) {
				treningssenterMeny(reader, getter);
			}
		}

	}

	public static void registrere(Scanner reader, Sporring getter) throws SQLException {


		System.out.println("Skriv:"
				+ "\n0 for å avslutte"
				+ "\n1 for apparat"
				+ "\n2 for Ovelsegruppe"
				+ "\n3 for Ovelse"
				+ "\n4 for Treningssenter"
				+ "\n5 for Treningsøkt"
				+ "\n6 for Ovelse i treningsøkt"
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
			//Øvelsegruppe
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn gruppens beskrivelse:");
			String description = reader.nextLine();
			Registrering.registrerOvelsegruppe(description);

		} else if (svar == 3) {
			//Øvelse
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn øvelsens navn:");
			String name = reader.nextLine();


			System.out.println("Skriv inn øvelsegruppeID. Du har disse å velge mellom:");
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

			System.out.println("Skriv inn øvelsens beskrivelse:");
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

			System.out.println("Skriv inn senterets størelse (i kvm):");
			int size = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn senterets beliggenhet:");
			String location = reader.nextLine();

			Registrering.registrerTreningssenter(name, rating, size, location);

		} else if (svar == 5) {
			//Treninsøkt
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn øktens dato (YYYY-MM-DD):");
			String date = reader.nextLine();

			System.out.println("Skriv inn øktens tidspunkt (HHMM):");
			String time = reader.nextLine();

			System.out.println("Skriv inn øktens varighet (i minutter):");
			int duration = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn øktens formrangering (1-10):");
			int form = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn øktens prestasjonsivå (1-10):");
			int quality = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn eventuelle notater til økten:");
			String notes = reader.nextLine();

			System.out.println("Skriv inn øktens treningssenters ID:");
			int centerID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			Registrering.registrerTreningsokt(date, time, duration, form,
		    		quality, notes, centerID);
		} else if (svar == 6) {
			//Øvelse i treningsøkt
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn treningsøktens ID (viser opptil de 10 siste):");
			System.out.println(getter.treningsOkter());
			int oktID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn øvelsens ID, du kan velge mellom disse:");
			System.out.println(getter.ovelser());
			int exerciseID = reader.nextInt();
			reader.nextLine(); // Consume empty nextLine

			System.out.println("Skriv inn antall kilo (per løft):");
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

	public static void seNØkter(Scanner reader, Sporring getter) throws SQLException {
		System.out.println("Skriv inn hvor mange av de siste gjennomførte øktene du vil se info om:");
		int n = reader.nextInt();
		String info = getter.nSisteTreninger(n);
		System.out.println(info);
	}

	public static void seOvelseSisteNDager(Scanner reader,Sporring getter) throws SQLException {
		System.out.println("Skriv inn øvelseID, du har disse å velge mellom:");
		System.out.println(getter.ovelser());
		int exercizeID = reader.nextInt();
		System.out.println("Skriv inn hvor mange dager bakover du vil se økter for:");
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
				+ "\n0 for å avslutte "
				+ "\n1 for å se alle treningssentere "
				+ "\n2 for å se alle apparater på et treningssenter "
				+ "\n3 for å se treningsøkter på et treningssenter ");
		int svar = reader.nextInt();
		if (svar == 1) {
			System.out.println("Treningssentere:");
			getter.treningssentere();
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
