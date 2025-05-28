package J18DrExtAbstractKlubTrenerIgraci;

class Club {
    private static int idCounter = 1; // static znaci da pripada klasi, a ne instanci objekta, ova linija se samo jednom izvrsava
    private int id;
    private String name;
    private String city;
    private Coach coach;

    public Club(String name, String city, Coach coach) {
        this.id = idCounter++; // na svako kreiranje objekta uvecat ce se za 1
        this.name = name;
        this.city = city;
        this.coach = coach;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + city + ") - Coach: " + coach;
    }
}
