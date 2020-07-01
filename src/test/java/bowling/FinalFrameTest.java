package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    Frames frames = new Frames();
    FinalFrame finalFrame = new FinalFrame();

    @DisplayName("일반 볼 테스트")
    @Test
    public void finalFrameTest() {
        int next = finalFrame.addScore(4, frames);
        next = finalFrame.addScore(4, frames);

        assertThat(finalFrame.isStrike()).isFalse();
        assertThat(finalFrame.isSpare()).isFalse();
        assertThat(finalFrame.isMiss()).isFalse();
        assertThat(next).isEqualTo(1);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void finalFrameStrikeTest() {
        int next = finalFrame.addScore(10, frames);

        assertThat(finalFrame.isStrike()).isTrue();
        assertThat(next).isEqualTo(0);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    public void finalFrameSpareTest() {
        int next = finalFrame.addScore(3, frames);
        next = finalFrame.addScore(7, frames);

        assertThat(finalFrame.isStrike()).isFalse();
        assertThat(finalFrame.isSpare()).isTrue();
        assertThat(next).isEqualTo(0);

    }

    @DisplayName("miss 테스트")
    @Test
    public void normalFrameMissTest() {
        int next = finalFrame.addScore(0, frames);

        assertThat(finalFrame.isStrike()).isFalse();
        assertThat(finalFrame.isSpare()).isFalse();
        assertThat(finalFrame.isMiss()).isTrue();
        assertThat(next).isEqualTo(0);
    }

}
