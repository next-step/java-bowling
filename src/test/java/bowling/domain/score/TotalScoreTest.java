package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.scores.DefaultFrameScores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalScoreTest {

    private TotalScore totalScore;

    @BeforeEach
    void setUp() {
        this.totalScore = TotalScore.defaultFrameTotalScore();
    }

    @Test
    void addScore() {
        this.totalScore.addRegularScore(Score.of(1));

        DefaultFrameScores expected = new DefaultFrameScores();
        expected.add(Score.of(1));

        assertThat(this.totalScore.regularScores()).isEqualTo(expected);
    }

    @Test
    void addBonusScore() {
        this.totalScore.addBonusScore(Score.of(2));

        assertThat(this.totalScore.sumTotalScore()).isEqualTo(2);
    }

    @Test
    void sumTotalScore() {
        totalScore.addBonusScore(Score.of(10));
        totalScore.addBonusScore(Score.of(6));
        totalScore.addBonusScore(Score.of(3));

        assertThat(totalScore.sumTotalScore()).isEqualTo(19);
    }
}
