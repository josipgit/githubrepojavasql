package J15PrExtZapoManaProg;

import static java.lang.System.out;

public class TestOsobaMain {
    public static void main(String[] args) {
        Osoba osoba1 = new Osoba();
        out.println(osoba1.getOib());

        Osoba osoba2 = new Student();
        // out.println(osoba2.getMatBrojStudenta);  // ovo je error

        Student student1 = new Student();
        out.println(student1.getMatBrojStudenta());

        // Student student2 = new Osoba(); // ovo je error

    } // end main
} // end class
