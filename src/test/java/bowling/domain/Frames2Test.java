package bowling.domain;

import bowling.domain.frames.Frames2;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Frames2Test {
    @Test
    @DisplayName("프레임이 모두 끝난 경우 isEnd() true")
    public void bowlingGameTest() {
        Frames2 frames = Frames2.init();

        for (int i = 1; i <= Frames2.MAX_FRAME_SIZE + 2; i++) {
            frames.setKnockDownPins(KnockDownPins.valueOf(10));
        }
        assertThat(frames.isEnd()).isTrue();
    }
}
