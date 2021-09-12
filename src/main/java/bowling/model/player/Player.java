package bowling.model.player;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private static final int NAME_LENGTH = 3;
    private static final Pattern NAME_PATTERN = Pattern.compile(String.format("^[a-zA-Z]{%d}$", NAME_LENGTH));

    private final String name;

    public Player(String name) {
        validateNamePattern(name);

        this.name = name;
    }

    private void validateNamePattern(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("플레이어 이름은 알파벳 %d글자로 이루어져야 합니다.", NAME_LENGTH));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (name.equals(o.toString())) {
            return true;
        }

        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
