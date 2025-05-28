import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

import java.util.Locale;

public class J11Dr6UnosNiza {
    public static void main(String[] args) {

        System.gc(); // garbage collection
        System.out.flush(); // clear output buffers and ensure immediate writing to the output stream
        
        out.println("\n\n-------------------Prvi nacin sa niz[]----------------------");

        Scanner scanner = new Scanner(in).useLocale(Locale.US);
        out.print("Unesite duljinu niza: ");
        int duljinaNiza = scanner.nextInt();
        int[] niz = new int[duljinaNiza];

        out.println("Duljina niza je: " + duljinaNiza);
        out.println("Duljina niza je: " + niz.length);

        out.println("Unesite " + duljinaNiza + " cijelih brojeva: ");
        for (int i = 0; i < duljinaNiza; i++) {
            niz[i] = scanner.nextInt();
        }

        int minNiz = niz[0];
        for (int num : niz) {
            if (num < minNiz) {
                minNiz = num;
            }
        }

        int maxNiz = niz[0];
        for (int num : niz) {
            if (num > maxNiz) {
                maxNiz = num;
            }
        }

        out.println("Najmanji broj u unesenoj listi je: " + minNiz);
        out.println("Najveci broj u unesenoj listi je: " + maxNiz);


        out.println("\n\n-------------------Drugi nacin sa ArrayList------------------");

        ArrayList<Integer> lista = new ArrayList<>();
        Scanner sc = new Scanner(in).useLocale(Locale.US);
        int ucitaniBroj = 0;

        while (ucitaniBroj != -1) {
            out.print("Ucitaj broj (ucitaj -1 za prekid unosa): ");
            ucitaniBroj = sc.nextInt();
            if (ucitaniBroj == -1) break; // ne ubacuj -1 u listu
            lista.add(ucitaniBroj);
        }

        int minLista = lista.get(0);
        for (int num : lista) {
            if (num < minLista) {
                minLista = num;
            }
        }

        int maxLista = lista.get(0);
        for (int num : lista) {
            if (num > maxLista) {
                maxLista = num;
            }
        }

        out.println("Najmanji broj u unesenoj listi je: " + minLista);
        out.println("Najveci broj u unesenoj listi je: " + maxLista);


    } // end main
} // end class
