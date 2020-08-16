package bowling.domain.state;

import bowling.domian.frame.exception.InvalidScoreCalculateException;
import bowling.domian.state.*;
import bowling.domian.state.exception.InvalidPinCountException;
import bowling.domian.state.finished.Strike;
import bowling.domian.state.running.FirstBowl;
import bowling.domian.state.running.Ready;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ReadyTest {

    @Test
    @DisplayName("투구 가능 여부 확인")
    public void isFinished() {
        Ready ready = new Ready();

        assertThat(ready.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Strike 시 객체 정상 반환 확인")
    public void bowlResultStrike() {
        Ready ready = new Ready();

        State state = ready.bowl(10);

        assertThat(state instanceof Strike).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 9})
    @DisplayName("투구 결과가 10 미만인 경우 FirstBowl 객체 반환")
    public void bowlResultFirstBowl(int falledPinsCount) {
        Ready ready = new Ready();

        State state = ready.bowl(falledPinsCount);

        assertThat(state instanceof FirstBowl).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("유효하지 않은 투구 입력값일 경우 Exception 발생")
    public void invalidFalledPinsCount(int falledPinsCount) {
        Ready ready = new Ready();

        assertThatExceptionOfType(InvalidPinCountException.class).isThrownBy(
                () -> ready.bowl(falledPinsCount)
        );
    }

    @Test
    @DisplayName("점수 계산 가능 확인")
    public void canGetScore() {
        Ready ready = new Ready();

        assertThat(ready.canGetScore()).isFalse();
    }

    @Test
    @DisplayName("점수 계산 예외 확인")
    public void getScoreThrowException() {
        Ready ready = new Ready();

        assertThatExceptionOfType(InvalidScoreCalculateException.class).isThrownBy(
                () -> ready.getScore()
        );
    }
}
