package bowling.vo;

import java.util.regex.Pattern;

public class GameUser {
    private static final Pattern NAME_CONDITION = Pattern.compile("^[a-zA-Z]{3}$");
    private final String name;

    public GameUser(final String name) {
        if (!NAME_CONDITION.matcher(name).find()) {
            throw new IllegalArgumentException("사용자의 이름은 영문 세글자여야 합니다.");
        }
        this.name = name;
    }
}
