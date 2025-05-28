package J13PrFuncAutomobili;

import static java.lang.System.*;

public class TestVoznja {
    public static void main(String[] args) {

        Auto auto1 = new Auto("6 brzina", "mercedes");
        Auto auto2 = new Auto("5 brzina", "fiat");
        out.println(auto1.DohvatiAutomobil());
        out.println(auto2.DohvatiAutomobil());

        auto1.PaliAuto();
        auto2.PaliAuto();

    }
}