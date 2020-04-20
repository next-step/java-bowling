package bowling.domain.score;

import bowling.domain.pitch.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstScoreTest {
    private FirstScore firstScore;

    @BeforeEach
    void setUp() {
        firstScore = new FirstScore(1);
    }

    @DisplayName("스페어 처리하면 Spare Score 를 생성한다.")
    @Test
    void bowlSpare() {
        Pitch spare = (new Pitch(1)).next(9);
        Score score = firstScore.add(spare);
        assertThat(score).isExactlyInstanceOf(SpareScore.class);
        assertThat(score.calculateScore().get()).isEqualTo(10);
    }

    @DisplayName("스페어 처리 못하면 Finished Score를 생성한다.")
    @Test
    void bowlNormal() {
        Pitch normal = new Pitch(8);
        Score score = firstScore.add(normal);
        assertThat(score).isExactlyInstanceOf(FinishedScore.class);
        assertThat(score.calculateScore().get()).isEqualTo(9);
    }
}
