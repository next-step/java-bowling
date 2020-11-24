package bowling.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    private Score score;

    @Test
    @DisplayName("Strike 일 경우 Score 확인")
    void getScoreIsStrike() {
        score = Score.ofStrike();
        assertThat(score.getFellPins()).isEqualTo(10);
        assertThat(score.getLeftOpportunity()).isEqualTo(2);
    }

    @Test
    @DisplayName("Spare 일 경우 Score 확인")
    void getScoreIsSpare() {
        score = Score.ofSpare();
        assertThat(score.getFellPins()).isEqualTo(10);
        assertThat(score.getLeftOpportunity()).isEqualTo(1);
    }

    @Test
    @DisplayName("Miss 일 경우 Score 확인")
    void getScoreIsMiss() {
        score = Score.ofMiss(4);
        assertThat(score.getFellPins()).isEqualTo(4);
        assertThat(score.getLeftOpportunity()).isEqualTo(0);
    }

    @Test
    @DisplayName("Gutter 일 경우 Score 확인")
    void getScoreIsGutter() {
        score = Score.ofMiss(0);
        assertThat(score.getFellPins()).isEqualTo(0);
        assertThat(score.getLeftOpportunity()).isEqualTo(0);
    }

    @Test
    @DisplayName("이전 스코어에 현재 스코어 계산")
    void calculateScore() {
        Score previousScore = Score.of(45);
        Score score = Score.of(previousScore.getFellPins()).calculate(previousScore);
        score = Score.of(5).calculate(score);

        assertThat(score.getFellPins()).isEqualTo(45);
    }

}
