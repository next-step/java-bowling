package bowling.domain;

public class Player {
    private Name name;

    private Player(Name name) {
        this.name = name;
    }

    public Player(String name) {
        this(new Name(name));
    }

    public Name name() {
        return name;
    }

}
