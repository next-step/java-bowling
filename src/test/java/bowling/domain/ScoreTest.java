package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void score10ShouldBeDone() {
        Score score = new Score(10, Optional.empty());
        assertThat(score.done()).isTrue();
    }

    @Test
    void scoreLowerThan9ShouldNotDone() {
        Score score = new Score(1, Optional.empty());
        assertThat(score.done()).isFalse();
    }

    @Test
    void returnRemainingPin() {
        Score score = new Score(1, Optional.empty());
        assertThat(score.remainingPin()).isEqualTo(9);
    }
}
