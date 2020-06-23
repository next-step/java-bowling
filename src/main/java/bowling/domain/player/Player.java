package bowling.domain.player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {

    private static final Pattern NAME_PATTERN = Pattern.compile("^([a-zA-Z]){3}");

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        validateName(name);
        return new Player(name);
    }

    private static void validateName(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("플레이어의 이름은 3글자 알파벳이어야 합니다.");
        }
    }
}
