package com.jaeyeonling.bowling.frame.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.frame.KnockdownPins.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MissTest {

    private FrameState state;

    @BeforeEach
    void setUp() {
        state = new Ready().bowl(valueOf(1));
    }

    @DisplayName("한번 던져서 거터나 스트라이크가 아니면 미스다.")
    @Test
    void singleType() {
        assertThat(state).isInstanceOf(Miss.class);
    }

    @DisplayName("두번 던져서 합이 10이 넘지 않으면 미스다.")
    @Test
    void doubleType() {
        assertThat(state.bowl(valueOf(1))).isInstanceOf(Miss.class);
        assertThat(state.bowl(valueOf(5))).isInstanceOf(Miss.class);
        assertThat(state.bowl(valueOf(4))).isInstanceOf(Miss.class);
        assertThat(state.bowl(valueOf(8))).isInstanceOf(Miss.class);
    }

    @DisplayName("미스 1번 시 끝나지 않는다.")
    @Test
    void singleFinished() {
        assertThat(state.isFinished()).isFalse();
    }

    @DisplayName("미스 2번 시 끝난다.")
    @Test
    void doubleFinished() {
        assertThat(state.bowl(valueOf(1)).isFinished()).isTrue();
    }

    @DisplayName("시각화를 한다.")
    @Test
    void readyVisualize() {
        assertThat(state.visualize()).isEqualTo("1");
        assertThat(state.bowl(valueOf(1)).visualize()).isEqualTo("1|1");
        assertThat(state.bowl(valueOf(4)).visualize()).isEqualTo("1|4");
        assertThat(state.bowl(valueOf(5)).visualize()).isEqualTo("1|5");
        assertThat(state.bowl(valueOf(8)).visualize()).isEqualTo("1|8");
    }

    @DisplayName("게임이 끝난 후 게임 시 예외처리 한다.")
    @Test
    void throwAlreadyFinishedFrameStateException() {
        assertThatExceptionOfType(AlreadyFinishedFrameStateException.class)
                .isThrownBy(() -> state.bowl(valueOf(1)).bowl(valueOf(1)));
    }
}
