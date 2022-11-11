package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    void equal() {
        Score score = new Score(10, 2);
        assertThat(score).isEqualTo(new Score(10, 2));
    }
}
