package bowling.domain.frame;

import bowling.domain.state.StateFormat;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("Strike 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusStrike() {
        Score score = Score.calculateScore(null, StateFormat.STRIKE, 10);

        assertThat(score.isCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("Strke 이후 한번 더 투구한 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusStrikeAndRollingOneTime() {
        Score score1 = Score.calculateScore(null, StateFormat.STRIKE, 10);
        score1.calculate(4);

        assertThat(score1.isCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("Strke 이후 두번 더 투구한 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusStrikeAndRollingTwoTimes() {
        Score score1 = Score.calculateScore(null, StateFormat.STRIKE, 10);
        Score score2 = score1.calculate(4);
        Score result = score2.calculate(6);

        assertThat(result.isCalculateDone()).isTrue();
    }

    @Test
    @DisplayName("spare 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusSpare() {
        Score score = Score.calculateScore(null, StateFormat.SPARE, 8);

        assertThat(score.isCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("Spare 이후 한번 더 투구한 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusSpareAndRollingOneTime() {
        Score score = Score.calculateScore(null, StateFormat.SPARE, 4);
        Score result = score.calculate(4);

        assertThat(result.isCalculateDone()).isTrue();
    }

    @Test
    @DisplayName("miss 상황에서 점수 계산 종료 확인")
    public void calculateDone_whenStatusMiss() {
        Score score = Score.calculateScore(null, StateFormat.MISS, 8);

        assertThat(score.isCalculateDone()).isTrue();
    }

    @Test
    @DisplayName("gutter 상황에서 점수 계산 종료 확인")
    public void calculateDone_whenStatusGutter() {
        Score score = Score.calculateScore(null, StateFormat.GUTTER, 8);

        assertThat(score.isCalculateDone()).isTrue();
    }
}
