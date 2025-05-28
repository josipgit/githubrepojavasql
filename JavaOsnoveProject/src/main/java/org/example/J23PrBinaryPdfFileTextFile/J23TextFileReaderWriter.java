package J23PrBinaryPdfFileTextFile;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

class J23TextFileReaderWriter {
    public static void main(String[] args) {
        String ulazniFile = null; // u ovu var spremamo putanju na ulazni fajl
        String izlazniFile = null; // putanja izlaznog fajla
        Scanner scanner = new Scanner(in).useLocale(Locale.US);
        Reader ulaz = null;
        Writer izlaz = null;

        try
        {
            out.print("Unesite putanju originalne datoteke: ");
            // C:\Users\josip\IntelliJProjects\JavaTecaj\src\J23PrBinaryPdfFileTextFile\original.txt
            ulazniFile = scanner.nextLine(); // unosimo putanju do ulaznog fajla
            File inputFile = new File(ulazniFile);
            ulaz = new FileReader(inputFile); // reads from the file

            int znakovi;
            StringBuilder stringBuilder = new StringBuilder();
            while ((znakovi = ulaz.read()) != -1) {
                char ch = (char) znakovi;
                stringBuilder.append(ch); // Add the character to the current name
            }

            out.print("Unesite putanju kopirane datoteke: ");
            // C:\Users\josip\IntelliJProjects\JavaTecaj\src\J23PrBinaryPdfFileTextFile\kopija.pdf
            izlazniFile = scanner.nextLine(); // unosimo putanju do izlaznog fajla
            izlaz = new FileWriter(izlazniFile);
            PrintWriter rucniUpis = new PrintWriter(izlaz);
            rucniUpis.println(stringBuilder); // Ensures output is written

            out.print("Program uspjesno izvrsen !!!!!!!");

        } catch (FileNotFoundException e) {
            System.err.println("Greska jer fajl nije pronaden: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Greska prilikom citanja ili pisanja je slijedeca: " + e.getMessage());
        } finally {
            try {
                if (ulaz != null) ulaz.close();  // Properly close Reader
                if (izlaz != null) izlaz.close();  // Properly close Writer
            } catch (IOException e) {
                System.err.println("Greska prilikom zatvaranja ulaza ili izlaza: " + e.getMessage());
            }
        }


    } // end main
} // end class