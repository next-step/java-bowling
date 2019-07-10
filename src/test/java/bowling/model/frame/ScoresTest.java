package bowling.model.frame;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {

    @Test
    void createScores_success() {
        // given
        Pins first = Pins.valueOf(1);
        Pins second = Pins.valueOf(2);

        // when
        Scores scores = Scores.of(first, second);

        // then
        assertThat(scores.isFinished()).isTrue();
    }

}
