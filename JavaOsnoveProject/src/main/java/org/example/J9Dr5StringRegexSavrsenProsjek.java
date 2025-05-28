import java.util.Scanner;

import static java.lang.System.*;

import java.util.Locale;

public class J9Dr5StringRegexSavrsenProsjek {

    public static void main(String[] args) {

        System.gc(); // garbage collection
        System.out.flush(); // clear output buffers and ensure immediate writing to the output stream

        out.println("\n\n-----------------Zadatak 1: String klasa--------------------");

        String string = "niz znakova";
        String string2 = "NIZ ZNAKOVA";
        String stringEmpty = "";

        out.println(string);
        out.println(string.length()); // ispisuje duljinu stringa
        out.println(string.charAt(7)); //ispise slovo stringa na sedmom mjestu
        out.println(string.endsWith("kova")); // true jer string zavrsava na a
        out.println(string.indexOf("a")); // ispisuje 6 jer je a na sestom mjestu u stringu

        String numberStr = "12345";
        int number = Integer.parseInt(numberStr); // pretvara uneseni string u broj
        out.println("The parsed number is: " + number);

        out.println("\n------------------------------------------");

        int result = string.codePointAt(0); // vraca unicode karaktera na nultom mjestu
        int result2 = string.codePointBefore(1); // vraca unicode na mjestu prije 1 a to je nula, dakle isto ko i povise
        out.println(result);
        out.println(result2);

        out.println("\n------------------------------------------");

        System.out.println(string.compareTo(string2)); // 32
        System.out.println(string2.compareTo(string)); // -32
        System.out.println(string.compareToIgnoreCase(string2)); // returns 0
        System.out.println(string.equals(string2)); // false
        System.out.println(stringEmpty.isEmpty()); // bool da li je prazan string

        out.println("-----------------regex--------------------");

        String regex = "cat|dog|fish";
        System.out.println("cat".matches(regex));
        System.out.println("dog".matches(regex));
        System.out.println("catfish".matches(regex));
        System.out.println("doggy bag".matches(regex));

        out.println("-----------------toUpperCase--------------------");

        String txt = "Hello World";
        System.out.println(txt.toUpperCase());
        System.out.println(txt.toLowerCase());

        out.println("-----------------substring--------------------");

        String myStr = "Hello, World!";
        System.out.println(myStr.substring(7, 12));


        out.println("\n\n-----------------Zadatak 2: Savrsen broj--------------------");

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Enter a number to check if it is perfect: ");

        try {
            long num = scanner.nextLong();

            if (isPerfect(num)) {
                System.out.println(num + " is a perfect number!");
            } else {
                System.out.println(num + " is NOT a perfect number.");
            }

            System.out.print("Enter a limit to find perfect numbers: ");
            long limit = scanner.nextLong();
            findPerfectNumbers(limit);
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.nextLine();
        }


        out.println("\n\n-----------------Zadatak 3: prosjek ocjena--------------------");

        int numGrades = -1;

        while (numGrades <= 0) {
            try {
                System.out.print("Enter the number of grades: ");
                numGrades = scanner.nextInt();
                if (numGrades <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number for the number of grades.");
                scanner.nextLine(); // Clear the invalid input and read from next line
            }
        }

        double[] grades = new double[numGrades];
        double sum = 0; // suma svih unesenih ocjena

        // upisi sve ocjene
        for (int i = 0; i < numGrades; i++) {
            while (true) {
                try {
                    System.out.print("Enter grade " + (i + 1) + ": ");
                    grades[i] = scanner.nextDouble();
                    sum += grades[i];
                    break; // break while loop if input is valid
                } catch (Exception e) {
                    System.out.println("Invalid grade! Please enter a valid number.");
                    scanner.next(); // idi dalje na izvrsavanje koda
                }
            }
        }

        // izracunaj prosjek ocjena
        double average = sum / numGrades;
        System.out.println("The average grade is: " + average);

        System.gc(); // garbage collection
        out.flush(); // clear output buffers and ensure immediate writing to the output stream


    } // end main


    public static boolean isPerfect(long num) { // ispisuje da li je neki broj savrsen
        long sum = 1;

        for (long i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }

    public static void findPerfectNumbers(long limit) { // pronadi savrsene brojeve do broja limit
        System.out.println("Perfect numbers up to " + limit + ":");
        for (long i = 2; i <= limit; i++) {
            if (isPerfect(i)) {
                System.out.println(i);
            }
        }
    }

} // end class
