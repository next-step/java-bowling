package bowling.domain.player;

import bowling.exception.InvalidPlayerNameException;
import bowling.util.StringLengthRange;

import java.util.Objects;

public class PlayerName {
    private static final StringLengthRange VALID_NAME_LENGTH = new StringLengthRange(1, 3);

    private final String name;

    public PlayerName(final String name) {
        VALID_NAME_LENGTH.orThrow(name, InvalidPlayerNameException::new);

        this.name = name;
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PlayerName name1 = (PlayerName) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PlayerName{" + "name='" + name + '\'' + '}';
    }
}