package J14PrExtIntGeomLikoCompare;

public class Krug extends GeometrijskiLik {
    double radius;

    public Krug(String naziv, int radius) {
        super(naziv);
        this.radius = radius;
    }

    public double Povrsina() {
        return radius * radius * Math.PI;
    }

    public double Opseg() {
        return 2 * radius * Math.PI;
    }

    public double getPovrsinaKruga() {
        return Povrsina();
    }

    @Override
    public String toString() {
        return "Krug {" + " " + naziv + " " +
                "radius=" + radius + " " +
                "Povrsina kruga je " + Povrsina() + " " +
                "Opseg kruga je " + Opseg() + " " +
                '}';
    }
}

