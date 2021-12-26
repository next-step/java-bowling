package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player create(String name) {
        validateName(name);
        return new Player(name);
    }

    private static void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("플레이어 이름이 null이 전달되었습니다.");
        }
        if (!NAME_REGEX.matcher(name).matches()) {
            throw new IllegalArgumentException("플레이어 이름은 영문이고 3자리만 허용합니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
