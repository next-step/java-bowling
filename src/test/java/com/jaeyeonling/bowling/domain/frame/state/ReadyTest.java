package com.jaeyeonling.bowling.domain.frame.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.domain.pins.KnockdownPins.*;
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
        assertThat(state.toSymbol()).isEqualTo("");
    }

    @DisplayName("거터를 할 수 있다.")
    @Test
    void gutterBowl() {
        assertThat(state.bowl(MIN)).isNotNull();
    }

    @DisplayName("미스를 할 수 있다.")
    @Test
    void missBowl() {
        assertThat(state.bowl(valueOf(5))).isNotNull();
    }

    @DisplayName("스트라이크를 할 수 있다.")
    @Test
    void strikeBowl() {
        assertThat(state.bowl(MAX)).isNotNull();
    }

    @DisplayName("스페어를 할 수 있다.")
    @Test
    void spareBowl() {
        assertThat(state.bowl(valueOf(5)).bowl(valueOf(5))).isNotNull();
    }
}
