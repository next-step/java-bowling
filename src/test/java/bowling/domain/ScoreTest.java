package bowling.domain;

import bowling.exception.InvalidScoreValueException;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    @DisplayName("생성 테스트 - 0 보다 작은 경우")
    public void lessZero() {
        // given

        // when
        ThrowingCallable throwingCallable = () -> Score.from(-1);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(InvalidScoreValueException.class);
    }

    @Test
    @DisplayName("생성 테스트 - 300 보다 큰 경우")
    public void overThreeHundreds() {
        // given

        // when
        ThrowingCallable throwingCallable = () -> Score.from(301);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(InvalidScoreValueException.class);
    }

    @Test
    @DisplayName("noScore 테스트")
    public void noScore() {
        // given
        Score noScore = Score.noScore();
        String expected = "";

        // when
        String score = noScore.score();

        // then
        assertThat(score).isEqualTo(expected);
        assertThat(noScore.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("Score 테스트 - 점수 있음")
    public void score() {
        // given
        Score zero = Score.from(0);
        String expected = "0";

        // when
        String score = zero.score();

        // then
        assertThat(score).isEqualTo(expected);
        assertThat(zero.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("plus 테스트")
    public void plus() {
        // given
        Score noScore = Score.noScore();
        Score score1 = Score.from(1);
        Score score2 = Score.from(10);

        // when
        Score result1 = noScore.plus(score1);
        Score result2 = score1.plus(score2);

        // then
        assertThat(result1).isEqualTo(Score.noScore());
        assertThat(result2).isEqualTo(Score.from(11));
    }

}