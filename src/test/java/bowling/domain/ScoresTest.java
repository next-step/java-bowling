package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScoresTest {

    @Test
    void create() {
        Scores scores = new Scores(Score.of(1));

        assertThat(scores).isEqualTo(new Scores(Score.of(1)));
    }
}
