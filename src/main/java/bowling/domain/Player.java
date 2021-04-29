package bowling.domain;

import java.util.Objects;

public class Player {
    private final static int NAME_LENGTH = 3;
    private final String name;

    private Player(final String name) {
        validation(name);
        this.name = name;
    }

    private void validation(final String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("참가자의 이름은 3글자로 입력해 주세요.");
        }
    }

    public String name() {
        return name;
    }

    public static Player from(String value) {
        return new Player(value);
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

