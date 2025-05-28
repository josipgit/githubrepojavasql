package J23PrBinaryPdfFileTextFile;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class J23BinaryFileInputOutputStream {

    public static void main(String[] args) {
        String putanjaOriginalFajle = null; // u ovu var spremamo putanju ulaznog fajla
        String putanjaIzlazneFajle = null; // putanja izlaznog fajla
        Scanner scanner = new Scanner(in).useLocale(Locale.US); // Kreiramo Scanner objekt za unos s tipkovnice
        InputStream ulaz = null; // Deklariramo ulazni stream za čitanje binarnih podataka
        OutputStream izlaz = null; // Deklariramo izlazni stream za pisanje binarnih podataka

        try {
            // Unos putanje ulaznog fajla
            out.print("Unesite putanju originalne datoteke: ");
            // C:\Users\josip\IntelliJProjects\JavaTecaj\src\J23PrBinaryPdfFileTextFile\original.pdf
            putanjaOriginalFajle = scanner.nextLine(); // Čitamo unos korisnika
            File originalFajla = new File(putanjaOriginalFajle); // Kreiramo File objekt za ulaznu datoteku
            ulaz = new BufferedInputStream(new FileInputStream(originalFajla)); // Koristimo BufferedInputStream radi bolje performanse

            // Čitanje binarnih podataka
            ByteArrayOutputStream buffer = new ByteArrayOutputStream(); // Kreiramo buffer za pohranu pročitanih podataka
            byte[] byteChunk = new byte[4096]; // Postavljamo veličinu bafera na 4KB
            int bytesRead;
            while ((bytesRead = ulaz.read(byteChunk)) != -1) { // Čitamo podatke u dijelovima dok ne dođemo do kraja
                buffer.write(byteChunk, 0, bytesRead); // Pohranjujemo pročitanih podataka u buffer
            }
            byte[] fileData = buffer.toByteArray(); // Pohranjujemo sve pročitane podatke u byte array

            // Unos putanje izlaznog fajla
            out.print("Unesite putanju kopirane datoteke: ");
            // C:\Users\josip\IntelliJProjects\JavaTecaj\src\J23PrBinaryPdfFileTextFile\kopija.pdf
            putanjaIzlazneFajle = scanner.nextLine(); // Čitamo putanju izlazne datoteke
            File izlaznaFajla = new File(putanjaIzlazneFajle); // Kreiramo File objekt za izlaznu datoteku

            // Ako izlazni fajl već postoji, ispisujemo njegov sadržaj i brišemo ga
            if (izlaznaFajla.exists()) {
                out.println("Izlazni fajl vec postoji. Sadrzaj (prvih 100 bajtova):");

                try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(izlaznaFajla))) {
                    byte[] existingData = new byte[100]; // Čitamo prvih 100 bajtova za prikaz
                    int readBytes = reader.read(existingData);
                    if (readBytes > 0) {
                        for (int i = 0; i < readBytes; i++) {
                            out.print((char) existingData[i]); // Ispisujemo sadržaj u obliku znakova
                        }
                        out.println();
                    }
                }

                out.println("Brisem postojeci izlazni fajl: ");
                if (!izlaznaFajla.delete()) { // Brišemo postojeću izlaznu datoteku
                    System.err.println("Greska: Ne mogu obrisati postojeci izlazni fajl!");
                    return; // Ako ne možemo obrisati, prekidamo program
                }
            }

            // Kreiramo izlazni fajl i upisujemo podatke sa izlaza preko stream-a u izlaznu fajlu
            izlaz = new DataOutputStream(new FileOutputStream(izlaznaFajla)); // Kreiramo izlazni stream
            izlaz.write(fileData); // Upisujemo sve binarne podatke u datoteku

            out.println("VAZNO!!!! Prvo treba zatvoriti ulaz i izlaz da bi mogli izbrisati fajl !!!!");

            if (ulaz != null) ulaz.close();  // Zatvaramo ulazni stream
            if (izlaz != null) izlaz.close();  // Zatvaramo izlazni stream

            out.println("Program uspjesno izvrsen, stara fajla obrisana, ulaz i izlaz zatvoreni !!!!");

            // Pitamo korisnika želi li obrisati novostvoreni fajl
            out.print("Zelite li izbrisati novostvoreni fajl? (Y/N): ");
            String userInput = scanner.nextLine().trim().toUpperCase(); // Čitamo unos korisnika i pretvaramo u velika slova

            if (userInput.equals("Y")) { // Ako korisnik želi obrisati fajl
                if (izlaznaFajla.delete()) {
                    out.println("Novostvoreni fajl uspjesno obrisan.");
                } else {
                    System.err.println("Greska: Ne mogu obrisati novostvoreni fajl!");
                }
            } else {
                out.println("Fajl nije obrisan.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Greska jer fajl nije pronaden: " + e.getMessage()); // Ispisujemo poruku ako datoteka ne postoji
        } catch (IOException e) {
            System.err.println("Greska prilikom citanja ili pisanja je slijedeca: " + e.getMessage()); // Ispisujemo grešku kod IO operacija
        } finally {
            out.println("Kraj programa !!!!!!"); // Ispisujemo kraj programa
        }
    }
}