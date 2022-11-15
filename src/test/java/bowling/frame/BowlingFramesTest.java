package bowling.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BowlingFramesTest {

    @Test
    void 볼링게임에서_프레임은_지정된_개수만큼_존재한다() {
        BowlingFrames frames = new BowlingFrames();
        assertThat(frames.getSize()).isEqualTo(BowlingFrames.LAST_FRAME);
    }

    @Test
    void 볼링게임에서_프레임의_마지막은_FinalFrame_이며_그_외의_프레임은_NormalFrame_이다() {
        BowlingFrames frames = new BowlingFrames();

        for (int i = 0; i < BowlingFrames.LAST_FRAME; i++) {
            assertThatFrame(frames, i);
        }

    }

    private void assertThatFrame(BowlingFrames frames, int i) {
        if (i == BowlingFrames.LAST_FRAME - 1) {
            assertTrue(frames.getFrame(i) instanceof FinalFrame);
            return;
        }

        assertTrue(frames.getFrame(i) instanceof NormalFrame);
    }

}
