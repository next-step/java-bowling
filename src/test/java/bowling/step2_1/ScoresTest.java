package bowling.step2_1;

import bowling.step2_1.domain.score.ScoreType;
import bowling.step2_1.domain.score.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoresTest {

    @Test
    @DisplayName("투구 기록 테스트 ")
    void addPitchTest() {
        Scores scores = new Scores();
        scores.addScore(3,1);
        assertThat(scores.size()).isEqualTo(1);
        scores.addScore(5,1);
        assertThat(scores.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구에 따른 타입(표기) 테스트 - 스트라이크")
    void pitchTypeTest_strike() {
        Scores scores = new Scores();
        scores.addScore(10, 3);
        assertThat(scores.getScores().get(0).getScore()).isEqualTo(10);
        assertThat(scores.getScores().get(0).getScoreType()).isEqualTo(ScoreType.STRIKE);
    }

    @Test
    @DisplayName("투구에 따른 타입(표기) 테스트 - 스페어")
    void pitchTypeTest_spare() {
        Scores scores = new Scores();
        scores.addScore(8, 3);
        scores.addScore(2, 3);
        assertThat(scores.getScores().get(0).getScore()).isEqualTo(8);
        assertThat(scores.getScores().get(0).getScoreType()).isEqualTo(ScoreType.DEFAULT);
        assertThat(scores.getScores().get(1).getScore()).isEqualTo(2);
        assertThat(scores.getScores().get(1).getScoreType()).isEqualTo(ScoreType.SPARE);

        Scores finalScores = new Scores();
        finalScores.addScore(6, 10);
        finalScores.addScore(4, 10);
        finalScores.addScore(10, 10);
        assertThat(finalScores.getScores().get(0).getScore()).isEqualTo(6);
        assertThat(finalScores.getScores().get(0).getScoreType()).isEqualTo(ScoreType.DEFAULT);
        assertThat(finalScores.getScores().get(1).getScore()).isEqualTo(4);
        assertThat(finalScores.getScores().get(1).getScoreType()).isEqualTo(ScoreType.SPARE);
        assertThat(finalScores.getScores().get(2).getScore()).isEqualTo(10);
        assertThat(finalScores.getScores().get(2).getScoreType()).isEqualTo(ScoreType.STRIKE);
    }

    @Test
    @DisplayName("투구에 따른 타입(표기) 테스트 - 거터")
    void pitchTypeTest_gutter() {
        Scores scores = new Scores();
        scores.addScore(0,2);
        scores.addScore(0,2);
        assertThat(scores.getScores().get(0).getScore()).isEqualTo(0);
        assertThat(scores.getScores().get(0).getScoreType()).isEqualTo(ScoreType.GUTTER);
        assertThat(scores.getScores().get(1).getScore()).isEqualTo(0);
        assertThat(scores.getScores().get(1).getScoreType()).isEqualTo(ScoreType.GUTTER);
    }
}