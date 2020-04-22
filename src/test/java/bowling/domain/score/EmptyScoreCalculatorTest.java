package bowling.domain.score;

import bowling.domain.pitch.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyScoreCalculatorTest {
    private EmptyScoreCalculator emptyScore;

    @BeforeEach
    void setUp() {
        emptyScore = new EmptyScoreCalculator();
    }

    @DisplayName("Strike Score 를 생성한다")
    @Test
    void createStrikeScore() {
        Pitch strikePitch = new Pitch(10);
        assertThat(emptyScore.add(strikePitch))
                .isExactlyInstanceOf(StrikeScoreCalculator.class);
    }

    @DisplayName("First Score 를 생성한다.")
    @Test
    void createFirstScore() {
        Pitch pitch = new Pitch(3);
        assertThat(emptyScore.add(pitch))
                .isExactlyInstanceOf(FirstScoreCalculator.class);
    }
}
