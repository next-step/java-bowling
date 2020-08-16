package bowling.domain.state;

import bowling.domian.frame.Score;
import bowling.domian.state.Pins;
import bowling.domian.state.finished.Spare;
import bowling.domian.state.exception.BowlFinishedException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SpareTest {
    @Test
    @DisplayName("투구 불가 확인")
    public void isFinished() {
        Spare spare = new Spare(Pins.bowl(3), Pins.bowl(7));

        assertThat(spare.isFinished()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10})
    @DisplayName("투구 시 무조건 예외 발생")
    public void bowlThrowException(int falledPinsCount) {
        Spare spare = new Spare(Pins.bowl(3), Pins.bowl(7));

        assertThatExceptionOfType(BowlFinishedException.class).isThrownBy(
                () -> spare.bowl(falledPinsCount)
        );
    }

    @Test
    @DisplayName("점수 계산 가능 확인")
    public void canGetScore() {
        Spare spare = new Spare(Pins.bowl(3), Pins.bowl(7));

        assertThat(spare.canGetScore()).isTrue();
    }

    @Test
    @DisplayName("점수 계산 테스트")
    public void getScore() {
        Spare spare = new Spare(Pins.bowl(3), Pins.bowl(7));

        assertThat(spare.getScore().getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("점수 추가 계산 테스트")
    public void calculateAdditionalScore() {
        Spare spare = new Spare(Pins.bowl(3), Pins.bowl(7));

        Score score = Score.strike();

        assertThat(spare.calculateAdditional(score).getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("점수 출력 형식 확인")
    public void getResult() {
        Spare spare = new Spare(Pins.bowl(3), Pins.bowl(7));

        assertThat(spare.getDesc()).isEqualTo("3|/");
    }
}
