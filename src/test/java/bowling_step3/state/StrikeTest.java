package bowling_step3.state;

import bowling_step3.domain.Scores;
import bowling_step3.domain.state.Strike;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {
    @Test
    public void pitch() {
        assertThatThrownBy(() -> {
            new Strike().pitch(8);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void strike_additionalTwo() {
        Scores scores = new Scores(List.of(2, 3), 0);
        Strike strike = new Strike();
        int score = strike.calculateAdditionalScore(scores);
        assertThat(score).isEqualTo(15);
    }

    @Test
    public void strike_strikeWithDoubled() throws Exception {
        Scores scores = new Scores(List.of(10), 0);
        Strike strike = new Strike();
        int score = strike.calculateAdditionalScore(scores);
        assertThat(score).isEqualTo(30);
    }
}