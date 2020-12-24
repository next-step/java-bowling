package bowling_step3.domain;

import bowling_step3.exception.PlayerExceedsMaxLengthException;
import bowling_step3.exception.PlayerNullPointException;
import bowling_step3.util.StringUtils;

import java.util.Objects;

public class Player {
    public static final int NAME_MAX_LENGTH = 3;
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        validate(name);
        return new Player(name);
    }

    private static void validate(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new PlayerNullPointException();
        }

        if(name.length() > NAME_MAX_LENGTH) {
            throw new PlayerExceedsMaxLengthException();
        }
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
}
