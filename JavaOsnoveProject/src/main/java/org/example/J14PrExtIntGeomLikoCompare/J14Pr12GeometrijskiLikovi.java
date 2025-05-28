package J14PrExtIntGeomLikoCompare;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.out;

public class J14Pr12GeometrijskiLikovi {

    public static void main(String[] args) {

        Trokut trokut = new Trokut("trokutistan", 7, 5, 4);
        Krug krug = new Krug("krugistan", 5);
        Pravokutnik pravokutnik = new Pravokutnik("pravokutistan", 3, 4);
        Trokut trokut2 = new Trokut("trokut2", 15, 9, 7);
        Krug krug2 = new Krug("krug2", 9);
        Pravokutnik pravokutnik2 = new Pravokutnik("pravokutnik2", 99, 123);

        /*double povKruga = krug.Povrsina();
        double povTrokuta = trokut.Povrsina();
        double povPravokutnika = pravokutnik.Povrsina();*/

        out.println(trokut);
        out.println(krug);
        out.println(pravokutnik);

        ArrayList<Double> povrsine = new ArrayList<>();
        povrsine.add(trokut.getPovrsinaTrokuta());
        povrsine.add(krug.getPovrsinaKruga());
        povrsine.add(pravokutnik.getPovrsinaPravokutnika());

        Collections.sort(povrsine);
        out.println(povrsine);

        out.println("\n--------------GeometrijskiLik lista -------------------------------------");

        ArrayList<GeometrijskiLik> geometrijskiLikovi = new ArrayList<>();
        geometrijskiLikovi.add(trokut);
        geometrijskiLikovi.add(krug);
        geometrijskiLikovi.add(pravokutnik);
        geometrijskiLikovi.add(trokut2);
        geometrijskiLikovi.add(krug2);
        geometrijskiLikovi.add(pravokutnik2);

        Collections.sort(geometrijskiLikovi);

        out.println("Sortirani po povrsini: ");
        for (GeometrijskiLik lik : geometrijskiLikovi) {
            out.println(lik);
        }

        out.println("\n----------------SviGeometrijskiLikoviLista lista -----------------------------------");

        SviGeometrijskiLikoviLista listaLikovi = new SviGeometrijskiLikoviLista(geometrijskiLikovi);
        out.println(listaLikovi);

    } // end main
} // end class
