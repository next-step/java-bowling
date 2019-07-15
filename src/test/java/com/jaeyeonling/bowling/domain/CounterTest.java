package com.jaeyeonling.bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

    private Counter counter;

    @BeforeEach
    void setUp() {
        counter = new Counter();
    }

    @DisplayName("카운트업하면 기본 값 보다 높거나 같다.")
    @Test
    void isHigherAndEqualsWhenCount() {
        // given
        counter.countup();

        // when
        final boolean condition = counter.isHigherAndEquals(1);

        // then
        assertThat(condition).isTrue();
    }

    @DisplayName("카운트업하면 기본 값 보다 낮거나 같다.")
    @Test
    void isLowerAndEqualsWhenCount() {
        // given
        counter.countup();

        // when
        final boolean condition = counter.isLowerAndEquals(0);

        // then
        assertThat(condition).isFalse();
    }

    @DisplayName("카운트다운하면 기본 값 보다 높거나 같지 않다.")
    @Test
    void isHigherAndEqualsWhenCountdown() {
        // given
        counter.countdown();

        // when
        final boolean condition = counter.isHigherAndEquals(0);

        // then
        assertThat(condition).isFalse();
    }

    @DisplayName("카운트다운하면 기본 값 보다 낮거나 같지 않다.")
    @Test
    void isLowerAndEqualsWhenCountdown() {
        // given
        counter.countdown();

        // when
        final boolean condition = counter.isLowerAndEquals(-1);

        // then
        assertThat(condition).isTrue();
    }

    @DisplayName("기본 값을 가진 카운터를 만든다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 75, 35, 354, 7, 63, 55, 74652, 3652335})
    void initializeCounter(final int initializeCount) {
        // given
        counter = new Counter(initializeCount);

        // when
        final boolean condition = counter.isLowerAndEquals(initializeCount);

        // then
        assertThat(condition).isTrue();
    }
}
