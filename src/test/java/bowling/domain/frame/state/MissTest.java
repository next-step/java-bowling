package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.domain.score.Calculator;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreCalculator;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MissTest {

    @DisplayName("miss 상태는 공을 굴릴수 없다")
    @Test
    public void bowl_fail() throws Exception {
        //given
        Miss miss = new Miss(new Pins(1), new Pins(2));

        //then
        assertThatThrownBy(
                () -> miss.bowl(1)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("miss 상태는 마무리 상태이다")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Miss miss = new Miss(new Pins(1), new Pins(2));

        //then
        assertTrue(miss.isFinish());
    }

    @DisplayName("이전 프레임에서 점수를 1회 더해준다(이전이 spare 일때")
    @Test
    public void getCalculateScore_success_once() throws Exception {
        //given
        Miss miss = new Miss(new Pins(1), new Pins(2));
        Calculator calculator = new ScoreCalculator(new Score(10), 1);
        Score compare = new Score(11);

        //when
        Calculator result = miss.getScoreCalculate(calculator);

        //then
        assertTrue(result.getScore().equals(compare));
    }

    @DisplayName("이전 프레임에서 점수를 2회 더해준다(이전이 strike 일때")
    @Test
    public void getCalculateScore_success_twice() throws Exception {
        //given
        Miss miss = new Miss(new Pins(1), new Pins(2));
        Calculator calculator = new ScoreCalculator(new Score(10), 2);
        Score compare = new Score(13);

        //when
        Calculator result = miss.getScoreCalculate(calculator);

        //then
        assertTrue(result.getScore().equals(compare));
    }
}
