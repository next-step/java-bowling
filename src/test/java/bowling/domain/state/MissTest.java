package bowling.domain.state;

import bowling.domian.frame.Score;
import bowling.domian.state.Miss;
import bowling.domian.state.Pins;
import bowling.domian.state.exception.BowlFinishedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MissTest {
    private Miss miss;

    @Before
    public void setUp() {
        Miss miss = new Miss(Pins.bowl(1), Pins.bowl(3));
    }

    @Test
    @DisplayName("투구 불가능 확인")
    public void isBowlImpossible() {
        assertThat(miss.isFinished()).isTrue();
    }

    @Test
    @DisplayName("투구 시도 시 exception 발생")
    public void bowlThrowException() {
        assertThatExceptionOfType(BowlFinishedException.class).isThrownBy(
                () -> miss.bowl(4)
        );
    }

    @Test
    @DisplayName("점수 계산 테스트")
    public void getScore() {
        assertThat(miss.getScore().getScore()).isEqualTo(4);
    }

    @Test
    @DisplayName("점수 추가 계산 테스트")
    public void calculateAdditionalScore() {
        Score score = Score.strike();

        assertThat(miss.calculateAdditional(score).getScore()).isEqualTo(14);
    }
}
