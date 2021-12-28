package bowling.domain;

import java.util.Objects;

public class PlayerName {

    public static final int VALID_NAME_LENGTH = 3;
    public static final String ERROR_NAME_VALUE_MSG = "이름은 3글자로 입력해주세요";

    private final String name;

    public PlayerName(String name) {
        if (name.length() != VALID_NAME_LENGTH) {
            throw new IllegalArgumentException(ERROR_NAME_VALUE_MSG);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PlayerName{" +
                "name='" + name + '\'' +
                '}';
    }

}
