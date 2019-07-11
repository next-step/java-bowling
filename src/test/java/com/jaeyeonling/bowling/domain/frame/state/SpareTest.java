package com.jaeyeonling.bowling.domain.frame.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.domain.frame.KnockdownPins.*;
import static com.jaeyeonling.bowling.domain.frame.KnockdownPins.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SpareTest {

    private FrameState state;

    @BeforeEach
    void setUp() {
        state = new Ready();
    }

    @DisplayName("스트라이크가 아니고 두 번 던져 합이 10이면 스페어다.")
    @Test
    void type() {
        assertThat(state.bowl(valueOf(1)).bowl(valueOf(9))).isInstanceOf(Spare.class);
        assertThat(state.bowl(valueOf(0)).bowl(MAX)).isInstanceOf(Spare.class);
        assertThat(state.bowl(valueOf(5)).bowl(valueOf(5))).isInstanceOf(Spare.class);
    }

    @DisplayName("스트라이크가 아니고 두 번 던져 합이 10이면 완료된 상태이다.")
    @Test
    void finishedType() {
        assertThat(state.bowl(valueOf(1)).bowl(valueOf(9))).isInstanceOf(Finished.class);
        assertThat(state.bowl(valueOf(0)).bowl(MAX)).isInstanceOf(Finished.class);
        assertThat(state.bowl(valueOf(5)).bowl(valueOf(5))).isInstanceOf(Finished.class);
    }

    @DisplayName("스페어는 게임이 끝났다.")
    @Test
    void doubleFinished() {
        assertThat(state.bowl(valueOf(1)).bowl(valueOf(9)).isFinished()).isTrue();
    }

    @DisplayName("시각화를 한다.")
    @Test
    void readyVisualize() {
        assertThat(state.bowl(valueOf(1)).bowl(valueOf(9)).visualize()).isEqualTo("1|/");
        assertThat(state.bowl(valueOf(0)).bowl(MAX).visualize()).isEqualTo("-|/");
        assertThat(state.bowl(valueOf(5)).bowl(valueOf(5)).visualize()).isEqualTo("5|/");
    }

    @DisplayName("게임이 끝난 후 게임 시 예외처리 한다.")
    @Test
    void throwAlreadyFinishedFrameStateException() {
        assertThatExceptionOfType(AlreadyFinishedFrameStateException.class)
                .isThrownBy(() -> state.bowl(valueOf(1))
                        .bowl(valueOf(9))
                        .bowl(valueOf(5)));
    }
}
