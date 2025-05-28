import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class J4Pr6IfElseSwitchCaseDoWhile {
    public static void main(String[] args) {

        for (int i = 0; i <= 20; i++) {
            if (i % 2 == 0)
                out.println("Broj " + i + " je paran");
            else
                out.println("Broj " + i + " je neparan");
        }

        Scanner scanner = new Scanner(in).useLocale(Locale.US);
        out.println("Unesi spol: ");
        String spol = scanner.nextLine();
        if (spol.equals("m"))
            out.println("Korisnik je muska osoba");
        else if (spol.equals("z"))
            out.println("Korisnik je zenska osoba");
        else
            out.println("Korisnik je nepoznat");


        out.println("Unesi ocjenu: ");
        int ocjena = scanner.nextInt();
        switch (ocjena) {
            case 1:
                out.println("nedovoljan");
                break;
            case 2:
                out.println("dovoljan");
                break;
            case 3:
                out.println("dobar");
                break;
            case 4:
                out.println("vrlo dobar");
                break;
            case 5:
                out.println("odlican");
                break;
            default:
                out.println("nema ocjene takve");
                break;
        }

        out.println("------------------------------------------");

        int j = 0;
        while (j < 16) {
            out.println(j);
            j++;
        }

        out.println("------------------------------------------");

        do {
            out.println(j);
            j++;
        }
        while (j < 16);

        out.println("------------------------------------------");

        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        out.println("Suma prvih 100 brojeva: " + sum);

        out.println("------------------------------------------");

        out.print("Unesi broj X do kojeg ce se zbrajati: ");
        double X = scanner.nextDouble();
        double suma = 0;
        int index = 1;
        while (index <= X) {
            suma += index++;
        }
        out.println("Suma brojeva do broja " + X + " je: " + suma);

        out.println("------------------------------------------");

        int y = 999;
        for (int k = 1; k <= 10; k++) {
            out.println("Broj " + y + " " + k + " puta");
        }


    } // end main
} // end class
