package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void createTest() {
        Score score = new Score();

        assertThat(score).isNotNull();
    }

}
