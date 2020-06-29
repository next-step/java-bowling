package bowling.domain;

public class Player {

    private final String name;
    private final int position;

    public Player(String name, int position) {
        if (name.length() != 3) {
            throw new IllegalArgumentException("user name length must 3");
        }
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
