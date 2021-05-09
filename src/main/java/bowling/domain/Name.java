package bowling.domain;

public final class Name {

    private final String name;

    public Name(final String name) {
        this.name = name;
    }

    public static Name valueOf(final String name) {
        return new Name(name);
    }
}
