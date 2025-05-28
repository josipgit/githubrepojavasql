package J21PrExcBuffStreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class J21PrExcBuffStreReader {
    // mora ic throw IOException na main zbog osiguranja za input stream ulaz, inace je error
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(System.in));
        System.out.println("Enter the data");
        String read = reader.readLine();
        System.out.println("You entered: " + read);
    }
}



