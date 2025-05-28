package J25ParcijalniIspit1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.out;

public class EvidencijaVozilaMain {
    public static void main(String[] args) throws MojaIznimka {
        ArrayList<Vozilo> lista = new ArrayList<Vozilo>();

        // instances below are outside of the try block because they need to be accessible
        // in the entire scope of the main method
        // If they were declared inside the try block, their scope would be limited to that block,
        // and they wouldn't be accessible later when adding them to the list or printing their data
        Automobil auto1;
        Automobil auto2;
        Motocikl moto1;
        Motocikl moto2;

        try {
            auto1 = new Automobil("ST-1234-ZG", "AUDI", 2022, 5);
            auto2 = new Automobil("ZG-4321-ST", "VW", 2018, 3);
            moto1 = new Motocikl("RI-9999-OS", "Harley-Davidson", 1995, "benzinski");
            moto2 = new Motocikl("OS-1111-RI", "Tomos", 1965, "benzinski");
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }

        lista.add(auto1);
        lista.add(auto2);
        lista.add(moto1);
        lista.add(moto2);

        for (Vozilo v : lista) {
            if (v instanceof Automobil) {
                out.println("Automobil: " + v.prikaziPodatke());
            } else {
                out.println("Motocikl: " + v.prikaziPodatke());
            }
        }

        out.println("----------------------------------------------------------");

        try {
            auto1.setBrojVrata(7);
        } catch (MojaIznimka e) {
            throw new IllegalArgumentException(e);
        }

        out.println(auto1.brojVrata);
        out.println(moto2.getTipMotora());

        out.println("----------------------------------------------------------");

        spremiPodatkeUDatoteku("C:\\Users\\josip\\IntelliJProjects\\JavaTecaj\\src\\J25ParcijalniIspit1\\ListaVozila.txt", lista);

        out.println("//////////////////////////////////////////////////////////");

        ucitajPodatkeIzDatoteke("C:\\Users\\josip\\IntelliJProjects\\JavaTecaj\\src\\J25ParcijalniIspit1\\ListaVozila.txt");

    } // end main

    public static void spremiPodatkeUDatoteku(String putanjaNaDatoteku, ArrayList<Vozilo> lista) {
        try (FileWriter writer = new FileWriter(putanjaNaDatoteku)) { // kad je ovako u zagradama onda se automatski close-a writer nakon upisa
            //FileWriter writes text to character files
            for (Vozilo v : lista) {
                if (v instanceof Automobil) {
                    writer.write("Automobil: " + v.prikaziPodatke() + "\n");
                } else {
                    writer.write("Motocikl: " + v.prikaziPodatke() + "\n");
                }
            }
        } catch (IOException e) {
            out.println("Error writing to file: " + e.getMessage());
        }
    }



    public static void ucitajPodatkeIzDatoteke(String putanjaNaDatoteku) {
        try (FileReader reader = new FileReader(putanjaNaDatoteku)) {
            int znakovi; // read() method reads a single character from the file as an integer
                         // which represents the Unicode value (or ASCII value for basic characters) of the read character
            StringBuilder stringBuilder = new StringBuilder();
            while ((znakovi = reader.read()) != -1) {
                stringBuilder.append((char) znakovi);
            }
            out.println("Sadržaj datoteke:");
            out.println(stringBuilder);
        } catch (IOException e) {
            out.println("Error reading file: " + e.getMessage());
        }
    }

} // end class
