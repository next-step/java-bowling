package bowling.player.vo;

import java.util.Objects;

public class Name {
    private static final int NAME_LENGTH = 3;

    private final String name;

    public Name(final String name) {
        validateNullOrEmpty(name);
        validateNameLength(name.trim());
        this.name = name.trim();
    }

    private void validateNullOrEmpty(String name) {
        if (Objects.isNull(name) || name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름이 null 또는 공백입니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("이름의 길이는 3 이어야 합니다. - " + name);
        }
    }
}
