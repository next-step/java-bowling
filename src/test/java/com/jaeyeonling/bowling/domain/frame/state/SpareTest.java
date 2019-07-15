package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.jaeyeonling.bowling.domain.pins.KnockdownPins.MAX;
import static com.jaeyeonling.bowling.domain.pins.KnockdownPins.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SpareTest {

    private FrameState state;

    @BeforeEach
    void setUp() {
        state = new Ready();
    }

    @DisplayName("스트라이크가 아니고 두 번 던져 합이 10이면 스페어다.")
    @ParameterizedTest
    @ValueSource(ints = {1,5,6,9})
    void type(final int rawKnockdownPins) {
        final KnockdownPins knockdownPins = valueOf(rawKnockdownPins);

        assertThat(state.bowl(knockdownPins).bowl(knockdownPins.remaining())).isInstanceOf(Spare.class);
    }

    @DisplayName("스트라이크가 아니고 두 번 던져 합이 10이면 완료된 상태이다.")
    @ParameterizedTest
    @ValueSource(ints = {1,5,6,9})
    void finishedType(final int rawKnockdownPins) {
        final KnockdownPins knockdownPins = valueOf(rawKnockdownPins);

        assertThat(state.bowl(knockdownPins).bowl(knockdownPins.remaining())).isInstanceOf(Finished.class);
    }

    @DisplayName("스페어는 게임이 끝났다.")
    @Test
    void doubleFinished() {
        assertThat(state.bowl(valueOf(1)).bowl(valueOf(9)).isFinished()).isTrue();
    }

    @DisplayName("시각화를 한다.")
    @Test
    void missSpareVisualize() {
        assertThat(state.bowl(valueOf(1)).bowl(valueOf(9)).toSymbol()).isEqualTo("1|/");
    }

    @DisplayName("시각화를 한다.")
    @Test
    void gutterSpareVisualize() {
        assertThat(state.bowl(valueOf(0)).bowl(MAX).toSymbol()).isEqualTo("-|/");
    }

    @DisplayName("게임이 끝난 후 게임 시 예외처리 한다.")
    @Test
    void throwAlreadyFinishedFrameStateException() {
        assertThatExceptionOfType(FinishedFrameStateException.class)
                .isThrownBy(() -> state.bowl(valueOf(1))
                        .bowl(valueOf(9))
                        .bowl(valueOf(5)));
    }
}
