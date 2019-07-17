package com.jaeyeonling.bowling.domain.frame.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.domain.pins.KnockdownPins.MAX;
import static com.jaeyeonling.bowling.domain.pins.KnockdownPins.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StrikeTest {

    private FrameState state;

    @BeforeEach
    void setUp() {
        state = new Ready().bowl(MAX);
    }

    @DisplayName("첫 볼링에 최대점수면 스트라이크다.")
    @Test
    void singleType() {
        assertThat(state).isInstanceOf(Strike.class);
    }

    @DisplayName("스트라이크는 완료된 상태이다.")
    @Test
    void finishedType() {
        assertThat(state).isInstanceOf(Finished.class);
    }

    @DisplayName("스트라이크면 끝난다.")
    @Test
    void doubleFinished() {
        assertThat(state.isFinished()).isTrue();
    }

    @DisplayName("시각화를 한다.")
    @Test
    void readyVisualize() {
        assertThat(state.toSymbol()).isEqualTo("X");
    }

    @DisplayName("게임이 끝난 후 게임 시 예외처리 한다.")
    @Test
    void throwAlreadyFinishedFrameStateException() {
        assertThatExceptionOfType(FinishedFrameStateException.class)
                .isThrownBy(() -> state.bowl(valueOf(1)));
    }
}
