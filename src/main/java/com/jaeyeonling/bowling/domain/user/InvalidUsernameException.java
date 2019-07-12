package com.jaeyeonling.bowling.domain.user;

public class InvalidUsernameException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "유저의 이름은 '%s' 패턴을 만족해야 합니다. (입력 값: '%s')";

    InvalidUsernameException(final String input) {
        super(String.format(ERROR_MESSAGE, Username.PATTERN, input));
    }
}
