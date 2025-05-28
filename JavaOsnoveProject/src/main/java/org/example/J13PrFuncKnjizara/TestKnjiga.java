package J13PrFuncKnjizara;

import static java.lang.System.out;

public class TestKnjiga {
    public static void main(String[] args) {
        Knjiga knjiga1 = new Knjiga("Zlocin i kazna", "Dobrisa Cesaric", "100 eura");
        Knjiga knjiga2 = new Knjiga("Pale sam na svijetu", "Neki", "10 eura");

        out.println(knjiga1.DohvatiKnjigu());
        out.println(knjiga2.DohvatiKnjigu());

    }
}
