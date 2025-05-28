package J26EvidencijaPolaznikaHash;

import java.util.*;

import static java.lang.System.out;

public class PolaznikMapHashMapMain {

    public static void main(String[] args) {

        //dakle kljucevi tipa String su emailovi <KEY, VALUE> = <email, polaznik>
        HashMap<String, Polaznik> polaznici = new HashMap<>();
        //Map<String, Polaznik> polaznici = new TreeMap<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            out.println("\n--- Evidencija polaznika ---");
            out.println("1. Unos novog polaznika");
            out.println("2. Ispis svih polaznika");
            out.println("3. Pretraži polaznika po e-mailu");
            out.println("4. Izlaz");
            out.print("Odaberite opciju: ");

            int opcija = scanner.nextInt();
            scanner.nextLine(); // ovo treba da se ocisti newline iz buffera nakon nextInt()

            switch (opcija) {
                case 1: // Unos novog polaznika
                    out.print("Unesite ime: ");
                    String ime = scanner.nextLine();
                    out.print("Unesite prezime: ");
                    String prezime = scanner.nextLine();
                    out.print("Unesite e-mail: ");
                    String email = scanner.nextLine().trim();

                    if (polaznici.containsKey(email)) {
                        out.println("Polaznik s tim e‑mailom već postoji!");
                    } else {
                        polaznici.put(email, new Polaznik(ime, prezime, email));
                        out.println("Polaznik je uspješno dodan!");
                    }
                    break;

                case 2: // Ispis svih polaznika
                    if (polaznici.isEmpty()) {
                        out.println("Nema unesenih polaznika.");
                    } else {
                        out.println("\n--- Popis polaznika (sortirano po e‑mailu) ---");
                        for (Polaznik p : polaznici.values()) {   // već sortirano
                            out.println(p);
                        }
                        List<Polaznik> izmijesaniPolaznici = new ArrayList<>(polaznici.values());
                        Collections.shuffle(izmijesaniPolaznici);
                        for (Polaznik p : izmijesaniPolaznici) {   // izmijesani polaznici
                            out.println(p);
                        }
                    }
                    break;

                case 3: // Pretraživanje po e-mailu
                    out.print("Unesite e-mail za pretragu: ");
                    String trazeni = scanner.nextLine().trim();

                    Polaznik pronadjen = polaznici.get(trazeni);
                    if (pronadjen != null) {
                        out.println("Pronađen polaznik: " + pronadjen);
                    } else {
                        out.println("Polaznik s e‑mailom '" + trazeni + "' nije pronađen.");
                    }
                    break;

                case 4: // Izlaz iz programa
                    out.println("Izlazim iz programa...");
                    scanner.close();
                    System.exit(0);

                default: // Krivi unos
                    out.println("Nevažeća opcija! Pokušajte ponovno.");
            }
        }
    }
}
