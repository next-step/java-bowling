package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpareTest {

    @DisplayName("첫 핀의 남은 개수가 0 이면 안된다")
    @Test
    public void validate_fail() throws Exception {
        //given
        assertThatThrownBy(
                () -> new Spare(new Pins(10), new Pins(2))
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("스페어 상태는 공을 굴릴수 없다")
    @Test
    public void bowl_fail() throws Exception {
        //given
        Strike strike = new Strike();

        //then
        assertThatThrownBy(
                () -> strike.bowl(1)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("스페어 상태는 마무리 상태이다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Strike strike = new Strike();

        //then
        assertTrue(strike.isFinish());
    }

    @DisplayName("spare 상태의 점수는 10점이다")
    @Test
    public void getScore_success() throws Exception {
        //given
        Strike strike = new Strike();
        Score ten = new Score(10);

        //then
        assertTrue(strike.getScore().equals(ten));
    }
}
