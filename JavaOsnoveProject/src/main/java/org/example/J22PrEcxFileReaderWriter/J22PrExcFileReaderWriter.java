package J22PrEcxFileReaderWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class J22PrExcFileReaderWriter {
    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\josip\\IntelliJProjects\\JavaTecaj\\src\\J22Pr\\datoteka.txt");
        File outputFile1 = new File("C:\\Users\\josip\\IntelliJProjects\\JavaTecaj\\src\\J22Pr\\kopija.txt");
        File outputFile2 = new File("C:\\Users\\josip\\IntelliJProjects\\JavaTecaj\\src\\J22Pr\\kopija2.txt");

        // First file operation
        Reader ulaz1 = null;
        Reader ulaz2 = null;
        Writer izlaz = null;

        // List to store names
        List<String> names = new ArrayList<>();

        try {
            ulaz1 = new FileReader(inputFile); // reads from the file
            ulaz2 = new StringReader("Ovo je ulazni string !!!!!!!!!!!!!!!!!!!");
            izlaz = new FileWriter(outputFile1);

            int znakovi; // za unicode charova
            StringBuilder stringBuilder = new StringBuilder();
            while ((znakovi = ulaz1.read()) != -1) {
                // the value -1 is commonly used to indicate the end of a stream or file when
                // reading data. Specifically, in this context, the read() method of a Reader
                // (like FileReader or StringReader) returns the next character as an integer.
                // When the end of the stream is reached, it returns -1 to signal that no more data is available
                char ch = (char) znakovi;
                // The read() method returns the Unicode value (integer) of the character,
                // which can be cast to a char to get the actual character
                if (ch == ';') {
                    // Split names when semicolon is encountered
                    names.add(stringBuilder.toString());
                    stringBuilder.setLength(0); // Reset the StringBuilder for the next name
                } else {
                    stringBuilder.append(ch); // Add the character to the current name
                }
            }
            // Add the last name (if any)
            if (stringBuilder.length() > 0) {
                names.add(stringBuilder.toString());
            }

            // Print out the names list, each on a new line
            for (String name : names) {
                System.out.println(name); // Print each name on a new line
            }

            // Write the names into outputFile1 (or do any other required operations)
            for (String name : names) {
                izlaz.write(name + "\n"); // Write each name to the output file
            }

        } catch (FileNotFoundException e) {
            System.err.println("Greska jer fajl nije pronaden: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Greska prilikom citanja ili pisanja je slijedeca: " + e.getMessage());
        } finally {
            try {
                if (ulaz1 != null) ulaz1.close();  // Properly close Reader
                if (izlaz != null) izlaz.close();  // Properly close Writer
            } catch (IOException e) {
                System.err.println("Greska prilikom zatvaranja ulaza ili izlaza: " + e.getMessage());
            }
        }

        // Second file operation with PrintWriter
        // try-with-resources already handles closing of izlaz2 and rucniUpis, so you don't need the finally block for those resources.
        // The variable izlaz2 is already declared inside the try-with-resources, so there's no need to manually close it in finally
        try (
                Writer izlaz2 = new FileWriter(outputFile2);
                PrintWriter rucniUpis = new PrintWriter(izlaz2)
        ) {
            rucniUpis.println("alooooooooooo"); // Ensures output is written
            rucniUpis.println("eeeeeeeeeeeeeeeee"); // Ensures output is written
            rucniUpis.println("iiiiiiiiiiiiiiiiii"); // Ensures output is written

        } catch (IOException e) {
            System.err.println("Greska prilikom pisanja u kopija2.txt: " + e.getMessage());
        }

    } // end main
} // end class
