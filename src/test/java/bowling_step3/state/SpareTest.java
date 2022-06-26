package bowling_step3.state;

import bowling_step3.domain.Scores;
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
        Spare spare = new Spare(new Scores(List.of(8, 2), 0));
        assertThat(spare.calculateAdditionalScore(scores)).isEqualTo(20);
    }
}
