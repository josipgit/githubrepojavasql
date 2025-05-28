package J13PrProizvodRandArrL;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class TestProizvod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // ArrayList<Proizvod> proizvodi = new ArrayList<Proizvod>();
        var proizvodi = new ArrayList<Proizvod>();
        var proizvodi2 = new ArrayList<Proizvod>();
        String[] productNames = {"salam", "sir", "jogurt", "kruv", "mliko", "break", "pasteta", "riba", "kruv", "cola", "karota", "kunpiri"};
        Random random = new Random();
        while (true) {  // vrti dok ne dode break
            String randomName = productNames[random.nextInt(productNames.length)]; // Random product name
            int randomPrice = random.nextInt(100) + 1; // Random price between 1 and 100
            int randomValue = random.nextInt(100) + 1; // Random value between 1 and 100

            Proizvod p1 = new Proizvod(randomName, randomPrice, randomValue);
            proizvodi.add(p1);
            //out.println(p1.IspisiProizvod());

            if (randomName.equals("break")) break;
        }

        out.println("\n--------------------Ispis svih proizvoda u PRVOJ Array listi proizvodi:---------");
        for (Proizvod p : proizvodi) {
            out.println(p.IspisiProizvod() + " ");
        }

        double randomDouble = random.nextDouble();
        float randomFloat = random.nextFloat();
        long randomLong = random.nextLong();
        int randomInt = random.nextInt();

        out.println("\n---------------------Ispis randoma:-----------------------");
        out.println(randomDouble + "\n" +
                randomFloat + "\n" +
                randomLong + "\n" +
                randomLong + "\n" +
                randomInt);

        while (true) {  // Loop until user enters "break"
            out.print("Unesi naziv proizvoda ('break' je za prekid unosa): ");
            String userInput = scanner.nextLine().trim().toLowerCase(); // upisi proizvod

            if (userInput.equals("break")) break; // Stop if user enters "break"

            boolean existsInList = false;
            for (String product : productNames) {
                if (userInput.equals(product)) {
                    existsInList = true;
                    break;
                }
            }

            if (!existsInList) {
                out.println("Proizvod nije u listi. Pokusaj ponovo.");
                continue;
            }

            int randomPrice = random.nextInt(100) + 1; // Random price between 1 and 100
            int randomValue = random.nextInt(100) + 1; // Random value between 1 and 100

            Proizvod p1 = new Proizvod(userInput, randomPrice, randomValue);
            proizvodi2.add(p1);
        }

        out.println("\n--------------------Ispis svih proizvoda u DRUGOJ Array listi proizvodi2:---------");
        for (Proizvod p : proizvodi2) {
            out.println(p.IspisiProizvod() + " ");
        }

    } // end main
} // end class
