package J13PrFuncUcenik;

import static java.lang.System.out;

public class TestUcenik {
    public static void main(String[] args) {

        J13PrFuncUcenik.Ucenik ucenik1 = new J13PrFuncUcenik.Ucenik("Ante", 20, 3.0, 4.0, 4.3, 4.5);
        J13PrFuncUcenik.Ucenik ucenik2 = new J13PrFuncUcenik.Ucenik("Ivan", 31, 5.8, 4.0, 4.6);

        out.println(ucenik1.IzracunajProsjekOcjena());
        out.println(ucenik1.IspisiUcenika());

        out.println(ucenik2.IzracunajProsjekOcjena());
        out.println(ucenik2.IspisiUcenika());

    }
}
