package bowling.domain;

public class Player {
    private static final int MAX_NAME_LENGTH = 3;
    private static final String OVER_MAX_NAME_LENGTH_MESSAGE =
            String.format("이름은 %d글자까지 가능합니다.", MAX_NAME_LENGTH);

    private final String name;

    public Player(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(OVER_MAX_NAME_LENGTH_MESSAGE);
        }

        this.name = name;
    }

    public String name() {
        return name;
    }
}