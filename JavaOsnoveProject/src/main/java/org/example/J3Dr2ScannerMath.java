import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class J3Dr2ScannerMath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in).useLocale(Locale.US);

        out.println("\n------Zadatak 1 - Zbroj dva broja------");
        out.print("Unesi broj a: ");
        double broj1 = scanner.nextDouble();
        out.println("a = " + broj1);
        out.print("Unesi broj b: ");
        double broj2 = scanner.nextDouble();
        out.println("b = " + broj2);
        out.println("Zbroj brojeva " + broj1 + " + " + broj2 + " = " + (broj1 + broj2));

        out.println("\n------Zadatak 2 - Ispis imena i prezimena i mjesta------");
        out.println("Josip Radic" + "\n\n" + "Okrug Gornji");

        out.println("\n------Zadatak 3 - Pitagorin poučak------");
        out.print("Unesi stranicu a: ");
        double a = scanner.nextDouble();
        out.println("a = " + a);
        out.print("Unesi stranicu b: ");
        double b = scanner.nextDouble();
        out.println("b = " + b);
        out.println("Hipotenuza c = " + Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));
    }
}
