package bowling.domain;

import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    void create() {
        assertThat(new Score(new BallThrow(0)).toInt()).isEqualTo(0);
    }
}
