package bowling.domain;

import java.util.Objects;

import static bowling.Messages.WARNING_PLAYERNAME_MUST_ENGLISH;
import static bowling.Messages.WARNING_PLAYERNAME_NOT_ALLOWED_LENGTH;

public class PlayerName {
    private static final int CRITERIA_NAME_LENGTH = 3;

    private String name;

    public PlayerName(String name) {
        validateNameLength(name);
        validateNameInEnglish(name);
        this.name = name;
    }

    public static PlayerName of(String name) {
        return new PlayerName(name);
    }

    private void validateNameLength(String name) {
        if (name.length() != CRITERIA_NAME_LENGTH) {
            throw new IllegalArgumentException(WARNING_PLAYERNAME_NOT_ALLOWED_LENGTH);
        }
    }

    private void validateNameInEnglish(String name) {
        if (name.toLowerCase() == name.toUpperCase()) {
            throw new IllegalArgumentException(WARNING_PLAYERNAME_MUST_ENGLISH);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}