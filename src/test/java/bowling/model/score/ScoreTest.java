package bowling.model.score;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    public void add() {
        assertThat(Score.of(5).add(Score.UNKNOWN)).isEqualTo(Score.UNKNOWN);
        assertThat(Score.of(5).add(Score.of(10))).isEqualTo(Score.of(15));
    }
}
