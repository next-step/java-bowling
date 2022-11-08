package bowling.domain;

public class Player {
    private static final String EMPTY_NAME_EXCEPTION_MESSAGE = "이름은 공백일수 없습니다.";
    private static final String INVALID_LENGTH_NAME_EXCEPTION_MESSAGE = "이름의 길이는 3자를 초과할 수 없습니다.";
    private static final String NON_ALPHABET_NAME_EXCEPTION_MESSAGE = "이름에는 알파벳만 포함되어야 합니다.";

    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION_MESSAGE);
        }

        if (name.length() > 3) {
            throw new IllegalArgumentException(INVALID_LENGTH_NAME_EXCEPTION_MESSAGE);
        }

        if (!containsAlphabetOnly(name)) {
            throw new IllegalArgumentException(NON_ALPHABET_NAME_EXCEPTION_MESSAGE);
        }
    }

    private boolean containsAlphabetOnly(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
