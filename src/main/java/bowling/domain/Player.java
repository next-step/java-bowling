package bowling.domain;

import java.util.Objects;

public class Player {
    private final String name;

    public static Player of(String name) {
        return new Player(name);
    }

    Player(String name) {
        this.name = validate(name.trim());
    }

    private String validate(String name) {
        if (name.length() > 3 || name.isEmpty()) {
            throw new IllegalArgumentException("Player 이름은 3글자 이하여야 합니다.");
        }
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Player player = (Player)o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
