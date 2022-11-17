package bowling.domain.score;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.scores.BonusScores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BonusScoresTest {

    private BonusScores bonusScores;

    @BeforeEach
    void setUp() {
        this.bonusScores = new BonusScores();
    }

    @Test
    void validateScore() {
        assertThatThrownBy(() -> bonusScores.validateScore()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void isNotEndScoreStrikeTrue() {
        TotalScore totalScore = TotalScore.defaultFrameTotalScore();
        totalScore.addRegularScore(Score.of(10));
        bonusScores.add(Score.of(10));
        assertThat(bonusScores.isNotEndScore(totalScore.regularScores())).isTrue();
    }

    @Test
    void isNotEndScoreStrikeFalse() {
        TotalScore totalScore = TotalScore.defaultFrameTotalScore();
        totalScore.addRegularScore(Score.of(10));
        bonusScores.add(Score.of(10));
        bonusScores.add(Score.of(10));
        assertThat(bonusScores.isNotEndScore(totalScore.regularScores())).isFalse();
    }

    @Test
    void isNotEndScoreSpareTrue() {
        TotalScore totalScore = TotalScore.defaultFrameTotalScore();
        totalScore.addRegularScore(Score.of(8));
        totalScore.addRegularScore(Score.of(2));
        assertThat(bonusScores.isNotEndScore(totalScore.regularScores())).isTrue();
    }

    @Test
    void isNotEndScoreSpareFalse() {
        TotalScore totalScore = TotalScore.defaultFrameTotalScore();
        totalScore.addRegularScore(Score.of(8));
        totalScore.addRegularScore(Score.of(2));
        bonusScores.add(Score.of(10));
        assertThat(bonusScores.isNotEndScore(totalScore.regularScores())).isFalse();
    }

    @Test
    void isNotEndScoreMissTrue() {
        TotalScore totalScore = TotalScore.defaultFrameTotalScore();
        totalScore.addRegularScore(Score.of(8));
        totalScore.addRegularScore(Score.of(1));
        assertThat(bonusScores.isNotEndScore(totalScore.regularScores())).isFalse();
    }

    @Test
    void isChanceMinusTwo() {
        assertThatThrownBy(() -> bonusScores.isChanceMinusTwo()).isInstanceOf(RuntimeException.class);
    }
}
