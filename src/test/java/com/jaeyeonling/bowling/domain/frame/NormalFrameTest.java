package com.jaeyeonling.bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.domain.frame.KnockdownPins.*;
import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private Frame firstFrame;

    @BeforeEach
    void setUp() {
        firstFrame = NormalFrame.first();
    }

    @DisplayName("거터 시 시각화한다.")
    @Test
    void gutterVisualize() {
        firstFrame.bowl(GUTTER);

        assertThat(firstFrame.getFrameState()).isEqualTo("   -   |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("거터 거터 시 시각화한다.")
    @Test
    void gutterGutterVisualize() {
        firstFrame.bowl(GUTTER).bowl(GUTTER);

        assertThat(firstFrame.getFrameState()).isEqualTo("  -|-  |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("거터 미스 시 시각화한다.")
    @Test
    void gutterMissVisualize() {
        firstFrame.bowl(GUTTER).bowl(valueOf(1));

        assertThat(firstFrame.getFrameState()).isEqualTo("  -|1  |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("미스 시 시각화한다.")
    @Test
    void missVisualize() {
        firstFrame.bowl(valueOf(1));

        assertThat(firstFrame.getFrameState()).isEqualTo("   1   |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("미스 미스 시 시각화한다.")
    @Test
    void missMissVisualize() {
        firstFrame.bowl(valueOf(1)).bowl(valueOf(1));

        assertThat(firstFrame.getFrameState()).isEqualTo("  1|1  |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("스페어 시 시각화한다.")
    @Test
    void spareVisualize() {
        firstFrame.bowl(valueOf(5)).bowl(valueOf(5));

        assertThat(firstFrame.getFrameState()).isEqualTo("  5|/  |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("스트라이크 시 시각화한다.")
    @Test
    void strikeVisualize() {
        firstFrame.bowl(MAX);

        assertThat(firstFrame.getFrameState()).isEqualTo("   X   |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("거터 스페어 시 시각화한다.")
    @Test
    void gutterSpareVisualize() {
        firstFrame.bowl(GUTTER).bowl(MAX);

        assertThat(firstFrame.getFrameState()).isEqualTo("  -|/  |       |       |       |       |       |       |       |       |       |");
    }
}
