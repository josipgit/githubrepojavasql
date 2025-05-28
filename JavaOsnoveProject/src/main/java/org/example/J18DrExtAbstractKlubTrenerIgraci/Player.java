package J18DrExtAbstractKlubTrenerIgraci;

class Player extends Person {
    private int number;
    private int shoeSize;

    public Player(String firstName, String lastName, int number, int shoeSize) {
        super(firstName, lastName);
        this.number = number;
        this.shoeSize = shoeSize;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (#" + number + ")";
    }
}
