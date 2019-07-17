package com.jaeyeonling.bowling.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class UsernameTest {

    @DisplayName("잘못된 유저이름일 경우 예외처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "abc", "sdfds", "sdfffff23", "123", "AAb", "bD2"})
    void throwInvalidUsernameException(final String username) {
        assertThatExceptionOfType(InvalidUsernameException.class)
                .isThrownBy(() -> Username.valueOf(username));
    }

    @DisplayName("유저이름은 생성할 때 유저이름과 같다.")
    @ParameterizedTest
    @ValueSource(strings = {"AAA", "BBB", "QWE", "BMB"})
    void getUsername(final String rawUsername) {
        // given
        final Username username = Username.valueOf(rawUsername);

        // when
        final String getUsername = username.getUsername();

        // then
        assertThat(getUsername).isEqualTo(rawUsername);
    }

    @DisplayName("이름이 같다면 같은 객체여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"AAA", "BBB", "QWE", "BMB"})
    void equals(final String rawUsername) {
        // given
        final Username username = Username.valueOf(rawUsername);
        final Username expected = Username.valueOf(rawUsername);

        // when
        final boolean equals = username.equals(expected);

        // then
        assertThat(equals).isTrue();
        assertThat(username == expected).isTrue();
    }
}
