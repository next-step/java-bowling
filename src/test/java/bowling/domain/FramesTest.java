package bowling.domain;

import bowling.domain.frames.FramesImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FramesTest {
    @Test
    public void createTest() {
        FramesImpl frames = FramesImpl.init();
        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("프레임이 모두 끝난 경우 isEnd() true")
    public void bowlingGameTest() {
        FramesImpl frames = FramesImpl.init();

        for (int i = 1; i <= FramesImpl.MAX_FRAME_SIZE + 2; i++) {
            frames.setKnockDownPins(KnockDownPins.valueOf(10));
        }
        assertThat(frames.isEnd()).isTrue();
    }
}
