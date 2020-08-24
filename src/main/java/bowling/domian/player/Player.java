package bowling.domian.player;

public class Player {
    private final Name name;

    private Player(Name name) {
        this.name = name;
    }

    public static Player get(String name) {
        return new Player(Name.get(name));
    }

    public String getName() {
        return name.getName();
    }
}
