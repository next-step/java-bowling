package bowling.domain.frame;

import bowling.domain.rolling.State;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("strike 상황에서 다음 투구 결과값이 필요한지 확인")
    public void needNextRollingResult_whenStatusStrike() {
        Score score = new Score(State.STRIKE, 10);

        assertThat(score.isCalculateEnd()).isFalse();
    }

    @Test
    @DisplayName("spare 상황에서 다음 투구 결과값이 필요한지 확인")
    public void needNextRollingResult_whenStatusSpare() {
        Score score = new Score(State.SPARE, 8);

        assertThat(score.isCalculateEnd()).isFalse();
    }

    @Test
    @DisplayName("miss 상황에서 다음 투구 결과값이 필요한지 확인")
    public void calculateDone_whenStatusMiss() {
        Score score = new Score(State.MISS, 8);

        assertThat(score.isCalculateEnd()).isTrue();
    }

    @Test
    @DisplayName("gutter 상황에서 다음 투구 결과값이 필요한지 확인")
    public void calculateDone_whenStatusGutter() {
        Score score = new Score(State.GUTTER, 0);

        assertThat(score.isCalculateEnd()).isTrue();
    }
}
