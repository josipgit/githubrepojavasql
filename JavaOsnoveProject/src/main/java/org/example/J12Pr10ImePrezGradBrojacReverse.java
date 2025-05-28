import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Set;
import java.util.HashSet;

public class J12Pr10ImePrezGradBrojacReverse {
    public static void main(String[] args) {
        String fakultet = "Fakultet organizacije i informatike Varazdin";
        String[] rijeciFakultet = fakultet.split(" ");
        for (String s : rijeciFakultet) {
            out.println(s);
        }
        String recenica = "   Danas je bio hladan dan   ";
        out.println(recenica);
        out.println("Broj znakova nakon unosa: " + recenica.length());
        recenica = recenica.trim(); // uklanja prazne razmake na pocetku i na kraju
        out.println(recenica);
        out.println("Broj znakova nakon skracivanja: " + recenica.length());


        out.println("\n\n----------------Zadatak 1 ime prezime grad--------------");

        Scanner scanner = new Scanner(in).useLocale(Locale.US);

        String[] imePrezimeArray;
        while (true) { // beskonacna petlja dok ne dode break
            out.print("Unesite ime i prezime: ");
            String imePrezime = scanner.nextLine().trim(); // trim ukloni space-ove na sa pocetka i kraja stringa

            // sada odvoji svaku rijec zasebno po space-u i spremi u polje tipa String imePrezimeArray
            imePrezimeArray = imePrezime.split("\\s+"); //

            if (imePrezimeArray[0].length() > 7) {
                out.println("Ime sadrzi vise od 7 znakova! Pokusajte ponovo.");
                continue;
            }
            break;
        }

        System.out.println("Upisali ste ime: " + imePrezimeArray[0]);
        System.out.println("Upisali ste prezime: " + imePrezimeArray[1]);

        System.out.print("Unesite ime grada: ");
        String grad = scanner.nextLine().toLowerCase(); // Convert to lowercase

        System.out.print("Samoglasnici u stringu: \n");

        // Set is an interface in Java that represents a collection of elements where no duplicates are allowed.
        // HashSet is a class that implements the Set interface.
        // uniqueVowels is the variable name of a Set that stores unique characters, specifically the vowels encountered in a string.
        Set<Character> uniqueVowels = new HashSet<>();

        // Check if c is one of the characters in string "aeiou"
        // indexOf(c) returns the index of the character in the string
        // which will be 0, 1, 2, 3, or 4 for 'a', 'e', 'i', 'o', 'u'
        // If c is not a vowel, indexOf(c) returns -1.
        // If c has not been added to the set yet, add(c) returns true, and the vowel is printed
        // If c has already been added, add(c) returns false, and it does not print the vowel again.
        for (char c : grad.toCharArray()) { // pretvara string u polje tipa char
            if (("aeiou".indexOf(c) != -1) && uniqueVowels.add(c)) { // provjerava ima li samoglasnik i ponavlja li se taj samoglasnik
                System.out.println(grad + " ima samoglasnik " + c);
            }
        }


        out.println("\n\n-----------Zadatak 2 brojac slova brojeva znakova u unesenom stringu----------");

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        int letterCount = 0;
        int numberCount = 0;
        int ostalo = 0;

        // Iterate through each character in the string
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Check if the character is a letter
            if (Character.isLetter(ch)) {
                letterCount++;
            }
            // Check if the character is a digit
            else if (Character.isDigit(ch)) {
                numberCount++;
            }
            // else if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
            else if (Character.isWhitespace(ch)) {continue;}  // continue je ovde visak
            else {ostalo++;}
        }

        // Output the results
        System.out.println("Number of letters: " + letterCount);
        System.out.println("Number of numbers: " + numberCount);
        System.out.println("Number of ostalo: " + ostalo);


        out.println("\n\n------------Zadatak 3 ispis stringa unatrag - reverse string----------");

        String str = scanner.nextLine();  // Read the input string

        // creates a new StringBuilder object and initializes it with the string str
        // calls the reverse() method of StringBuilder
        // toString() converts the StringBuilder back to a String
        String reversed = new StringBuilder(str).reverse().toString();

        System.out.println("Reversed String: " + reversed);

        scanner.close();


        out.println("\n\n------------Zadatak 4 obrni redoslijed rijeci u recenici----------");
        String sentence = "prva druga treca cetvrta rijec";
        String[] words = sentence.split("\\s+");  // podijeli po spaceu
        StringBuilder reversedSentence = new StringBuilder();

        // Reverse word order
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]);
            if (i > 0) {
                reversedSentence.append(" ");
            }
        }
        System.out.println("Reversed word order: " + reversedSentence);

    } // end main
} // end class
