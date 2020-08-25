package bowling.domain.state;

import bowling.domian.frame.Score;
import bowling.domian.state.finished.Strike;
import bowling.domian.state.exception.BowlFinishedException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StrikeTest {
    @Test
    @DisplayName("투구 불가 확인")
    public void isFinished() {
        Strike strike = new Strike();

        assertThat(strike.isFinished()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10})
    @DisplayName("투구 시 무조건 예외 발생")
    public void bowlThrowException(int falledPinsCount) {
        Strike strike = new Strike();

        assertThatExceptionOfType(BowlFinishedException.class).isThrownBy(
                () -> strike.bowl(falledPinsCount)
        );
    }

    @Test
    @DisplayName("점수 계산 가능 확인")
    public void canGetScore() {
        Strike strike = new Strike();

        assertThat(strike.canGetScore()).isTrue();
    }

    @Test
    @DisplayName("점수 계산 테스트")
    public void getScore() {
        Strike strike = new Strike();

        assertThat(strike.getScore().getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("점수 추가 계산 테스트")
    public void calculateAdditionalScore() {
        Strike strike = new Strike();

        Score score = Score.strike();

        assertThat(strike.calculateAdditional(score).getScore()).isEqualTo(20);
        assertThat(score.isCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("점수 출력 형식 확인")
    public void getResult() {
        Strike strike = new Strike();

        assertThat(strike.getDesc()).isEqualTo("X");
    }
}
