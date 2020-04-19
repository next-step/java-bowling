package bowling.domain.score;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreCalculatorTest {

    @DisplayName("0~2회 사이만 점수 합산이 가능하다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void validate_success(int addCount) throws Exception {
        new ScoreCalculator(new Score(2), addCount);
    }

    @DisplayName("0~2회 사이만 점수 합산이 가능하다")
    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 3, 10})
    public void validate_fail(int addCount) throws Exception {
        assertThatThrownBy(
                () -> new ScoreCalculator(new Score(2), addCount)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("다음 점수를 합산 할수 있는지 판단한다")
    @Test
    public void canAddNextScore_success() throws Exception {
        //given
        Calculator calc1 = new ScoreCalculator(new Score(2), 0);
        Calculator calc2 = new ScoreCalculator(new Score(2), 2);

        //then
        assertFalse(calc1.canAddNextScore());
        assertTrue(calc2.canAddNextScore());
    }

    @Test
    public void sumScore_success() throws Exception {
        //given
        Calculator calculator = new ScoreCalculator(new Score(10), 2);
        Calculator compare = new ScoreCalculator(new Score(15), 1);

        //when
        calculator = calculator.sumScore(new Score(5));

        //then
        assertTrue(calculator.equals(compare));
    }
}
