package seul.bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @Test
    public void addScore() {
        FrameResult frameResult = new FrameResult();

        frameResult.addScore(9, false);
        frameResult.addScore(1, true);

        assertThat(frameResult.getScore().getScore()).isEqualTo(10);
        assertThat(frameResult.getStatus()).isEqualTo(FrameStatus.SPARE);
    }

    @Test
    public void endScore() {
        FrameResult frameResult = new FrameResult();
        frameResult.addScore(10, true);

        frameResult.addBonusScore(1);
        frameResult.addBonusScore(2);

        assertThat(frameResult.endScore()).isTrue();
    }
}
