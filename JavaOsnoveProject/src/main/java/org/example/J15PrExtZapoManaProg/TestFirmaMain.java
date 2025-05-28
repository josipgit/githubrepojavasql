package J15PrExtZapoManaProg;

import static java.lang.System.out;

public class TestFirmaMain {
    public static void main(String[] args) {
        Zaposlenik zaposlenik1 = new Zaposlenik("Ante", 100);
        zaposlenik1.oZaposleniku();

        Manager manager1 = new Manager("Jozo", 1000);
        manager1.Radi();

        Programer programer1 = new Programer("Frane", 10);
        programer1.Radi();

        Zaposlenik zaposlenik2 = new Manager("Stipe", 1500);  // zaposlenik1 is instance of Manager
        // zaposlenik2.Radi();  // error, Stipe upravlja timom.

        Zaposlenik zaposlenik3 = new Programer("Vlado", 9999);
        // zaposlenik3.Radi();  // error je Vlado pise programski kod.

        Manager manager2 = new Manager("Jure", 14);
        manager2.oZaposleniku();

        // Manager manager3 = new Programer(); // error

        if (zaposlenik1 instanceof Manager) {
            Manager m1 = (Manager) zaposlenik1;
            // (Manager) zaposlenik1; ti daje pristup metodama i atributima specifičnim za Manager.
            // Bez castanja, možeš koristiti samo ono što postoji u klasi Zaposlenik
            m1.Radi();
        } else {
            out.println("\n\n---------------prvi zaposlenik nije instanceof Manager-------------");
        }

        if (zaposlenik2 instanceof Manager)
        {
            Manager m2 = (Manager) zaposlenik2;
            m2.Radi(); // sad nije error Stipe upravlja timom.
        }
        else{
            out.println("\n\n---------drugi zaposlenik nije instanceof Manager----------");
        }

        if (zaposlenik3 instanceof Programer)
        {
            Programer p2 = (Programer) zaposlenik3;
            p2.Radi(); // sad nije error Vlado upravlja timom.
        }
        else{
            out.println("\n\n---------treci zaposlenik nije instanceof Programer----------");
        }

    }
}
