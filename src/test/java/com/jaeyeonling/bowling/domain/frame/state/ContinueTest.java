package com.jaeyeonling.bowling.domain.frame.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.domain.pins.KnockdownPins.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class ContinueTest {

    private FrameState state;

    @BeforeEach
    void setUp() {
        state = new Ready().bowl(valueOf(1));
    }

    @DisplayName("미스 1번 시 끝나지 않는다.")
    @Test
    void singleFinished() {
        assertThat(state.isFinished()).isFalse();
    }


    @DisplayName("miss 1번 시 시각화를 한다.")
    @Test
    void missVisualize() {
        assertThat(state.toSymbol()).isEqualTo("1");
    }

    @DisplayName("한번 던져서 거터나 스트라이크가 아니면 진행중다.")
    @Test
    void singleType() {
        assertThat(state).isInstanceOf(Continue.class);
    }
}
