package bowling.frame.state;

import bowling.frame.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {
    @Test
    public void bowl() {
        assertThatThrownBy(() -> {
            new Strike().bowl(8);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void getScore() throws Exception {
        Score score = new Score(10, 2);
        Strike strike = new Strike();
        score = strike.calculateAdditionalScore(score);
        assertThat(score).isEqualTo(new Score(20, 1));

        strike = new Strike();
        score = strike.calculateAdditionalScore(score);
        assertThat(score).isEqualTo(new Score(30, 0));
    }
}
