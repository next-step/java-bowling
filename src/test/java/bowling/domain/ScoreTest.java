package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void create() {
        Score score = new Score(10, 2);
        assertThat(score).isEqualTo(new Score(10, 2));
    }
}