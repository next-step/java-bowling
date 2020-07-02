package bowling.domain;

public class Player {
    private static final int NAME_LENGTH = 3;
    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("name length is must " + NAME_LENGTH);
        }
    }

    public String getName() {
        return name;
    }
}
