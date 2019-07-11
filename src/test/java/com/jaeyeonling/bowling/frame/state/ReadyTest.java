package com.jaeyeonling.bowling.frame.state;

import com.jaeyeonling.bowling.frame.KnockdownPins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.frame.KnockdownPins.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    private FrameState state;

    @BeforeEach
    void setUp() {
        state = new Ready();
    }

    @DisplayName("초기값은 타입은 Ready 이다.")
    @Test
    void readyType() {
        assertThat(state).isInstanceOf(Ready.class);
    }

    @DisplayName("시각화 시 공백이다.")
    @Test
    void readyVisualize() {
        assertThat(state.visualize()).isEqualTo("");
    }

    @DisplayName("볼링을 할 수 있다.")
    @Test
    void bowl() {
        assertThat(state.bowl(KnockdownPins.GUTTER)).isNotNull();
        assertThat(state.bowl(valueOf(5))).isNotNull();
        assertThat(state.bowl(KnockdownPins.MAX)).isNotNull();
    }
}
