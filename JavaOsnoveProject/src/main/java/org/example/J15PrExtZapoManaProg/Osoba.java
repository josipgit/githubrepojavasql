package J15PrExtZapoManaProg;

public class Osoba {
    String ime, prezime;
    String oib;
    String getOib() {
        return "OIB osobe je: " + oib;
    }
}

class Student extends Osoba {
    int matBrojStudenta;

    int getMatBrojStudenta() {
        return matBrojStudenta;
    }
}

class Profesor extends Osoba {
}

class Apsolvent extends Student {
}
