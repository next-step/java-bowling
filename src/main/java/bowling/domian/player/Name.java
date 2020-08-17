package bowling.domian.player;

import bowling.domian.player.exception.InvalidNameException;

public class Name {
    private static final String NAME_INVALID_ERROR_MESSAGE = "사용자의 이름은 3글자 영문입니다!";
    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name get(String name) {
        checkValid(name);

        return new Name(name);
    }

    private static void checkValid(String name) {
        if (!isLengthValid(name) || !isOnlyAlphabet(name)) {
            throw new InvalidNameException(NAME_INVALID_ERROR_MESSAGE);
        }
    }

    private static boolean isLengthValid(String name) {
        return name.length() == 3;
    }

    private static boolean isOnlyAlphabet(String name) {
        return name.matches("^[a-zA-Z]*$");
    }
}
