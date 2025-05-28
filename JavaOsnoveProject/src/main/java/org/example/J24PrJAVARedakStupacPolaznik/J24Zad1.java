package J24PrJAVARedakStupacPolaznik;

import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class J24Zad1 {
    public static void main(String[] args) {
        out.println("-----------------zadatak 1------------------");
        out.println("   J     a    v     v   a");
        out.println("   J    a a    v   v   a a");
        out.println("J  J   aaaaa    V V   aaaaa");
        out.println(" JJ   a     a    V   a     a");

        out.println("-----------------zadatak 2------------------");
        Scanner scanner = new Scanner(in).useLocale(Locale.US);
        out.print("Unesite broj redaka: ");
        int brojRedaka = scanner.nextInt();
        out.print("Unesite broj stupaca: ");
        int brojStupaca = scanner.nextInt();
        scanner.close();

        for (int i = 1; i <= brojRedaka; i++) {
            for (int j = 1; j <= brojStupaca; j++) {
                if (i % 2 != 0) { // ako je i neparan
                    out.print("*");
                } else { // ako je i paran
                    out.print("o");
                }
            }
            out.println(); // aj u novu liniju
        }

        out.println("-----------------zadatak 3------------------");
        // Kreiranje liste polaznika
        List<J24PrJAVARedakStupacPolaznik.Polaznik> polaznici = new ArrayList<>();
        polaznici.add(new Polaznik("Ana", "Zanic", 25));
        polaznici.add(new Polaznik("Marko", "Ljubic", 20));
        polaznici.add(new Polaznik("Ivana", "Kekez", 22));
        polaznici.add(new Polaznik("Zoran", "Antic", 36));

        //Collections.sort(polaznici);
        //Collections.sort(polaznici, Comparator.comparingInt(Polaznik::getDob));
        //Collections.sort(polaznici, Comparator.comparing(Polaznik::getIme));
        //Collections.sort(polaznici, Comparator.comparing(Polaznik::getPrezime));
        polaznici.sort((p1, p2) -> p1.getPrezime().compareTo(p2.getPrezime()));
        // Ispis sortirane liste
        System.out.println("Sortirani polaznici: ");
        for (Polaznik p : polaznici) {
            System.out.println(p);
        }
        out.println("----------------------------------------------------------");
        polaznici.forEach(polaznik -> out.println(polaznik));

    } // end main
} // end class