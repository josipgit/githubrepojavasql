package J17PrIntKnjiznicaCompare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

interface iPosudbaKnjige {
    void PosudiKnjigu();

    void VratiKnjigu();
}

public class Knjiga implements Comparable<Object>, iPosudbaKnjige {
    String naslov;
    String dostupnost;

    public Knjiga(String naslov, String dostupnost) {
        this.naslov = naslov;
        this.dostupnost = dostupnost;
    }

    public void PosudiKnjigu() {
        this.dostupnost = "je posudena";
        out.println("Knjiga " + naslov + " " + this.dostupnost);
    }

    public void VratiKnjigu() {
        this.dostupnost = "je vracena";
        out.println("Knjiga " + naslov + " " + this.dostupnost);
    }

    public int compareTo(Object obj) {
        Knjiga k = (Knjiga) obj; // Cast to Knjiga
        return this.naslov.compareTo(k.naslov); // Compare strings correctly
    }

    public String toString() {
        return "Knjiga{" +
                "naslov='" + naslov + '\'' +
                ", dostupnost='" + dostupnost + '\'' +
                '}';
    }
}

class Main {
    public static void main(String[] args) {
        List<Knjiga> knjige = new ArrayList<>();
        knjige.add(new Knjiga("Alica u zemlji cudesa", "je dostupna"));
        knjige.add(new Knjiga("Zlocin i kazna", "je dostupna"));
        knjige.add(new Knjiga("Poarot", "je dostupna"));
        knjige.add(new Knjiga("Ilijada", "je dostupna"));
        knjige.add(new Knjiga("Pale sam na svitu", "je dostupna"));

        Collections.sort(knjige);
        // Printing sorted students
        for (Knjiga s : knjige) {
            System.out.println(s);
        }
        out.println("----------------------------------------------------------");
        out.println(knjige.get(0));
        out.println("----------------------------------------------------------");
        knjige.get(0).PosudiKnjigu();
        out.println("----------------------------------------------------------");
        out.println(knjige.get(0));
        out.println("----------------------------------------------------------");
        knjige.get(0).VratiKnjigu();
        out.println("----------------------------------------------------------");
        out.println(knjige.get(0));


    }
}