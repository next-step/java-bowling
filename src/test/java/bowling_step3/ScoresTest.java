package bowling_step3;

import bowling_step3.domain.Scores;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoresTest {
    @Test
    public void getScoreWhenUnReady() throws Exception {
        assertThatThrownBy(() -> {
            new Scores(Arrays.asList(10, 2), 2).getScore();
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void getScoreWhenXX9() {
        Scores scores = new Scores(Arrays.asList(10, 10, 9), 0);
        assertThat(scores.getScore()).isEqualTo(29);
    }

    @Test
    public void getScoreWhenX81() {
        Scores scores = new Scores(Arrays.asList(10, 8, 1), 0);
        assertThat(scores.getScore()).isEqualTo(19);
    }

    @Test
    public void getScoreWhenSpare8() throws Exception {
        Scores scores = new Scores(Arrays.asList(10, 8), 0);
        assertThat(scores.getScore()).isEqualTo(18);
    }
}


