package bowling.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    @Test
    public void getScoreWhenUnReady() throws Exception {
        assertThatThrownBy(() -> {
            new Score(10, 2).getScore();
        }).isInstanceOf(CannotCalculateException.class);
    }

    @Test
    public void getScoreWhenXX9() {
        Score score = new Score(10, 2);
        assertThat(score.bowl(10).bowl(9).getScore()).isEqualTo(29);
    }

    @Test
    public void getScoreWhenX81() {
        Score score = new Score(10, 2);
        assertThat(score.bowl(8).bowl(1).getScore()).isEqualTo(19);
    }

    @Test
    public void getScoreWhenSpare8() throws Exception {
        Score score = new Score(10, 1);
        assertThat(score.bowl(8).getScore()).isEqualTo(18);
    }
}
