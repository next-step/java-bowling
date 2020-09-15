package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    public void of() {
        Score score = Score.of(0, 10);
        int tryCount = 0;

        while (score.canNextSum()) {
            tryCount++;
            score = score.sum(score);
        }

        assertThat(tryCount).isEqualTo(10);
    }

    @Test
    public void canNextSum() {
        Score score = Score.of(0, 10);

        while (score.canNextSum()) {
            score = score.sum(Score.of(10,0));
        }

        assertThat(score.toInt()).isEqualTo(100);
    }


    @Test
    public void ofStrike() {
        Score score = Score.ofStrike(0);
        int tryCount = 0;

        while (score.canNextSum()) {
            tryCount++;
            score = score.sum(score);
        }

        assertThat(tryCount).isEqualTo(2);
    }

    @Test
    public void ofSpare() {
        Score score = Score.ofSpare(0);
        int tryCount = 0;

        while (score.canNextSum()) {
            tryCount++;
            score = score.sum(score);
        }

        assertThat(tryCount).isEqualTo(1);
    }

    @Test
    public void ofOpen() {
        Score score = Score.ofOpen(0);
        int tryCount = 0;

        while (score.canNextSum()) {
            tryCount++;
            score = score.sum(score);
        }

        assertThat(tryCount).isEqualTo(0);
    }
}
