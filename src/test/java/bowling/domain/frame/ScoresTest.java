package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = new Scores();
        scores.add(1);
        scores.add(2);
        scores.add(3);
    }

    @Test
    void size() {
        assertThat(scores.size()).isEqualTo(3);
    }

    @Test
    void first() {
        assertThat(scores.first()).isEqualTo(1);
    }

    @Test
    void add() {
        scores.add(4);
        assertThat(scores.size()).isEqualTo(4);
    }
}
