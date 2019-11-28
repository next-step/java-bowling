package bowling.domain;

import java.util.Objects;

public class Player {

    private static final int NAME_LENGTH = 3;

    private String name;

    public Player(String name) {
        this.name = name;
        validate();
    }

    private void validate() {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름의 길이가 적절하지 않습니다.");
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
}
