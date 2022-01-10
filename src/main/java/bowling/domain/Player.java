package bowling.domain;

public class Player {
    private final String name;

    public Player(String name) {
        checkNameLength(name);
        this.name = name;
    }

    private void checkNameLength(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException("name's length must be 3");
        }
    }

    public String getName() {
        return name;
    }

}
