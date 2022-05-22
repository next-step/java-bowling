package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void score10ShouldBeDone() {
        Score score = new Score(10);
        assertThat(score.done()).isTrue();
    }

    @Test
    void scoreLowerThan9ShouldNotDone() {
        Score score = new Score(1);
        assertThat(score.done()).isFalse();
    }


    @Test
    void printGutterWithDash() {
        assertThat(Score.payload(Optional.of(new Score(0, 0)))).isEqualTo(String.format("%-4s", "-|-"));
        assertThat(Score.payload(Optional.of(new Score(1, 0)))).isEqualTo(String.format("%-4s", "1|-"));
        assertThat(Score.payload(Optional.of(new Score(0, 1)))).isEqualTo(String.format("%-4s", "-|1"));
    }
}
