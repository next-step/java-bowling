package com.jaeyeonling.bowling.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class UserTest {

    @DisplayName("잘못된 유저이름일 경우 예외처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "abc", "sdfds", "sdfffff23", "123", "AAb", "bD2"})
    void throwInvalidUsernameException(final String username) {
        assertThatExceptionOfType(InvalidUsernameException.class)
                .isThrownBy(() -> User.of(username));
    }
}
