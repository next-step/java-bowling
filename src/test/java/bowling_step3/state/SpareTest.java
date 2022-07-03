package bowling_step3.state;

import bowling_step3.domain.Scores;
import bowling_step3.domain.state.Spare;
import bowling_step3.domain.state.Status;
import bowling_step3.domain.state.Strike;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SpareTest {
    @Test
    public void createWhenIllegal() throws Exception {
        assertThatThrownBy(() -> {
            new Spare(new Scores(List.of(7, 2), 0));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void pitchThrErr() {
        assertThatThrownBy(() -> {
            new Spare(new Scores(List.of(8, 2), 0)).pitch(8);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void calculateAdditionalScore() throws Exception {
        Scores scores = new Scores(List.of(10, 2), 0);
        Status status = new Strike(scores);
        Spare spare = new Spare(new Scores(List.of(8, 2), 0));
        assertThat(spare.calculateAdditionalScore(status)).isEqualTo(20);
    }
}
