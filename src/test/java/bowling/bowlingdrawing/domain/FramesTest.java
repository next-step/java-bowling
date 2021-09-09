package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FramesTest {

    @Test
    @DisplayName("Frame 추가")
    void addFrame() {
        // given
        Frames frames = new Frames();
        // when
        frames.pitch(8);
        // then
        assertThat(frames.frames().get(0)).isEqualTo(Frame.of(8));
    }

    @Test
    @DisplayName("MaxFrame 초과 검증")
    void validateMaxNumberOfFrames() {
        // given
        Frames frames = new Frames();
        // when
        for (int i = 0; i < 10; i++) {
            frames.pitch(7);
            frames.pitch(2);
        }
        // then
        assertThatThrownBy(() -> frames.pitch(7))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("현재 진행 Frame Number 반환")
    void currentFrameNumber() {
        // given
        Frames frames = new Frames();
        // when
        for (int i = 0; i < 3; i++) {
            frames.pitch(7);
            frames.pitch(2);
        }
        // then
        assertThat(frames.currentFrameNumber()).isEqualTo(4);
    }
}
