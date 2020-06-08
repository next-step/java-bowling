package bowling.domain.frame;

import bowling.domain.rolling.State;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("Strike 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusStrike() {
        Score score = Score.calculateScore(null, State.STRIKE, 10);

        assertThat(score.isCalculateEnd()).isFalse();
    }

    @Test
    @DisplayName("Strke 이후 한번 더 투구한 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusStrikeAndRollingOneTime() {
        Score score1 = Score.calculateScore(null, State.STRIKE, 10);
        score1.calculate(State.MISS, 4);

        assertThat(score1.isCalculateEnd()).isFalse();
    }

    @Test
    @DisplayName("Strke 이후 두번 더 투구한 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusStrikeAndRollingTwoTimes() {
        Score score1 = Score.calculateScore(null, State.STRIKE, 10);
        score1.calculate(State.MISS, 4);
        score1.calculate(State.GUTTER, 6);

        assertThat(score1.isCalculateEnd()).isTrue();
    }

    @Test
    @DisplayName("spare 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusSpare() {
        Score score = Score.calculateScore(null, State.SPARE, 8);

        assertThat(score.isCalculateEnd()).isFalse();
    }

    @Test
    @DisplayName("Spare 이후 한번 더 투구한 상황에서 점수 계산 종료 확인")
    public void needNextRollingResult_whenStatusSpareAndRollingOneTime() {
        Score score1 = Score.calculateScore(null, State.SPARE, 4);
        score1.calculate(State.MISS, 4);

        assertThat(score1.isCalculateEnd()).isTrue();
    }

    @Test
    @DisplayName("miss 상황에서 점수 계산 종료 확인")
    public void calculateDone_whenStatusMiss() {
        Score score = Score.calculateScore(null, State.MISS, 8);

        assertThat(score.isCalculateEnd()).isTrue();
    }

    @Test
    @DisplayName("gutter 상황에서 점수 계산 종료 확인")
    public void calculateDone_whenStatusGutter() {
        Score score = Score.calculateScore(null, State.GUTTER, 8);

        assertThat(score.isCalculateEnd()).isTrue();
    }
}
