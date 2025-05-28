import java.util.Scanner;
import static java.lang.System.out;

public class J6Pr7ForForFaktorijelPoljeProstArms {
    public static void main(String[] args) {
        int x = 1;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 12; j++) {
                System.out.printf(x + "\t");
                x++;
            }
            System.out.printf("\n");
        }

        out.println("--------------------------------Faktorijele----------------------------------");

        var broj = 10; // Broj kojem racunamo faktorijelu
        long faktorijel = 1;
        for (int i = 1; i <= broj; i++) {
            faktorijel *= i;
        }
        System.out.println("Faktorijela od broja " + broj + " iznosi: " + faktorijel);

        out.println("------------------------------------Polje------------------------------------");

        int[] polje = {1, 2, 3, 4, 5};
        for (int s : polje)
            out.println(s);

        out.println("----------------------------------Prosti brojevi-----------------------------");

        boolean jeProst;
        int n = 50;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                continue; // ako je i=1, ne izvrsavaj ostatak for petlje nego idi na i=2
            }

            jeProst = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    jeProst = false;
                    break; // odnosi se samo na j for petlju
                }
            }
            if (jeProst) // je li prost?
                System.out.println(i); // Da, ispiši ga
        }

        out.println("--------------------------------Armstrong numbers----------------------------");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Unesi broj za Armstrong racunanje: ");
        int armstrongBroj = scanner.nextInt();

        int zbroj = 0;
        while (armstrongBroj != 0) {
            int znamenka = armstrongBroj % 10;  // vadenje zadnje znamenke iz broja armstrongBroj
            zbroj += Math.pow(znamenka, 3);
            armstrongBroj /= 10;
        }

        System.out.println("Suma kubova znamenki je: " + zbroj);
        scanner.close();


    } // end main
} // end class
