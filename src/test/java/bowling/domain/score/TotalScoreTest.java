package bowling.domain.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalScoreTest {

    private TotalScore totalScore;

    @BeforeEach
    void setUp() {
        this.totalScore = new TotalScore();
    }

    @Test
    void create() {
    }

    @Test
    void addScore() {
        this.totalScore.addScore(Score.of(1));
    }

    @Test
    void addBonusScore() {
        this.totalScore.addBonusScore(Score.of(2));
    }
}
