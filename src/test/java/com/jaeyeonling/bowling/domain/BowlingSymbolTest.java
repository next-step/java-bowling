package com.jaeyeonling.bowling.domain;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingSymbolTest {

    @DisplayName("스트라이크를 심볼로 변환한다.")
    @Test
    void maxToSymbol() {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.MAX;

        // when
        final String symbol = BowlingSymbol.toSymbolFrom(knockdownPins);

        // then
        assertThat(symbol).isEqualTo("X");
    }

    @DisplayName("거터를 심볼로 변환한다.")
    @Test
    void minToSymbol() {
        // given
        final KnockdownPins knockdownPins = KnockdownPins.MIN;

        // when
        final String symbol = BowlingSymbol.toSymbolFrom(knockdownPins);

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
        final String symbol = BowlingSymbol.toSymbolFrom(knockdownPins);

        // then
        assertThat(symbol).isEqualTo(String.valueOf(rawKnockdownPins));
    }

    @DisplayName("거터 2번을 심볼로 변환한다.")
    @Test
    void gutterAndGutterToSymbol() {
        // given
        final KnockdownPins first = KnockdownPins.MIN;
        final KnockdownPins second = KnockdownPins.MIN;

        // when
        final String symbol = BowlingSymbol.toSymbolFrom(first, second);

        // then
        assertThat(symbol).isEqualTo("-|-");
    }

    @DisplayName("거터 1번과 미스 1번을 심볼로 변환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9})
    void gutterAndMissToSymbol(final int rawKnockdownPins) {
        // given
        final KnockdownPins first = KnockdownPins.MIN;
        final KnockdownPins second = KnockdownPins.valueOf(rawKnockdownPins);

        // when
        final String symbol = BowlingSymbol.toSymbolFrom(first, second);

        // then
        assertThat(symbol).isEqualTo("-|" + rawKnockdownPins);
    }

    @DisplayName("미스 1번과 거터 1번을 심볼로 변환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9})
    void missAndGutterToSymbol(final int rawKnockdownPins) {
        // given
        final KnockdownPins first = KnockdownPins.valueOf(rawKnockdownPins);
        final KnockdownPins second = KnockdownPins.MIN;

        // when
        final String symbol = BowlingSymbol.toSymbolFrom(first, second);

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
        final String symbol = BowlingSymbol.toSymbolFrom(first, second);

        // then
        assertThat(symbol).isEqualTo(rawKnockdownPins + "|" + rawKnockdownPins);
    }

    @DisplayName("미스 1번과 스페어를 심볼로 변환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4})
    void missAndSpareToSymbol(final int rawKnockdownPins) {
        // given
        final KnockdownPins first = KnockdownPins.valueOf(rawKnockdownPins);
        final KnockdownPins second = first.remaining();

        // when
        final String symbol = BowlingSymbol.toSymbolFrom(first, second);

        // then
        assertThat(symbol).isEqualTo(rawKnockdownPins + "|/");
    }

    @DisplayName("거터 1번과 스페어를 심볼로 변환한다.")
    @Test
    void minAndSpareToSymbol() {
        // given
        final KnockdownPins first = KnockdownPins.MIN;
        final KnockdownPins second = KnockdownPins.MAX;

        // when
        final String symbol = BowlingSymbol.toSymbolFrom(first, second);

        // then
        assertThat(symbol).isEqualTo("-|/");
    }
}
