import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class J5Dr3ZbroZnamBrojDjeljivih {
    public static void main(String[] args) {

        out.println("\n------Zadatak 1 - Zbroj znamenki unesenog broja------");

        Scanner scanner = new Scanner(in).useLocale(Locale.US);

        out.print("Unesi broj: ");
        long uneseniBroj = scanner.nextLong();
        int sumaZnamenki = 0;

        while (uneseniBroj != 0) {
            sumaZnamenki += uneseniBroj % 10; // pribroji zadnju znamenku u sumu znamenki
            uneseniBroj /= 10;       //ukloni zadnju znamenku iz unesenog broja
        }

        out.println("Suma znamenki unesenog broja iznosi: " + sumaZnamenki);

        out.println("-----------------------------------------------------------------------------");

        out.println("Zadatak 2 verzija 1 - Zbroj brojeva od 1 do 1000 djeljivih sa 7 ");

        int sumaDjeljivihSa7 = 0;
        int i = 7;
        do {
            sumaDjeljivihSa7 += i;
            i += 7;
        }
        while (i <= 1000);

        out.println("Zbroj brojeva od 1 do 1000 djeljivih sa 7 je: " + sumaDjeljivihSa7);

        out.println("-----------------------------------------------------------------------------");

        out.println("Zadatak 2 verzija 2 - Zbroj brojeva od 1 do 1000 djeljivih sa 7 ");

        int sumaDjeljivihSa7_ver2 = 0;
        int i_ver2 = 1;
        do {
            if ((i_ver2 % 7) == 0)
                sumaDjeljivihSa7_ver2 += i_ver2;
            i_ver2++;
        }
        while (i_ver2 <= 1000);

        out.println("Zbroj brojeva od 1 do 1000 djeljivih sa 7 je: " + sumaDjeljivihSa7_ver2);

    } //end main
} // end class
