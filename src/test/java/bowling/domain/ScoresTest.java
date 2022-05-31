package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = new Scores();
    }

    @Test
    void createTest() {
        assertThat(scores.first()).isNull();
        assertThat(scores.second()).isNull();
    }

}
