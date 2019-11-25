package bowling.domain;

import java.util.Objects;

public class Player {

    public static final int MIN_NAME_LENGTH = 3;

    private final String name;

    public static Player create(String name) {
        return new Player(name);
    }

    private Player(String name) {
        assertNameLength(name);
        this.name = name;
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

    private void assertNameLength(String name) {
        if (name == null || name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 최소 3글자 이상이여야 합니다.");
        }
    }
}
