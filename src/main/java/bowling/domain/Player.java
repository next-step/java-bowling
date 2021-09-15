package bowling.domain;

import bowling.exception.InvalidInputException;

import java.util.Objects;

public class Player {
    private static final String INVALID_USER_NAME_MESSAGE = "이름은 3글자로 지정해주세요";
    private static int NAME_LENGTH = 3;

    private String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new InvalidInputException(INVALID_USER_NAME_MESSAGE);
        }
    }

    public String name() {
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

    @Override
    public String toString() {
        return name;
    }
}
