package bowling;

import java.util.regex.Pattern;

public class Player {

    private static final String NAMING_REGEX = "^[a-zA-Z]{3}$";
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private static void validate(String name) throws IllegalArgumentException {
        if (!Pattern.matches(NAMING_REGEX, name)) {
            throw new IllegalArgumentException("이름은 영문 3 글자만 허용 됩니다.");
        }
    }

    public static Player of(String name) {
        validate(name);
        return new Player(name);
    }
}
