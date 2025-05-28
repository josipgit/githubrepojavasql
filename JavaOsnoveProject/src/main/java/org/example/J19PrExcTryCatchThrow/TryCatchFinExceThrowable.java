import java.util.Scanner;

import static java.lang.System.out;

public class TryCatchFinExceThrowable {
    public static void main(String[] args) {
        System.gc(); // garbage collection
        out.flush(); // clear output buffers and ensure immediate writing to the output stream
        Scanner scanner = new Scanner(System.in);
        int broj1 = 0;
        int broj2 = 0;

        while (true) {
            try {
                out.print("Unesite prvi broj: ");
                broj1 = Integer.parseInt(scanner.nextLine()); // Read first number

                out.print("Unesite drugi broj: ");
                broj2 = Integer.parseInt(scanner.nextLine()); // Read second number

                break; // If input is valid, exit the loop
            } catch (Throwable e) { // NumberFormatException e
                // Catch invalid number format and prompt user again
                out.println("Unijeli ste pogresan broj, unesite broj ponovo !" + "\n" + e + "\n");
            } finally {
                out.println("Dogada se svakako bez obzira na try catch !!!!");
            }
        }

        // Once valid numbers are entered, add them
        int result = broj1 + broj2;
        out.println("Rezultat: " + result);

        scanner.close(); // Close the scanner

        out.println("\n\n----------------------------------------------------------");

        int brojX = 0;
        String unos = "234";
        int[] polje = {1, 2, 3};

        try {
            // 1. greška
            //brojX = new Integer(unos);
            // 2. greška
            //brojX = polje[5];
            // 3. greška
            brojX = brojX / 1;
        } catch (NumberFormatException e) {
            System.out.println("Greška pri konverziji znakovnog niza u broj");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Greška pri pristupu elementu iz polja");
        } catch (Exception e) {
            System.out.println("Sve ostale greške.");
        }

        System.out.println("Završetak programa.");

        out.println("\n\n----------------------------------------------------------");

        try {
            double rezultat = izracunajKorijen(-1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        out.println("\n\n----------------------------------------------------------");

        System.gc(); // garbage collection
        out.flush(); // clear output buffers and ensure immediate writing to the output stream
    } // end main

    public static double izracunajKorijen (double broj) throws Exception{
        if (broj < 0){
            throw new Exception("Broj ne smije biti manji od jedan !!!!!!!!");
        }
        return Math.sqrt(broj);
    }

} // end class