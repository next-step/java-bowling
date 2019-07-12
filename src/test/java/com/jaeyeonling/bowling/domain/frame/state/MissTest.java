package com.jaeyeonling.bowling.domain.frame.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.jaeyeonling.bowling.domain.frame.KnockdownPins.valueOf;
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
    @ParameterizedTest
    @ValueSource(ints = {1,5,6,8})
    void doubleType(final int knockdownPins) {
        assertThat(state.bowl(valueOf(knockdownPins))).isInstanceOf(Miss.class);
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

    @DisplayName("miss 1번 시 시각화를 한다.")
    @Test
    void missVisualize() {
        assertThat(state.visualize()).isEqualTo("1");
    }

    @DisplayName("miss 2번 시 시각화를 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1,5,6,8})
    void missMissVisualize(final int knockdownPins) {
        assertThat(state.bowl(valueOf(knockdownPins)).visualize()).isEqualTo("1|" + knockdownPins);
    }

    @DisplayName("게임이 끝난 후 게임 시 예외처리 한다.")
    @Test
    void throwAlreadyFinishedFrameStateException() {
        assertThatExceptionOfType(AlreadyFinishedFrameStateException.class)
                .isThrownBy(() -> state.bowl(valueOf(1)).bowl(valueOf(1)));
    }
}
