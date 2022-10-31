package bowling.domain.state;

import bowling.domain.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void description() {
        assertThat(new Strike().description()).isEqualTo("X");
    }

    @Test
    void tries() {
        assertThat(new Strike().tries()).isEqualTo(1);
    }

    @Test
    void getScore() {
        State state = new Strike();

        assertThat(state.getScore()).isEqualTo(new Score(10, 2));
    }

    @Test
    void addScore() {
        State state = new Strike();
        Score previousScore = new Score(10, 1);

        assertThat(state.addScore(previousScore)).isEqualTo(new Score(20, 0));
    }
}
