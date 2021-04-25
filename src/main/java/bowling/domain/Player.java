package bowling.domain;

public class Player {

    private static final int NAME_MAX_LENGTH = 3;
    private static final String NAME_LENGTH_EXCEPTION_MESSAGE = "이름은 3글자 이하 입니다";
    private static final String NAME_EMPTY_EXCEPTION_MESSAGE = "이름은 1글자 이상 입니다.";

    private final String name;

    private Player(final String name) {
        this.name = name;
    }

    public static Player from(final String name) {
        validateName(name);
        return new Player(name);
    }

    private static void validateName(String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_EXCEPTION_MESSAGE);
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(NAME_EMPTY_EXCEPTION_MESSAGE);
        }
    }

    public String name() {
        return this.name;
    }
}
