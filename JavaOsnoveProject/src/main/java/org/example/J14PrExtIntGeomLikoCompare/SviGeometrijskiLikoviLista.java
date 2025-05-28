
package J14PrExtIntGeomLikoCompare;

import java.util.ArrayList;
import java.util.List;

public class SviGeometrijskiLikoviLista {
    // List to store any geometric objects
    private List<Object> likovi;

    // Constructor that accepts multiple objects
    public SviGeometrijskiLikoviLista(Object... objects) {
        this.likovi = new ArrayList<>();
        for (Object obj : objects) {
            likovi.add(obj);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object lik : likovi) {
            sb.append(lik.toString()); // Append each object's string representation
            sb.append(System.lineSeparator());
        }
        return sb.toString(); // Return the built string
    }

}