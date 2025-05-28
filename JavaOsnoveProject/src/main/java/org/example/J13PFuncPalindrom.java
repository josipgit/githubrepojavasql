public class J13PFuncPalindrom {

    public static void main(String[] args) {
        ispisiPozdrav();
        String s = "Cao";
        ispisi(s);
        int i = 5;
        ispisi(i);
        double[] arr = {1, 5, 8, 9};

        System.out.println(zbroji(5, 8));
        System.out.println("Bez unosa: " + prosjek());
        System.out.println("Unos brojeva: " + prosjek(4, 5, 12, 22, 111));
        System.out.println("Unos preko polja: " + prosjek(arr));
        String palindrom = "ana voli Milovana";
        System.out.println(isPalindrome(palindrom));
    }

    public static boolean isPalindrome(String str) {
        // in next line call multiple methods on an object in a single line, using the dot (.) operator
        str = str.replaceAll("\\s+", "").toLowerCase(); // Remove spaces, convert to lowercase
        int left = 0, right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindrom(String word) {
        word = word.toLowerCase().replaceAll(" ", "");
        int index = word.length() - 1;
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(index--)) {
                return false;
            }
        }
        return true;
    }

    private static double prosjek(double... args) {
        if (args.length == 0) {
            return 0.0;
        }
        double p;
        double suma = 0.0;
        for (double arg : args) { // zbrajamo unesene brojeve
            suma += arg;
        }
        p = suma / args.length; // dijelimo vrijednost sume sa ukupnim brojem unesenih brojeva
        return p;
    }

    private static void ispisiPozdrav() {
        System.out.println("Bok bok!");
    }

    private static void ispisi(String s) {
        System.out.println(s);
    }

    private static void ispisi(int i) {
        System.out.println(i);
    }

    private static int zbroji(int x, int y) {
        return x + y;
    }

}