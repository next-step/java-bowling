package bowling.domain;

import java.util.Objects;

public class Player {
    private final String name;

    public Player(String name) {
        checkValidation(name);
        this.name = name;
    }

    private void checkValidation(String name) {
        if (name == null || name.length() != 3) {
            throw new IllegalArgumentException("플레이어 이름 길이는 3입니다.");
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
