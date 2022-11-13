package bowling.domain.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalScoreTest {

    private TotalScore totalScore;

    @BeforeEach
    void setUp() {
        this.totalScore = TotalScore.defaultFrameTotalScore();
    }

    @Test
    void create() {
    }

    @Test
    void addScore() {
        this.totalScore.addRegularScore(Score.of(1));
    }

    @Test
    void addBonusScore() {
        this.totalScore.addBonusScore(Score.of(2));
    }
}
