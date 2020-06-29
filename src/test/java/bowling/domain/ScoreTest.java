package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("Score 클래스 테스트")
class ScoreTest {

    @Test
    void ofMiss() {
        int score = 8;
        Score actual = Score.ofMiss(score);

        assertThat(actual).isEqualTo(new Score(score, 0));
    }

    @Test
    void ofSpare() {
        Score actual = Score.ofSpare();

        assertThat(actual).isEqualTo(new Score(10, 1));
    }

    @Test
    void ofStrike() {
        Score actual = Score.ofStrike();

        assertThat(actual).isEqualTo(new Score(10, 2));
    }

    @Test
    void bowl() {
        int currentScore = 4;
        Score score = Score.ofSpare();

        Score actual = score.bowl(currentScore);

        assertThat(actual).isEqualTo(new Score(10+currentScore, 0));
    }

    @Test
    void getScore() {
        int countOfPins = 9;
        Score score = Score.ofMiss(countOfPins);

        int actual = score.getScore();

        assertThat(actual).isEqualTo(countOfPins);
    }

    @Test
    void getScore_fail() {
        Score score = Score.ofSpare();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(score::getScore);
    }

    @Test
    void canCalculateScore() {
        Score score = Score.ofSpare();

        boolean actual = score.canCalculateScore();

        assertThat(actual).isFalse();
    }

    @Test
    void createScore() {
        Score actual = Score.createScore(State.STRIKE, 10);
        assertThat(actual).isEqualTo(Score.ofStrike());
    }
}
