package bowling.domain;

public class Player {
    private static final int NAME_LENGTH = 3;
    private final int id;
    private final String name;

    public Player(int id, String name) {
        validateName(name);
        this.id = id;
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("name length is must " + NAME_LENGTH);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
