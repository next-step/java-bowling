package bowling.domain;

import bowling.domain.exception.IncorrectNameArgumentException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private static final String INCORRECT_LENGTH_ERROR_MESSAGE =
            "이름은 영문 세글자로 입력해 주세요.";
    private static final Pattern namePattern = Pattern.compile("[A-Za-z]{3}");

    private String name;

    public Player(String name) {
        if (Objects.isNull(name) || !namePattern.matcher(name).matches()) {
            throw new IncorrectNameArgumentException(
                    INCORRECT_LENGTH_ERROR_MESSAGE);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
