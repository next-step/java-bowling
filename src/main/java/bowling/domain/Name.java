package bowling.domain;

import bowling.exception.InvalidNameException;

public class Name {
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 3;

    private final String name;

    public Name(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidNameException("참가자 이름은 빈값이 될 수 없습니다.");
        }

        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new InvalidNameException("이름의 길이는 " + MINIMUM_NAME_LENGTH + "~" + MAXIMUM_NAME_LENGTH + " 사이의 값 입니다.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}