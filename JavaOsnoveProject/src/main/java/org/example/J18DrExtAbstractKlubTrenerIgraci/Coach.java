package J18DrExtAbstractKlubTrenerIgraci;

class Coach extends Person {
    private int yearsOfExperience;

    public Coach(String firstName, String lastName, int yearsOfExperience) {
        super(firstName, lastName);
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Experience: " + yearsOfExperience + " years)";
    }
}
