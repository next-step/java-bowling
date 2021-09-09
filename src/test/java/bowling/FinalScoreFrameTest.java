package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalScoreFrameTest {
    @Test
    @DisplayName("다음 프레임으로 넘어가지 않는 케이스 테스트")
    void bowlTest1() {
        FinalScoreFrame finalScoreFrame = new FinalScoreFrame(new Turn(10));

        ScoreFrame nextScoreFrame = finalScoreFrame.addScore(10);
        assertThat(nextScoreFrame).isSameAs(finalScoreFrame);

        ScoreFrame nextNextScoreFrame = nextScoreFrame.addScore(10);
        assertThat(nextNextScoreFrame).isSameAs(finalScoreFrame);
    }

    @Test
    @DisplayName("다음 프레임으로 넘어가는 케이스 테스트")
    void bowlTest2() {
        FinalScoreFrame finalScoreFrame = new FinalScoreFrame(new Turn(10));

        ScoreFrame nextScoreFrame = finalScoreFrame.addScore(10);
        assertThat(nextScoreFrame).isSameAs(finalScoreFrame);

        ScoreFrame nextNextScoreFrame = nextScoreFrame.addScore(10);
        assertThat(nextNextScoreFrame).isSameAs(finalScoreFrame);

        ScoreFrame nextNextNextScoreFrame = nextNextScoreFrame.addScore(10);
        assertThat(nextNextNextScoreFrame).isNotSameAs(finalScoreFrame);
    }
}
