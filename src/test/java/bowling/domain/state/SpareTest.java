package bowling.domain.state;

import bowling.domian.state.Spare;
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
        Spare spare = new Spare();

        assertThat(spare.isFinished()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10})
    @DisplayName("투구 시 무조건 예외 발생")
    public void bowlThrowException(int falledPinsCount) {
        Spare strike = new Spare();

        assertThatExceptionOfType(BowlFinishedException.class).isThrownBy(
                () -> strike.bowl(falledPinsCount)
        );
    }
}
