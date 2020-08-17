package bowling.domian.player;

import bowling.domian.player.exception.InvalidNameException;

public class Name {
    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name get(String name) {
        checkValid(name);

        return new Name(name);
    }

    private static void checkValid(String name) {
        if (name.length() != 3 || name.matches("^[a-zA-Z]*$")) {
            throw new InvalidNameException("사용자의 이름은 3글자 영문입니다!");
        }
    }
}
