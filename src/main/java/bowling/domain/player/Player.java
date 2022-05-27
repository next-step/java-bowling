package bowling.domain.player;

import java.util.regex.Pattern;

public class Player {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private static void validate(String name) {
        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("플레이어의 이름은 영어 3글자만 가능합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
