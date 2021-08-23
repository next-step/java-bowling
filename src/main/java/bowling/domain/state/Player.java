package bowling.domain.state;

import bowling.exception.LimitException;
import java.util.Objects;

public class Player {
    private static final int MAX_COUNT = 3;
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        lengthValidation(name);
        return new Player(name);
    }

    private static void lengthValidation(String name) {
        if (name.length() != MAX_COUNT) {
            throw new LimitException("이름은 3글자여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
