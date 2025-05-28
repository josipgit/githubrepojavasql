import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

import java.util.Locale;

public class J10Pr9ArrLRandNovci {
    public static void main(String[] args) {

        System.gc(); // garbage collection
        System.out.flush(); // clear output buffers and ensure immediate writing to the output stream


        int[] polje = new int[5];
        for (int i = 0; i < polje.length; i++) {
            polje[i] = (int) ((Math.random() * 5) + 1);
        }

        for (int broj : polje)
            out.println(broj);

        ArrayList<Integer> lista = new ArrayList<>();
        Scanner sc = new Scanner(in).useLocale(Locale.US);
        int ucitaniBroj = 0;
        do {
            out.print("Ucitaj broj (ucitaj -1 za prekid unosa): ");
            ucitaniBroj = sc.nextInt();
            lista.add(ucitaniBroj);
        }
        while (ucitaniBroj != -1);

        out.println("------------------------------------------");

        out.println(lista);

        out.println("------------------------------------------");
        for (Integer i : lista) {
            out.println(i + " ");
        }
        out.println("------------------------------------------");
        lista.remove(1); // makne element liste na indexu 1
        out.println("Nakon remove na mjestu index 1: " + lista);
        out.println("lista.size(): " + lista.size()); // koliko elemenata ima lista


        out.println("\n\n-----------------Zadatak 1 : novcanice--------------------");

        Scanner scanner = new Scanner(in).useLocale(Locale.US);
        out.print("Unesi iznos: ");
        double iznos = scanner.nextDouble();
        double[] vrijednosti = {500, 200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01};

        for (int i = 0; i < vrijednosti.length; i++) {
            int cijeli = (int) (iznos / vrijednosti[i]);
            double ostatak = iznos % vrijednosti[i];
            out.println("Novcanica/kovanica od " + vrijednosti[i] + " treba vratiti " + cijeli + " komada");
            if (ostatak <= 0) {
                for (int j = i; j < vrijednosti.length; j++) {
                    out.println("Novcanica/kovanica od " + vrijednosti[j] + " treba vratiti 0 komada");
                }
                break;
            }
            iznos = ostatak;
        }

        out.println("\n\n----------------------------------------------------------");

    } // end main
} // end class

