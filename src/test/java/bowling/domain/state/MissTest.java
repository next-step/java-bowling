package bowling.domain.state;

import bowling.domian.state.Miss;
import bowling.domian.state.exception.BowlFinishedException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MissTest {
    @Test
    @DisplayName("투구 불가능 확인")
    public void isBowlImpossible() {
        Miss miss = new Miss();

        assertThat(miss.isFinished()).isTrue();
    }

    @Test
    @DisplayName("투구 시도 시 exception 발생")
    public void bowlThrowException() {
        Miss miss = new Miss();

        assertThatExceptionOfType(BowlFinishedException.class).isThrownBy(
                () -> miss.bowl(4)
        );
    }
}
