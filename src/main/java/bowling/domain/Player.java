package bowling.domain;

import java.util.Objects;

public class Player {
    private static final int NAME_LENGTH = 3;
    private final String name;

    public Player(String name) {
        checkValidation(name);
        this.name = name;
    }

    private void checkValidation(String name) {
        if (name == null || name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름 길이는 " + NAME_LENGTH + "입니다.");
        }
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
        return name;
    }
}
