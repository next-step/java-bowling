package bowling.domain.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    private Score spare;

    @BeforeEach
    void setUp() {
        spare = new Score(10, 1);
    }

    @Test
    @DisplayName("Score Equality")
    void equality() {
        Score sample = new Score(14, 2);

        assertThat(sample).isEqualTo(new Score(14, 2));
        assertThat(sample).isNotEqualTo(new Score(14, 1));
    }

    @Test
    @DisplayName("보너스 점수 추가")
    void scoring() {
        Score newScore = spare.addScore(5);

        assertThat(newScore).isEqualTo(new Score(15, 0));
    }
}
