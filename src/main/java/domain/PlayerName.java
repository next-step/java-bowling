package domain;

import util.StringUtils;

public class PlayerName {
    private static final int VALID_NAME_LENGTH = 3;
    private final String name;

    public PlayerName(String name) {
        if (StringUtils.isEmpty(name) ||
            name.length() != VALID_NAME_LENGTH ||
            !name.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("잘못 입력된 이름입니다.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}