package J14PrExtIntGeomLikoCompare;

public class Trokut extends GeometrijskiLik {

    int a;
    int b;
    int c;

    public Trokut(String naziv, int a, int b, int c) {
        super(naziv); // Pass naziv to the superclass constructor
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double Povrsina() {
        double s = 0.5*(a+b+c);
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    public double Opseg() {
        return a + b + c;
    }

    public double getPovrsinaTrokuta() {
        return Povrsina();
    }

    @Override
    public String toString() {
        return "Trokut {" + " " + naziv + " " +
                "a=" + a + " " +
                ", b=" + b + " " +
                ", c=" + c + " " +
                " Povrsina trokuta je " + Povrsina() + " " +
                " Opseg trokuta je " + Opseg() + " " +  // FIXED
                "}";
    }
}
