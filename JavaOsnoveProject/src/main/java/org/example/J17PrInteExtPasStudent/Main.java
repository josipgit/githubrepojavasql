package J17PrInteExtPasStudent;

import static java.lang.System.out;

interface iGlasanje {
    void glasanje();
}

interface iSposobnost {
    void sposobnost();
}

class Pas implements iGlasanje,iSposobnost {
    public void glasanje() {
        out.println("vau vau");
    }

    public void sposobnost(){
        out.println("grize");
    }

}

public class Main {
    public static void main(String[] args) {
        Pas pas = new Pas();
        pas.glasanje();
        pas.sposobnost();
    }
}
