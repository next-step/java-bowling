package bowling.domain.frame.state;

import bowling.domain.score.Score;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GutterTest {

    @DisplayName("gutter 상태에서는 투구 불가능하다")
    @Test
    public void bowl_fail() throws Exception {
        //given
        Gutter gutter = new Gutter();

        //then
        assertThatThrownBy(
                () -> gutter.bowl(0)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("gutter 상태는 완료 상태이다")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Gutter gutter = new Gutter();

        //then
        assertTrue(gutter.isFinish());
    }

    @DisplayName("Gutter 상태에서는 점수가 0점이다")
    @Test
    public void getScore_success() throws Exception {
        //given
        Gutter gutter = new Gutter();
        Score score = new Score(0);

        //then
        assertTrue(gutter.getCurrentCalculator().getScore().equals(score));
    }

    @DisplayName("Gutter 상태에서는 점수를 더해도 이전과 동일 하다")
    @Test
    public void getCalculateScore_success() throws Exception {
        //given
        Gutter gutter = new Gutter();
        Score score = new Score(10, 2);
        Score compare = new Score(10);

        //when
        score = gutter.getCalculateScore(score);

        //then
        assertTrue(score.equals(compare));
    }
}
