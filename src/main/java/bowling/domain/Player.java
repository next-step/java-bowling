package bowling.domain;

public class Player {
    private static final int MAX_NAME_LENGTH = 3;

    private final String name;

    private Player(final String name) {
        this.name = name;
    }

    public static Player from(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 최대 3글자까지 가능합니다.");
        }
        return new Player(name);
    }
}
