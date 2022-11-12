package bowling.domain;

public class Player {
    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }
}
