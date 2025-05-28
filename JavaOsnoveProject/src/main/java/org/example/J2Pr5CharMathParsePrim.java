public class J2Pr5CharMathParsePrim {
    public static void main(String[] args) {
//deklaracija
        byte a2;
//definicija
        short x = 10000;
        long myWidth;
//inicijalizacija
        myWidth = 5252;
        float decimalni = 3.12323F;
        double decimalni2 = 3.12323;

        char znak1 = '\u0041'; // slovo A
        char znak2 = '\u004A'; // slovo J
        char znak3 = '\u004F'; // slovo O
        char znak4 = '\u0053'; // slovo S
        char znak5 = '\u0049'; // slovo I
        char znak6 = '\u0050'; // slovo P

        boolean istiZnak = znak1 == znak2; // jel znak1 jednako znak2

        System.out.println(x + myWidth);
        System.out.println(x - myWidth);
        System.out.println(x * myWidth);
        System.out.println(x / myWidth);
        System.out.println(x % myWidth);
        System.out.println((float) x / myWidth);
        System.out.println("----------------------------------------");
        System.out.println((short) decimalni / (int) decimalni2);
        System.out.println(myWidth / (int) decimalni2);
        System.out.println((float) myWidth % (int) decimalni2);
        //System.out.printf((float) myWidth % (int) decimalni2);
        System.out.println("----------------------------------------");
        System.out.printf("%d\n", x / myWidth); // ovo je 1
        //System.out.printf("%f", x / myWidth); //ovo je error jer nisi casta u float koji ocekuje, a rezultat je int
        System.out.printf("%f\n", (float) x / myWidth);
        System.out.println("----------------------------------------");
        System.out.println(znak1);
        System.out.printf("%c%c%c%c%c\n\n", znak2, znak3, znak4, znak5, znak6); // JOSIP
        System.out.println(istiZnak);
        System.out.println("----------------------------------------");
        int baze = 5;
        int algoritmi = 3;
        int praktikum = 3;
        int osnove = 3;
        float prosjek1 = (float) (baze + algoritmi + praktikum + osnove) / 4;
        double prosjek2 = (float) (baze + algoritmi + praktikum + osnove) / 4;
        //float prosjek3 = (double) (baze+algoritmi+praktikum+osnove)/4; // ovo je error
        System.out.println("Prosjek pretvaranja tipova: " + prosjek1);
        System.out.println("Prosjek pretvaranja tipova: " + prosjek2);
        System.out.println("Matematicki zaokruzivanje: " + Math.round(prosjek2));
        System.out.println("Math PI = " + Math.PI);
        System.out.println("Math E = " + Math.E);
        System.out.println("----------------------------------------");

        System.out.println("\n\tZadatak 1");
        int baza = 10;
        int potencija = 3;
        System.out.println("Deset na trecu potenciju = " + (int) Math.pow(baza, potencija));

        System.out.println("\nZadatak 2");
        System.out.println("Zaokruzen PI na cijeli broj = " + Math.round(Math.PI));

        System.out.println("\nZadatak 3");
        System.out.println("Korijen od 400 = " + Math.round(Math.sqrt(400)));

        System.out.println("\nZadatak 4");
        System.out.printf("Zaokruzen E na dvije decimale = %.2f%n", Math.E);
        System.out.printf("Zaokruzen E na dvije decimale = %.2f%n", (float) Math.E);
    }
}
