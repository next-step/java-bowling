package bowling.frame.state;

import bowling.frame.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SpareTest {
    @Test
    public void createWhenillegal() throws Exception {
        assertThatThrownBy(() -> {
            new Spare(Pins.bowl(7), Pins.bowl(2));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void bowl() {
        assertThatThrownBy(() -> {
            new Spare(Pins.bowl(8), Pins.bowl(2)).bowl(8);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void cacluateAdditionalScore() throws Exception {
        Score score = new Score(10, 2);
        Spare spare = new Spare(Pins.bowl(8), Pins.bowl(2));
        assertThat(spare.calculateAdditionalScore(score)).isEqualTo(new Score(20, 0));
    }
}
