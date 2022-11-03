package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreResultTest {

    @Test
    void shouldSumResult() {

        ScoreResult result = new ScoreResult();
        result.addScore(1);
        result.addScore(3);
        result.addScore(5);

        assertThat(result.sum()).containsExactly(1, 4, 9);
    }

}
