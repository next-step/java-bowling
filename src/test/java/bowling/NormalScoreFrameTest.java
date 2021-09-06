package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalScoreFrameTest {
    @Test
    @DisplayName("다음 NormalScoreFrame으로 진행하는 케이스 테스트")
    void bowlTest1() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1), FrameResult.MISS);

        ScoreFrame nextScoreFrame = normalScoreFrame.process(10);

        assertThat(nextScoreFrame).isInstanceOf(NormalScoreFrame.class);

        ScoreFrame nextNextScoreFrame = nextScoreFrame.process(10);

        assertThat(nextNextScoreFrame).isInstanceOf(NormalScoreFrame.class);
    }

    @Test
    @DisplayName("다음 FinalScoreFrame으로 진행하는 케이스 테스트")
    void bowlTest2() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(9), FrameResult.MISS);
        ScoreFrame nextScoreFrame = normalScoreFrame.process(10);

        assertThat(nextScoreFrame).isInstanceOf(FinalScoreFrame.class);

        NormalScoreFrame normalScoreFrame2 = new NormalScoreFrame(new Turn(9), FrameResult.MISS);
        ScoreFrame nextScoreFrame2 = normalScoreFrame2.process(9).process(1);

        assertThat(nextScoreFrame2).isInstanceOf(FinalScoreFrame.class);

        NormalScoreFrame normalScoreFrame3 = new NormalScoreFrame(new Turn(9), FrameResult.MISS);
        ScoreFrame nextScoreFrame3 = normalScoreFrame3.process(9).process(0);

        assertThat(nextScoreFrame3).isInstanceOf(FinalScoreFrame.class);
    }

    @Test
    @DisplayName("다음 ScoreFrame으로 진행하지 않는 케이스 테스트")
    void bowlTest3() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1), FrameResult.MISS);
        ScoreFrame nextScoreFrame = normalScoreFrame.process(9);

        assertThat(nextScoreFrame).isSameAs(normalScoreFrame);
    }
}
