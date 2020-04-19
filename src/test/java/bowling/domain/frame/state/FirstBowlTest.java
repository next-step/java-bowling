package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.domain.score.Calculator;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreCalculator;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstBowlTest {


    @DisplayName("첫투구와 두번째 투구의 쓰러뜨린 핀의 개수가 10개가 넘으면 exception")
    @Test
    public void validate_fail() throws Exception {
        //given
        FirstBowl first = new FirstBowl(new Pins(3));

        //then
        assertThatThrownBy(
                () -> first.bowl(10)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("나머지 공을 모두 처리하면 spare 상태를 리턴 한다.")
    @Test
    public void blow_success_spare() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new Pins(4));

        //when
        State bowl = firstBowl.bowl(6);

        //then
        assertTrue(bowl instanceof Spare);
    }


    @DisplayName("공을 던져 핀이 남으면 miss 상태를 리턴한다")
    @Test
    public void blow_success_miss() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new Pins(4));

        //when
        State bowl = firstBowl.bowl(1);

        //then
        assertTrue(bowl instanceof Miss);
    }

    @DisplayName("공을 던져 남은 핀이 10개 이면 gutter 상태를 리턴한다")
    @Test
    public void blow_success_gutter() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new Pins(0));

        //when
        State bowl = firstBowl.bowl(0);

        //then
        assertTrue(bowl instanceof Gutter);
    }

    @DisplayName("첫 투구 상태는 완료 상태가 아니다")
    @Test
    public void isFinish_success() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new Pins(3));

        //then
        assertFalse(firstBowl.isFinish());
    }

    @DisplayName("FirstBowl 상태애서 점수 계산 확인")
    @Test
    public void getScore_success() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new Pins(3));
        Score compare = new Score(3);

        //when
        Calculator calculator = firstBowl.getCurrentCalculator();

        //then
        assertTrue(calculator.getScore().equals(compare));
    }

    @DisplayName("첫 투구한 점수를 더해주고 반환 한다")
    @Test
    public void getCalculateScore_success() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new Pins(5));
        Calculator calculator = new ScoreCalculator(new Score(10), 2);
        Score compare = new Score(15);

        //when
        calculator = firstBowl.getScoreCalculate(calculator);

        //then
        assertTrue(calculator.getScore().equals(compare));
    }
}
