import java.util.Scanner;

class J20PrExc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Unesite broj za izračunavanje faktorijela: ");

                // Check if input is an integer
                if (!scanner.hasNextInt()) { // ako uneseni broj nije integer
                    // iduca linija je obavezna da ukloni uneseni invalidni znak
                    scanner.next(); // ukloni invalidni input iz buffera da petlja ne bi beskonacno vrtila krivi input
                    throw new IllegalArgumentException("Unijeli ste pogrešan znak, unesite ponovo !!!!!!");
                }

                int broj = scanner.nextInt();

                // Check if the number is negative
                if (broj < 0) {
                    throw new IllegalArgumentException("Unijeli ste negativan broj, molimo unesite ponovo !!!!!!");
                }

                long faktorijel = 1;
                for (int i = 1; i <= broj; i++) {
                    faktorijel *= i;
                }

                System.out.println("Faktorijel od broja " + broj + " iznosi: " + faktorijel);
                break; // Exit loop when input is valid
            } catch (Exception e) { // ovde moze i Throwable e
                System.out.println("Izuzetak: " + e.getMessage());
                //iduca linija obavezno triba da ocisti buffer potpuno od svega
                scanner.nextLine(); // ocisti buffer od svega (od enter-a ili space-a ili stagod je ostalo u bufferu)
            }
        }

        scanner.close(); // Close scanner after successful input
    }
}