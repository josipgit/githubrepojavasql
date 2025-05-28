package J18DrExtAbstractKlubTrenerIgraci;

abstract class Person { // base class (parent, super, root)
    protected String firstName; // protected je dostupan samo u child klasama (derived, subclass, extended)
    protected String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

