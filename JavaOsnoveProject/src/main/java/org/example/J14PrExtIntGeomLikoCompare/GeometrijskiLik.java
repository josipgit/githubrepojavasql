package J14PrExtIntGeomLikoCompare;

public abstract class GeometrijskiLik implements Comparable<GeometrijskiLik> {
    String naziv;

    public GeometrijskiLik(String naziv) {
        this.naziv = naziv;
    }

    public abstract double Povrsina();

    public abstract double Opseg();

    public int compareTo(GeometrijskiLik other) {
        //GeometrijskiLik k = (GeometrijskiLik) other;
        return Double.compare(this.Povrsina(), other.Povrsina());
        // return this.Povrsina().compareTo(k.Povrsina()); // ovo je error jer je Povrsina tipa double (primitivni tip)
    }

}
