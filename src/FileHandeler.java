/* *****************************************************************
 * FileHandeler.java
 * Skrevet av:	Vegard Krudtå Langås og Christoffer Müller Nordeng
 * 
 * Oblig 4 Vår 2011 Datateknikk år 1
 * 					Høgskolen i Narvik
 * 	
 * Versjon:		1.0
 * Dato:		3. December 2011
 * Copyright:	Modified BSD license aka BSD 3-clause license.
 * 				See LICENSE.md
 * 
 * Info:		Handterer lesing og skriving av filer.
 * 				Lesing av filer gjøres på en linje for linje basis
 * ***************************************************************** */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class FileHandeler {
	// Filhandtereren kan handtere 1 fil
	private ArrayList<String> file;
	private boolean initiated;

	public FileHandeler() {
		// Kan ikke lese en fil før man har åpnet den
		initiated = false;
		file = new ArrayList<String>();

	}

	// Lar brukeren grafiskt åpne og velge filen sin
	public void openFile() {

		JFileChooser chooser = new JFileChooser();

		int status = chooser.showOpenDialog(null);

		// Hvis brukeren ikke velger en fil
		if (status != JFileChooser.APPROVE_OPTION) {
			System.out.println("Ingen fil valgt");
		} else { // Hvis brukeren velger en fil
			try {
				Scanner scan = new Scanner(chooser.getSelectedFile());
				while (scan.hasNext()) {
					String line = scan.nextLine();
					if (!line.startsWith("#"))
						file.add(line);
				}
				initiated = true;
			} catch (Exception e) {
				int i = 0;
				i++;
			}
		}
	}

	// Lar brukeren grafiskt plassere filen sin og bestemme filnavn
	public void saveFile(String text) {
		JFileChooser chooser = new JFileChooser();

		int status = chooser.showSaveDialog(null);

		// Hvis brukeren ikke velger en plassering og filnavn
		if (status != JFileChooser.APPROVE_OPTION) {
			System.out.println("Ingen fil valgt");
		} else { // Hvis brukeren velger en plassering og filnavn
			File outFile = chooser.getSelectedFile();
			try { // Fors¿ker Œ skrive filen
				BufferedWriter fileOut = new BufferedWriter(new FileWriter(
						outFile));
				fileOut.write(text);
				fileOut.flush();
				fileOut.close();
			} catch (IOException e) { // Hvis det feiler så har jeg ikke mye å
										// fåreslå, kanskje programmet ikke har
										// skrivetilgang
				// Auto-generated catch block
				e.printStackTrace();
			}
			// Sørger for at ikke noen programmer leser og skriver om en annen
			// slik at det blir rot i hvilken fil brukeren velger
			initiated = false;
		}
	}

	public ArrayList<String> getFile() {
		return file;
	}

	// Returnerer en og en linje fra filen
	public String getLine(int n) {
		// Hvis programmet har åpnet en fil
		if (initiated) {
			return file.get(n);
		}
		// Nå er filen tom eller ikke åpnet
		return ("Line not found!");
	}

	public void shuffle() {
		if (initiated) {
			Collections.shuffle(file);
		}
	}
}