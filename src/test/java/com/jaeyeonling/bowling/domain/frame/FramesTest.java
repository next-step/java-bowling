package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.frame.state.FinishedFrameStateException;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = FramesFactory.create();
    }

    @DisplayName("프레임들의 첫 프레임 값은 1이다.")
    @Test
    void firstFrameIndex() {
        final int currentFrameIndex = frames.getCurrentFrameIndex();

        assertThat(currentFrameIndex).isEqualTo(1);
    }

    @DisplayName("스트라이크 1번 당 프레임이 1씩 증가한다.")
    @Test
    void strikeFrameIndex() {
        for (int i = 1; i <= 10; i++) {
            final int currentFrameIndex = frames.getCurrentFrameIndex();
            frames.bowl(KnockdownPins.MAX);

            assertThat(currentFrameIndex).isEqualTo(i);
        }
    }

    @DisplayName("스트라이크를 제외하면 2번 당 프레임이 1씩 증가한다.")
    @Test
    void otherFrameIndex() {
        for (int i = 1; i <= 10; i++) {
            final int currentFrameIndex = frames.getCurrentFrameIndex();

            frames.bowl(KnockdownPins.MIN);
            frames.bowl(KnockdownPins.MIN);

            assertThat(currentFrameIndex).isEqualTo(i);
        }
    }

    @DisplayName("모든 프레임이 끝난 후 게임 시 예외처리한다.")
    @Test
    void throwFinishedFrameStateException() {
        // given
        for (int i = 1; i <= 10; i++) {
            frames.bowl(KnockdownPins.MIN);
            frames.bowl(KnockdownPins.MIN);
        }

        // when / then
        assertThatExceptionOfType(FinishedFrameStateException.class)
                .isThrownBy(() -> frames.bowl(KnockdownPins.MIN));
    }
}
