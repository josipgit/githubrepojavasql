package J19PrExcTryCatchThrow;

import java.util.ArrayList;

import static java.lang.System.out;

public class KlasaError {
    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>();
        try {
            while(true)
            {
                lista.add(1);
            }
        } catch (OutOfMemoryError e) { //
            out.println(e);
        } finally {
            out.println("ovo je finally !!!!!!!!");
        }
    }
}