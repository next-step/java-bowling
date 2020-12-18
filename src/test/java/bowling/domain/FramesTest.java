package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FramesTest {
    @Test
    @DisplayName("프레임이 모두 끝난 경우 isEnd() true")
    public void bowlingGameTest() {
        Frames frames = Frames.init();

        for (int i = 1; i <= Frames.MAX_FRAME_SIZE + 2; i++) {
            frames.setKnockDownPins(KnockDownPins.valueOf(10));
        }
        assertThat(frames.isEnd()).isTrue();
    }
}
