package bowling;

public class Player {

    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Player of(String name) {
        return new Player(name);
    }
}
