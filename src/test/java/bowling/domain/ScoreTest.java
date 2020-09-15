package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    public void getScore() {
        Score score = new Score(10);
        assertThat(score.hit(9).hit(1).toInt()).isEqualTo(20);
    }
}
