package bowling.bowler;

import bowling.global.exception.NotMatchingBowlerNameException;
import bowling.global.utils.ExceptionMessage;

import java.util.Objects;

import static bowling.global.utils.CommonConstant.NUMBER_THREE;

public class Bowler {

    private final String name;

    private Bowler(String name) {
        validatePlayerNameisNull(name);
        validatePlayerNameLength(name);
        this.name = name.toUpperCase();
    }

    public static Bowler of(String name) {
        return new Bowler(name);
    }

    public String getName() {
        return name;
    }

    private void validatePlayerNameisNull(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NotMatchingBowlerNameException(ExceptionMessage.INVALID_PLAYER_NAME_IS_NULL);
        }
    }

    private void validatePlayerNameLength(String name) {
        if (name.length() > NUMBER_THREE) {
            throw new NotMatchingBowlerNameException(ExceptionMessage.INVALID_PLAYER_NAME_LENGTH);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowler bowler = (Bowler) o;
        return Objects.equals(name, bowler.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
