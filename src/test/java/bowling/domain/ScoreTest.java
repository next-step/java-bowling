package bowling.domain;

import static bowling.domain.Score.DEFAULT_SCORE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void createTest() {
        Score score = new Score(DEFAULT_SCORE);

        assertThat(score).isNotNull();
    }



}
