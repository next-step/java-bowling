package bowling.domain.frame.state;

import bowling.domain.score.Score;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrikeTest {

    @DisplayName("스트라이크 상태는 공을 굴릴수 없다")
    @Test
    public void bowl_fail() throws Exception {
        //given
        Strike strike = new Strike();

        //then
        assertThatThrownBy(
                () -> strike.bowl(1)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("스트라이크 상태는 마무리 상태이다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Strike strike = new Strike();

        //then
        assertTrue(strike.isFinish());
    }

    @DisplayName("Strike의 점수는 10점이다")
    @Test
    public void getCurrentScore_success() throws Exception {
        //given
        Strike strike = new Strike();
        Score ten = new Score(10);

        //then
        assertTrue(strike.getCurrentScore().equals(ten));
    }

    @DisplayName("이전 점수에 합한 점수를 반환해 준다")
    @Test
    public void getCalculateScore_success() throws Exception {
        //given
        Strike strike = new Strike();
        Score score = new Score(10, 2);
        Score compare = new Score(20);

        //when
        Score result = strike.getCalculateScore(score);

        //then
        assertTrue(result.equals(compare));
    }
}
