package com.jaeyeonling.bowling.domain.pins;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class KnockdownPinsTest {

    @DisplayName("쓰러트린 핀의 갯수가 " + KnockdownPins.MAX_VALUE + "보다 크면 예외처리 한다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MAX_VALUE + 1})
    void throwLongerThanMaxKnockdownPinsException(final int knockdownPins) {
        assertThatExceptionOfType(LongerThanMaxKnockdownPinsException.class)
                .isThrownBy(() -> KnockdownPins.valueOf(knockdownPins));
    }

    @DisplayName("쓰러트린 핀의 갯수가 " + KnockdownPins.MIN_VALUE + "보다 낮으면 예외처리 한다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MIN_VALUE - 1})
    void throwShorterThanMinKnockdownPinsException(final int knockdownPins) {
        assertThatExceptionOfType(ShorterThanMinKnockdownPinsException.class)
                .isThrownBy(() -> KnockdownPins.valueOf(knockdownPins));
    }

    @DisplayName("쓰러트린 핀의 갯수가 같다면 같은 객체다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MIN_VALUE, 5, KnockdownPins.MAX_VALUE})
    void equals(final int rawKnockdownPins) {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.valueOf(rawKnockdownPins);
        final KnockdownPins expected = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final boolean isEquals = knockdownPins.equals(expected);

        // then
        assertThat(isEquals).isTrue();
    }

    @DisplayName("쓰러트린 핀의 갯수가 " + KnockdownPins.MIN_VALUE + " 이면 최소다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MIN_VALUE})
    void isMin(final int rawKnockdownPins) {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final boolean isMin = knockdownPins.isMin();

        // then
        assertThat(isMin).isTrue();
    }

    @DisplayName("쓰러트린 핀의 갯수가 " + KnockdownPins.MIN_VALUE + "이 아니면 최소가 아니다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MIN_VALUE + 1, KnockdownPins.MAX_VALUE})
    void isNotMin(final int rawKnockdownPins) {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final boolean isMin = knockdownPins.isMin();

        // then
        assertThat(isMin).isFalse();
    }

    @DisplayName("쓰러트린 핀의 갯수가 " + KnockdownPins.MAX_VALUE + " 이면 최대다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MAX_VALUE})
    void isMax(final int rawKnockdownPins) {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final boolean isMax = knockdownPins.isMax();

        // then
        assertThat(isMax).isTrue();
    }

    @DisplayName("쓰러트린 핀의 갯수가 " + KnockdownPins.MIN_VALUE + "이 아니면 최대가 아니다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MIN_VALUE, KnockdownPins.MAX_VALUE - 1})
    void isNotMax(final int rawKnockdownPins) {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final boolean isMax = knockdownPins.isMax();

        // then
        assertThat(isMax).isFalse();
    }
}

