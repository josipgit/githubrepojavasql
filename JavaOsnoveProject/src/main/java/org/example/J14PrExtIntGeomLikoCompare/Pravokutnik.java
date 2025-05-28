package J14PrExtIntGeomLikoCompare;

public class Pravokutnik extends GeometrijskiLik {

    int a;
    int b;

    public Pravokutnik(String naziv, int a, int b) {
        super(naziv);
        this.a = a;
        this.b = b;
    }

    public double Povrsina() {
        return a * b;
    }

    @Override
    public double Opseg() {
        return OpsegPravokutnika(); // Return the correct perimeter
    }

    public int OpsegPravokutnika() {
        return a + a + b + b;
    }

    public double getPovrsinaPravokutnika() {
        return Povrsina();
    }

    @Override
    public String toString() {
        return "Pravokutnik {" + " " + naziv + " " +
                "a=" + a + " " +
                ", b=" + b + " " +
                "Povrsina pravokutnika je " + Povrsina() + " " +
                "Opseg pravokutnika je " + Opseg() + " " +
                '}';
    }
}



