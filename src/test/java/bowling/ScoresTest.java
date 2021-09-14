package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {
    @Test
    @DisplayName("점수의 합이 주어진 숫자보다 높은지 확인")
    void isTotalHigherOrEqualThanTest() {
        Scores scores = new Scores();

        scores.add(new Score(10));
        scores.add(new Score(10));

        assertThat(scores.isTotalScoreHigherOrEqualThan(20)).isTrue();
    }

    @Test
    @DisplayName("두번째 점수 이후인지 확인")
    void isAfterSecondScoreTest() {
        Scores scores = new Scores();

        scores.add(new Score(10));
        scores.add(new Score(10));
        scores.add(new Score(10));

        assertThat(scores.isAfterSecondScore()).isTrue();
    }

    @Test
    @DisplayName("두번째 점수인지 확인")
    void isSecondScoreTest() {
        Scores scores = new Scores();

        scores.add(new Score(10));
        scores.add(new Score(10));

        assertThat(scores.isSecondScore()).isTrue();
    }

    @Test
    @DisplayName("n 개의 점수 합 계산")
    void sumTest() {
        Scores scores = new Scores();

        scores.add(new Score(2));
        scores.add(new Score(8));

        assertThat(scores.sum(2)).isEqualTo(new Score(10));
    }

    @Test
    @DisplayName("점수의 갯수 확인")
    void getSizeTest() {
        Scores scores = new Scores();

        scores.add(new Score(2));

        assertThat(scores.getSize()).isEqualTo(1);
    }
}
