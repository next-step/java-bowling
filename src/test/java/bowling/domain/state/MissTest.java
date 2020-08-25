package bowling.domain.state;

import bowling.domian.frame.Score;
import bowling.domian.state.finished.Miss;
import bowling.domian.state.Pins;
import bowling.domian.state.exception.BowlFinishedException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MissTest {
    @Test
    @DisplayName("투구 불가능 확인")
    public void isBowlImpossible() {
        Miss miss = new Miss(Pins.bowl(1), Pins.bowl(3));

        assertThat(miss.isFinished()).isTrue();
    }

    @Test
    @DisplayName("투구 시도 시 exception 발생")
    public void bowlThrowException() {
        Miss miss = new Miss(Pins.bowl(1), Pins.bowl(3));

        assertThatExceptionOfType(BowlFinishedException.class).isThrownBy(
                () -> miss.bowl(4)
        );
    }

    @Test
    @DisplayName("점수 계산 가능 확인")
    public void canGetScore() {
        Miss miss = new Miss(Pins.bowl(1), Pins.bowl(3));

        assertThat(miss.canGetScore()).isTrue();
    }

    @Test
    @DisplayName("점수 계산 테스트")
    public void getScore() {
        Miss miss = new Miss(Pins.bowl(1), Pins.bowl(3));

        assertThat(miss.getScore().getScore()).isEqualTo(4);
    }

    @Test
    @DisplayName("점수 추가 계산 테스트")
    public void calculateAdditionalScore() {
        Miss miss = new Miss(Pins.bowl(1), Pins.bowl(3));
        Score score = Score.strike();

        assertThat(miss.calculateAdditional(score).getScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("점수 출력 형식 확인")
    public void getResult() {
        Miss miss = new Miss(Pins.bowl(1), Pins.bowl(3));

        assertThat(miss.getDesc()).isEqualTo("1|3");
    }
}
