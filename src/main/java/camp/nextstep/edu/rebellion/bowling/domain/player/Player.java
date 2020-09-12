package camp.nextstep.edu.rebellion.bowling.domain.player;


import camp.nextstep.edu.rebellion.bowling.util.StringUtil;

import java.util.regex.Pattern;

public class Player {
    private static final String ALLOWED_NAME_CHARACTER = "^[a-zA-Z]*$";
    private static final int MAX_NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        checkLength(name);
        checkCharacter(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void checkCharacter(String name) {
        if (!Pattern.matches(ALLOWED_NAME_CHARACTER, name)) {
            throw new IllegalArgumentException("참가자의 이름은 영문자만 가능합니다 " + name);
        }
    }

    private void checkLength(String name) {
        if (StringUtil.isEmpty(name) || MAX_NAME_LENGTH < name.length()) {
            throw new IllegalArgumentException("참가자의 이름은 최대 3글자 입니다 " + name);
        }
    }
}

