package bowling;

import bowling.domain.NormalFrame;
import bowling.domain.Score;
import bowling.domain.SecondScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingScoreTest {

    private NormalFrame normalFrame = NormalFrame.init();

    @DisplayName("클래스로 넣을 시 점수가 같은지 테스트")
    @Test
    public void testBowlingScore() {
        NormalFrame normalFrame = NormalFrame.of(Score.SCORE1);
        assertThat(normalFrame.getFirstScore().getScore()).isEqualTo("1");
        normalFrame.secondScore(SecondScore.SCORE3);
        assertThat(normalFrame.getSecondScore().getScore()).isEqualTo("3");
    }

    @DisplayName("첫번재점수 세팅 테스트")
    @Test
    public void testFirstScore() {
        normalFrame.firstScore(3);
        assertThat(normalFrame.getFirstScore().getScore()).isEqualTo("3");
    }

    @DisplayName("두번째점수 스페어로 나오는지 테스트")
    @Test
    public void testSecondScoreSpare() {
        normalFrame.firstScore(3);
        normalFrame.secondScore(7);
        assertThat(normalFrame.getSecondScore().getScore()).isEqualTo("/");
    }
}
