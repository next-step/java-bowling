package com.jaeyeonling.bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("쓰러트린 핀의 갯수가 " + KnockdownPins.MIN_VALUE + " 이면 거터다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MIN_VALUE})
    void isGutter(final int rawKnockdownPins) {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final boolean isMin = knockdownPins.isGutter();

        // then
        assertThat(isMin).isTrue();
    }

    @DisplayName("쓰러트린 핀의 갯수가 " + KnockdownPins.MIN_VALUE + "이 아니면 거터가 아니다.")
    @ParameterizedTest
    @ValueSource(ints = {KnockdownPins.MIN_VALUE + 1, KnockdownPins.MAX_VALUE})
    void isNotGutter(final int rawKnockdownPins) {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final boolean isMin = knockdownPins.isGutter();

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

    @DisplayName("스트라이크를 심볼로 변환한다.")
    @Test
    void maxToSymbol() {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.MAX;

        // when
        final String symbol = knockdownPins.toSymbol();

        // then
        assertThat(symbol).isEqualTo("X");
    }

    @DisplayName("거터를 심볼로 변환한다.")
    @Test
    void minToSymbol() {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.GUTTER;

        // when
        final String symbol = knockdownPins.toSymbol();

        // then
        assertThat(symbol).isEqualTo("-");
    }

    @DisplayName("미스를 심볼로 변환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9})
    void missToSymbol(final int rawKnockdownPins) {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final String symbol = knockdownPins.toSymbol();

        // then
        assertThat(symbol).isEqualTo(String.valueOf(rawKnockdownPins));
    }

    @DisplayName("거터 2번을 심볼로 변환한다.")
    @Test
    void gutterAndGutterToSymbol() {
        // given
        final KnockdownPins first = KnockdownPins.GUTTER;
        final KnockdownPins second = KnockdownPins.GUTTER;

        // when
        final String symbol = first.toSymbol(second);

        // then
        assertThat(symbol).isEqualTo("-|-");
    }

    @DisplayName("거터 1번과 미스 1번을 심볼로 변환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9})
    void gutterAndMissToSymbol(final int rawKnockdownPins) {
        // given
        final KnockdownPins first = KnockdownPins.GUTTER;
        final KnockdownPins second = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final String symbol = first.toSymbol(second);

        // then
        assertThat(symbol).isEqualTo("-|" + rawKnockdownPins);
    }

    @DisplayName("미스 1번과 거터 1번을 심볼로 변환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9})
    void missAndGutterToSymbol(final int rawKnockdownPins) {
        // given
        final KnockdownPins first = KnockdownPins.valueOf(rawKnockdownPins);
        final KnockdownPins second = KnockdownPins.GUTTER;

        // when
        final String symbol = first.toSymbol(second);

        // then
        assertThat(symbol).isEqualTo(rawKnockdownPins + "|-");
    }

    @DisplayName("미스 2번을 심볼로 변환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4})
    void missAndMissToSymbol(final int rawKnockdownPins) {
        // given
        final KnockdownPins first = KnockdownPins.valueOf(rawKnockdownPins);
        final KnockdownPins second = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final String symbol = first.toSymbol(second);

        // then
        assertThat(symbol).isEqualTo(rawKnockdownPins + "|" + rawKnockdownPins);
    }

    @DisplayName("미스 1번과 스페어를 심볼로 변환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4})
    void missAndSpareToSymbol(final int rawKnockdownPins) {
        // given
        final int secondValue = KnockdownPins.MAX_VALUE - rawKnockdownPins;

        final KnockdownPins first = KnockdownPins.valueOf(rawKnockdownPins);
        final KnockdownPins second = KnockdownPins.valueOf(secondValue);

        // when
        final String symbol = first.toSymbol(second);

        // then
        assertThat(symbol).isEqualTo(rawKnockdownPins + "|" + secondValue);
    }

    @DisplayName("거터 1번과 스페어를 심볼로 변환한다.")
    @Test
    void minAndSpareToSymbol() {
        // given
        final KnockdownPins first = KnockdownPins.valueOf(KnockdownPins.MIN_VALUE);
        final KnockdownPins second = KnockdownPins.valueOf(KnockdownPins.MAX_VALUE);

        // when
        final String symbol = first.toSymbol(second);

        // then
        assertThat(symbol).isEqualTo("-|/");
    }
}

