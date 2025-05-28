
package J26EvidencijaPolaznikaHash;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.out;

// Glavna klasa za evidenciju polaznika
class PolaznikSetTreeSetMain {
    public static void main(String[] args) {
        // Set je interface tj. skup koji nije sortiran i ne sadrzi duplikate
        // TreeSet jer klasa ciji su elementi sortirani u binarnom stablu
        // Kreiramo TreeSet koji automatski održava sortiran redoslijed
        // Koristi compareTo metodu iz Polaznik klase za sortiranje
        Set<Polaznik> polaznici = new TreeSet<>(); // HashSet nije kompatibilan sa TreeSet, treba Set

        // Scanner za čitanje korisničkog unosa
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Ispis glavnog izbornika
            out.println("\n--- Evidencija polaznika ---");
            out.println("1. Unos novog polaznika");
            out.println("2. Ispis svih polaznika");
            out.println("3. Pretraži polaznika po e-mailu");
            out.println("4. Izlaz");
            out.print("Odaberite opciju: ");

            // Čitanje korisničkog unosa
            int opcija = scanner.nextInt();
            scanner.nextLine();  // ovo treba da se ocisti newline iz buffera nakon nextInt()
            /*
               Objašnjenje zašto je potrebna linija "scanner.nextLine();" :
               Kada korisnik unese broj i pritisne Enter, nextInt() pročita samo broj,
               ali newline znak (\n) ostane u bufferu. Ako odmah nakon toga pozovemo
               nextLine(), ona će pročitati samo taj preostali newline i vratiti prazan string.
               Zato se čisti buffer pozivom scanner.nextLine() nakon nextInt().
             */

            // Switch-case za obradu odabira korisnika
            switch (opcija) {
                case 1: // Unos novog polaznika
                    // Unos novog polaznika
                    out.print("Unesite ime: ");
                    String ime = scanner.nextLine();
                    out.print("Unesite prezime: ");
                    String prezime = scanner.nextLine();
                    out.print("Unesite e-mail: ");
                    String email = scanner.nextLine().trim();   // trim je da ukloni razmake

                    // provjeri postoji li vec polaznik sa takvim emailom
                    boolean postoji = false;
                    for (Polaznik p : polaznici) {
                        if (p.getEmail().equalsIgnoreCase(email)) {
                            postoji = true;
                            break;
                        }
                    }

                    if (postoji) {
                        out.println("Polaznik s tim e‑mailom već postoji!");
                    } else {
                        Polaznik noviPolaznik = new Polaznik(ime, prezime, email);
                        polaznici.add(noviPolaznik);
                        out.println("Polaznik je uspješno dodan!");
                    }
                    break;

                case 2: // Ispis svih polaznika
                    if (polaznici.isEmpty()) {
                        out.println("Nema unesenih polaznika.");
                    } else {
                        out.println("\n--- Popis polaznika ---");
                        // Enhanced for petlja za prolazak kroz sve polaznike
                        for (Polaznik p : polaznici) {
                            // Ispis polaznika koristeći toString() metodu
                            out.println(p);
                        }
                    }
                    break;

                case 3: // Pretraživanje po e-mailu
                    out.print("Unesite e-mail za pretragu: ");
                    String trazeniEmail = scanner.nextLine(); // Čitanje emaila za pretragu
                    boolean pronaden = false; // flag za pronalazak

                    // Prolazak kroz sve polaznike
                    for (Polaznik p : polaznici) {
                        // Usporedba emailova (ignorira velika/mala slova)
                        if (p.getEmail().equalsIgnoreCase(trazeniEmail)) {
                            out.println("Pronađen polaznik: " + p);
                            pronaden = true; // Postavi zastavicu
                            break; // Prekini petlju nakon pronalaska
                        }
                    }

                    // Ako polaznik nije pronađen
                    if (!pronaden) {
                        out.println("Polaznik s e-mailom '" + trazeniEmail + "' nije pronađen.");
                    }
                    break;

                case 4: // Izlaz iz programa
                    out.println("Izlazim iz programa...");
                    scanner.close(); // Zatvaranje Scanner resursa
                    System.exit(0);  // Prekid programa s statusom 0 (uspješan završetak)
                    /* System.exit(0) služi za prekid rada Java programa i zatvaranje svih njegovih procesa.
                       Argument 0 označava da je program završio bez grešaka. */

                default: // Krivi unos
                    out.println("Nevažeća opcija! Pokušajte ponovno.");
            }
        }
    }
}

