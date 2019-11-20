package bowling;

import bowling.domain.FrameSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameSetTest {

    @Test
    void playTest() {
        FrameSet firstFrameSet = new FrameSet(1);
        FrameSet lastFrame = firstFrameSet
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10); // Strike

        assertThat(lastFrame.isEnd()).isTrue();

        FrameSet lastFrame2 = firstFrameSet
                .play(10) // Strike
                .play(8) // Hit
                .play(2) // Spare
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10) // Strike
                .play(10); // Strike

        assertThat(lastFrame2.isEnd()).isTrue();
    }
}
