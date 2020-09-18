package bowling.model;

import java.util.Objects;

public class Player {
    public static final int FIXED_LENGTH = 3;
    private final String name;

    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("주어진 이름이 비어있습니다.");
        }
        if (name.length() != FIXED_LENGTH) {
            throw new IllegalArgumentException("주어진 이름은 3자여야합니다.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
