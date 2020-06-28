package bowling.vo;

import java.util.regex.Pattern;

public class GameUser {
    private static final String NAME_CONDITION = "^[a-zA-Z]{3}";
    private final String name;

    public GameUser(final String name) {
        if (!Pattern.matches(NAME_CONDITION, name)) {
            throw new IllegalArgumentException("사용자 이름은 영문, 3글자여야합니다.");
        }
        this.name = name;
    }
}
