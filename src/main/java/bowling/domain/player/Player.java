package bowling.domain.player;

public class Player {
    private final Name name;

    private Player(String name) {
        this.name = Name.of(name);
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
