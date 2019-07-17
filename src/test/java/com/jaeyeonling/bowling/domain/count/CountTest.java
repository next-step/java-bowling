package com.jaeyeonling.bowling.domain.count;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CountTest {

    private final int DEFAULT_VALUE = 10;
    private Count count;

    @BeforeEach
    void setUp() {
        count = Count.of(DEFAULT_VALUE);
    }

    @DisplayName("카운트업하면 기본 값 보다 높거나 같다.")
    @Test
    void isHigherAndEqualsWhenCount() {
        // given
        count = count.up();

        // when
        final boolean condition = count.isHigherAndEquals(DEFAULT_VALUE + 1);

        // then
        assertThat(condition).isTrue();
    }

    @DisplayName("카운트업하면 기본 값 보다 낮거나 같다.")
    @Test
    void isLowerAndEqualsWhenCount() {
        // given
        count = count.up();

        // when
        final boolean condition = count.isLowerAndEquals(DEFAULT_VALUE);

        // then
        assertThat(condition).isFalse();
    }

    @DisplayName("카운트다운하면 기본 값 보다 높거나 같지 않다.")
    @Test
    void isHigherAndEqualsWhenCountdown() {
        // given
        count = count.down();

        // when
        final boolean condition = count.isHigherAndEquals(DEFAULT_VALUE);

        // then
        assertThat(condition).isFalse();
    }

    @DisplayName("카운트다운하면 기본 값 보다 낮거나 같지 않다.")
    @Test
    void isLowerAndEqualsWhenCountdown() {
        // given
        count = count.down();

        // when
        final boolean condition = count.isLowerAndEquals(DEFAULT_VALUE - 1);

        // then
        assertThat(condition).isTrue();
    }

    @DisplayName("카운트 초기화 값이 " + Count.MIN + " 보다 낮으면 예외처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {Count.MIN - 1})
    void throwShorterThanMinCountException(final int initialValue) {
        assertThatExceptionOfType(ShorterThanMinCountException.class)
                .isThrownBy(() -> Count.of(initialValue));
    }
}
