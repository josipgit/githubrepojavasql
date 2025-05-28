import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Locale;

public class J8Pr8StringPrijestupnaIspisPoljaSort {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(in).useLocale(Locale.US);

        out.print("Upisi prvi broj: ");
        int broj1 = scanner.nextInt();

        out.print("Upisi drugi broj: ");
        int broj2 = scanner.nextInt();

        int zbroj = broj1 + broj2;
        int umnozak = broj1 * broj2;

        out.println("Zbroj: " + zbroj);
        out.println("Umnozak: " + umnozak);

        out.println("------------------------------------------");

        out.print("Upisi godinu: ");
        int godina = scanner.nextInt();

        // Provjeri da li je prijestupna
        boolean daLiJePrijestupna = (godina % 4 == 0 && godina % 100 != 0) || (godina % 400 == 0);

        // Display the result
        if (daLiJePrijestupna) {
            out.println(godina + " je prijestupna godina.");
        } else {
            out.println(godina + " nije prijestupna godina.");
        }

        scanner.close();

        out.println("------------------------------------------");

        //int[] polje = new int[5];
        int[] polje = {11, 232, 4234323, 4, 35, 6, 11, 8, 9};
        out.println("Broj clanova polja je: " + polje.length + "\n");

        for (int i : polje) {
            out.print(i + "\t"); // ovo ispisuje sve clanove polja direktno bez da setamo po polju indeksom
        }

        out.println("\n------------------------------------------");

        for (int i = 0; i < polje.length; i++) { // ispis setanjem po polju indeksom
            out.println("Element polja " + i + " je " + polje[i]);
        }

        Arrays.sort(polje); // sortiranje polja po velicini
        for (int i : polje) {
            out.print(i + "\t"); // ovo ispisuje sve clanove polja direktno bez da setamo po polju indeksom
        }

        //Arrays.fill(polje, 8); //inicijaliziramo sve clanove polja sa 8

        out.println("\n------------------------------------------");
        System.out.println(Arrays.toString(polje));

        out.println("------------------------------------------");

        for (int i = polje.length - 1; i >= 0; i--) { // ispis setanjem po polju indeksom unazad
            out.println("Element polja " + i + " je " + polje[i]);
        }
        out.println("------------------------------------------");

        String string = "niz znakova";
        out.println(string);
        out.println(string.length()); // ispisuje duljinu stringa
        out.println(string.charAt(7)); //ispise slovo stringa na sedmom mjestu
        out.println(string.endsWith("a")); // true jer string zavrsava na a
        out.println(string.indexOf("a")); // 6 jer je a na sestom mjestu u stringu

        String intNumberAsString = "12345";
        String doubleNumberAsString = "123.456";
        int intNumber = Integer.parseInt(intNumberAsString);
        double doubleNumber = Double.parseDouble(doubleNumberAsString);
        System.out.println("The parsed number is: " + intNumber + "  " + doubleNumber);

    } // end main
} // end class
