package com.jaeyeonling.bowling.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.jaeyeonling.bowling.frame.KnockdownPins.*;
import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private Frame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = Frame.first();
    }

    @DisplayName("거터 거터 시 시각화한다.")
    @Test
    void gutterGutterVisualize() {
        finalFrame.bowl(GUTTER).bowl(GUTTER);

        assertThat(finalFrame.visualize()).isEqualTo("  -|-  |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("거터 미스 시 시각화한다.")
    @Test
    void gutterMissVisualize() {
        finalFrame.bowl(GUTTER).bowl(valueOf(1));

        assertThat(finalFrame.visualize()).isEqualTo("  -|1  |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("미스 미스 시 시각화한다.")
    @Test
    void missMissVisualize() {
        finalFrame.bowl(valueOf(1)).bowl(valueOf(1));

        assertThat(finalFrame.visualize()).isEqualTo("  1|1  |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("스페어 시 시각화한다.")
    @Test
    void spareVisualize() {
        finalFrame.bowl(valueOf(5)).bowl(valueOf(5));

        assertThat(finalFrame.visualize()).isEqualTo("  5|/  |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("스트라이크 시 시각화한다.")
    @Test
    void strikeVisualize() {
        finalFrame.bowl(MAX);

        assertThat(finalFrame.visualize()).isEqualTo("   X   |       |       |       |       |       |       |       |       |       |");
    }

    @DisplayName("거터 스페어 시 시각화한다.")
    @Test
    void gutterSpareVisualize() {
        finalFrame.bowl(GUTTER).bowl(MAX);

        assertThat(finalFrame.visualize()).isEqualTo("  -|/  |       |       |       |       |       |       |       |       |       |");
    }
}
