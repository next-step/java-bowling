package bowling.domain.score;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreTest {

    @DisplayName("0~300 점 사이의 점수 생성 가능")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 100, 150, 200, 299, 300})
    public void validateScoreRange_success(int count) throws Exception {
        new Score(count, 0);
    }

    @DisplayName("0~300 범위 밖의 점수 생성시 exception")
    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 301, 400})
    public void validateScoreRange_fail(int count) throws Exception {
        assertThatThrownBy(
                () -> new Score(count, 0)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("0~2회 사이만 점수 합산이 가능하다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void validateAddCount_success(int addCount) throws Exception {
        new Score(2, addCount);
    }

    @DisplayName("0~2회 사이만 점수 합산이 가능하다")
    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 3, 10})
    public void validateAddCount_fail(int addCount) throws Exception {
        assertThatThrownBy(
                () -> new Score(2, addCount)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("점수를 더해준다")
    @Test
    public void addScore_success() throws Exception {
        //given
        Score current = new Score(10, 1);
        Score added = new Score(5, 1);

        //when
        current = current.addScore(added);

        //then
        assertThat(current.getScore()).isEqualTo(15);
    }

    @DisplayName("점수를 더해준다")
    @Test
    public void addScore_success2() throws Exception {
        //given
        Score score = new Score(10, 2);
        Score compare = new Score(15, 1);

        //when
        score = score.addScore(new Score(5));

        //then
        assertTrue(score.equals(compare));
    }

    @DisplayName("다음 점수를 합산 할수 있는지 판단한다")
    @Test
    public void canAddNextScore_success() throws Exception {
        //given
        Score calc1 = new Score(2, 0);
        Score calc2 = new Score(2, 2);

        //then
        assertFalse(calc1.canAddNextScore());
        assertTrue(calc2.canAddNextScore());
    }

}
