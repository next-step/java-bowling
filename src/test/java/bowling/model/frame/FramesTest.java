package bowling.model.frame;

import bowling.model.Result;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    public void getCurrentFrame_addResult_isEnded() {
        Frames frames = new Frames();
        assertThat(frames.getCurrentFrame().getIndex()).isEqualTo(1);
        frames.addResult(Result.MAX_PIN_COUNT);
        for (int i = 2; i < NormalFrame.MAX_FRAME_INDEX; i++) {
            assertThat(frames.isEnded()).isFalse();
            assertThat(frames.getCurrentFrame().getIndex()).isEqualTo(i);
            frames.addResult(Result.MAX_PIN_COUNT);
        }
        frames.addResult(Result.MAX_PIN_COUNT);
        frames.addResult(Result.MAX_PIN_COUNT);
        frames.addResult(Result.MAX_PIN_COUNT);
        assertThat(frames.isEnded()).isTrue();
    }
}
