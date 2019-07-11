package com.jaeyeonling.bowling.domain.frame.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.domain.frame.KnockdownPins.*;
import static org.assertj.core.api.Assertions.assertThat;

class FinalStateTest {

    private FrameState state;

    @BeforeEach
    void setUp() {
        state = new FinalState();

        assertThat(state.visualize()).isEqualTo("");
    }

    @DisplayName("거터 1번 시 시각화한다.")
    @Test
    void singleGutterVisualize() {
        assertThat(state.bowl(GUTTER).visualize()).isEqualTo("-");
    }

    @DisplayName("거터 1번 시 끝나지 않는다.")
    @Test
    void singleGutterFinished() {
        assertThat(state.bowl(GUTTER).isFinished()).isFalse();
    }

    @DisplayName("거터 2번 시 시각화한다.")
    @Test
    void doubleGutterVisualize() {
        assertThat(state.bowl(GUTTER).bowl(GUTTER).visualize()).isEqualTo("-|-");
    }

    @DisplayName("거터 2번 시 끝난다.")
    @Test
    void doubleGutterFinished() {
        assertThat(state.bowl(GUTTER).bowl(GUTTER).isFinished()).isTrue();
    }

    @DisplayName("스페어 거터 시 시각화한다.")
    @Test
    void spareGutterVisualize() {
        assertThat(state.bowl(GUTTER).bowl(MAX).bowl(GUTTER).visualize()).isEqualTo("-|/|-");
    }

    @DisplayName("스페어 거터 시 끝난다.")
    @Test
    void spareGutterFinished() {
        assertThat(state.bowl(GUTTER).bowl(MAX).bowl(GUTTER).isFinished()).isTrue();
    }

    @DisplayName("스페어 스트라이크 시 시각화한다.")
    @Test
    void spareStrikeVisualize() {
        assertThat(state.bowl(GUTTER).bowl(MAX).bowl(MAX).visualize()).isEqualTo("-|/|X");
    }

    @DisplayName("스페어 스트라이크 시 끝난다.")
    @Test
    void spareStrikeFinished() {
        assertThat(state.bowl(GUTTER).bowl(MAX).bowl(MAX).isFinished()).isTrue();
    }

    @DisplayName("스페어 미스 시 시각화한다.")
    @Test
    void spareMissVisualize() {
        assertThat(state.bowl(GUTTER).bowl(MAX).bowl(valueOf(5)).visualize()).isEqualTo("-|/|5");
    }

    @DisplayName("스페어 미스 시 끝난다.")
    @Test
    void spareMissFinished() {
        assertThat(state.bowl(GUTTER).bowl(MAX).bowl(valueOf(5)).isFinished()).isTrue();
    }

    @DisplayName("미스 시 시각화한다.")
    @Test
    void missVisualize() {
        assertThat(state.bowl(valueOf(5)).visualize()).isEqualTo("5");
    }

    @DisplayName("미스 시 끝나지 않는다.")
    @Test
    void missFinished() {
        assertThat(state.bowl(valueOf(5)).isFinished()).isFalse();
    }

    @DisplayName("미스 미스 시 시각화한다.")
    @Test
    void missMissVisualize() {
        assertThat(state.bowl(valueOf(5)).bowl(valueOf(1)).visualize()).isEqualTo("5|1");
    }

    @DisplayName("미스 미스 시 끝난다.")
    @Test
    void missMissFinished() {
        assertThat(state.bowl(valueOf(5)).bowl(valueOf(1)).isFinished()).isTrue();
    }

    @DisplayName("미스 거터 시 시각화한다.")
    @Test
    void missGutterVisualize() {
        assertThat(state.bowl(valueOf(5)).bowl(GUTTER).visualize()).isEqualTo("5|-");
    }

    @DisplayName("미스 거터 시 끝난다.")
    @Test
    void missGutterFinished() {
        assertThat(state.bowl(valueOf(5)).bowl(GUTTER).isFinished()).isTrue();
    }

    @DisplayName("스크라이크 시 시각화한다.")
    @Test
    void strikeVisualize() {
        assertThat(state.bowl(MAX).visualize()).isEqualTo("X");
    }

    @DisplayName("스크라이크 시 끝나지 않는다.")
    @Test
    void strikeFinished() {
        assertThat(state.bowl(MAX).isFinished()).isFalse();
    }

    @DisplayName("스크라이크 스트라이크 시 시각화한다.")
    @Test
    void strikeStrikeVisualize() {
        assertThat(state.bowl(MAX).bowl(MAX).visualize()).isEqualTo("X|X");
    }

    @DisplayName("스크라이크 스크라이크 시 끝나지 않는다.")
    @Test
    void strikeStrikeFinished() {
        assertThat(state.bowl(MAX).bowl(MAX).isFinished()).isFalse();
    }

    @DisplayName("스크라이크 스트라이크 스트라이크 시 시각화한다.")
    @Test
    void strikeStrikeStrikeVisualize() {
        assertThat(state.bowl(MAX).bowl(MAX).bowl(MAX).visualize()).isEqualTo("X|X|X");
    }

    @DisplayName("스크라이크 스크라이크 스크라이크 시 끝난다.")
    @Test
    void strikeStrikeStrikeFinished() {
        assertThat(state.bowl(MAX).bowl(MAX).bowl(MAX).isFinished()).isTrue();
    }
}
