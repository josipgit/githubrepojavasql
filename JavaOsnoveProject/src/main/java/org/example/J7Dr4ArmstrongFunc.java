import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Locale;

public class J7Dr4ArmstrongFunc {
    public static void main(String[] args) {
        out.print("Unesi broj do kojeg ispisujemo sve Armstrongove brojeve: ");

        Scanner scanner = new Scanner(in).useLocale(Locale.US);
        long uneseniBroj = scanner.nextLong();
        scanner.close();
        out.println("Armstrongovi brojevi od 100 do " + uneseniBroj + " su: ");

        for (long num = 100; num < uneseniBroj; num++) {
            if (isArmstrong(num)) {
                out.println(num);
            }
        }
    } // end main

    public static boolean isArmstrong(long num) {
        long proslijedeniBroj = num;
        long sum = 0;
        int digits = String.valueOf(num).length(); // broj znamenki broja num proslijedenog gore u for petlji

        while (num > 0) {
            long digit = num % 10; // vadenje zadnje znamenke iz broja num
            sum += Math.pow(digit, digits); // znamenka^digits
            num /= 10;
        }

        return sum == proslijedeniBroj; // vrati true ako je broj Armstrongov

    }
} // end class
