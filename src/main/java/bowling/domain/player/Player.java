package bowling.domain.player;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private static final String NOT_MATCH_NAME_MESSAGE = "플레이어의 이름은 3글자이하 영문이어야 합니다.";
    private static final Pattern NAME_PATTERN = Pattern.compile("^([a-zA-Z]){3}");

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    private void validateName(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(NOT_MATCH_NAME_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
