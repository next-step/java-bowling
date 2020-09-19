package bowling;

import bowling.domain.NormalFrame;
import bowling.domain.Score;
import bowling.domain.SecondScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @DisplayName("첫번째 점수가 잘 들어가는지 테스트")
    @Test
    public void normalFrameTest() {
        NormalFrame normalFrame = NormalFrame.init();
        normalFrame.hit(3,5);
        assertThat(normalFrame.getFirstScore()).isEqualTo(Score.SCORE3);
    }

    @DisplayName("첫번째 점수가 잘 들어가는지 테스트")
    @Test
    public void normalSecondTest() {
        NormalFrame normalFrame = NormalFrame.init();
        normalFrame.hit(3,5);
        normalFrame.hit(5,5);
        assertThat(normalFrame.getFirstScore()).isEqualTo(Score.SCORE3);
        assertThat(normalFrame.getSecondScore()).isEqualTo(SecondScore.SCORE5);
    }
}
