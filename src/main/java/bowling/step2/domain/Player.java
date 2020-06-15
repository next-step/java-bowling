package bowling.step2.domain;

import bowling.step2.exception.PlayerNameEmptyException;
import bowling.step2.exception.PlayerNameMaximumException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player valueOf(String name) {
        validateEmpty(name);
        validateMaximumLength(name);
        return new Player(name);
    }

    private static void validateEmpty(String name) {
        if (name == null || name.trim().equals("")) {
            throw new PlayerNameEmptyException();
        }
    }

    private static void validateMaximumLength(String name) {
        if (name.length() > 3) {
            throw new PlayerNameMaximumException();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Player) {
            Player temp = (Player)obj;
            return name.equals(temp.name);
        }
        return false;
    }
}