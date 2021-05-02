package bowling.domain;

import bowling.IllegalPlayerNameException;

import java.util.Objects;

public class Player {
    private static int NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalPlayerNameException("이름의 길이가 잘못되었습니다");
        }
        this.name = name;
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

    public String name() {
        return name;
    }
}
