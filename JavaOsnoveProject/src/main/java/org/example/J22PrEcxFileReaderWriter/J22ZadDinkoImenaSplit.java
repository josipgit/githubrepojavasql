import java.io.BufferedReader;
import java.io.FileReader;

public class J22ZadDinkoImenaSplit {
    public static void main(String[] args) {
        String file = "C:\\Users\\josip\\IntelliJProjects\\JavaTecaj\\src\\J22Pr\\datoteka.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linija;

            while ((linija = br.readLine()) != null) {
                String[] linijaSplit = linija.split(";");

                for (String s : linijaSplit) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}


//Kreiraj novu datoteku u kojoj se nalazi tekst:
//Pero;Marica;Ivica;Dodo;Ivana
//
//Korištenjem BufferedReadera učitaj podatke iz dateke u novu listu, kreiraj novo polje String[] koje će sadržavati imena.
//Ispiši imena iz polja
//Implementiraj try-catch