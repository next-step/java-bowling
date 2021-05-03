package bowling;

import java.util.Objects;

public class Player {
    private static final int NAME_LENGTH = 3;
    private static final String INVALID_NAME = "이름은 3자여야합니다.";
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        validName(name);
        return new Player(name);
    }

    private static void validName(String name) {
        if(name.length() !=NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME);
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
        return "" + name + "";
    }
}
