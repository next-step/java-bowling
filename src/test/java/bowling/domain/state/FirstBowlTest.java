package bowling.domain.state;

import bowling.domian.state.*;
import bowling.domian.state.exception.InvalidPinCountException;
import bowling.domian.state.finished.Miss;
import bowling.domian.state.finished.Spare;
import bowling.domian.state.running.FirstBowl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FirstBowlTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 7, 11})
    @DisplayName("두번째 투구 입력값 유효성 확인")
    public void invalidFalledPinsCount(int falledPinsCount) {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(4));

        assertThatExceptionOfType(InvalidPinCountException.class).isThrownBy(
                () -> firstBowl.bowl(falledPinsCount)
        );
    }

    @Test
    @DisplayName("투구 가능 확인")
    public void isBowlPossible() {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(4));

        assertThat(firstBowl.isFinished()).isFalse();
    }


    @ParameterizedTest
    @CsvSource(value = {"0,10", "5,5"})
    @DisplayName("두번째 투구 결과 Spare 확인")
    public void bowlResultSpare(int firstFalledPinsCount, int secondFalledPinsCount) {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(firstFalledPinsCount));

        State state = firstBowl.bowl(secondFalledPinsCount);

        assertThat(state instanceof Spare).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,4", "4,4"})
    @DisplayName("두번째 투구 결과 Miss 확인")
    public void bowlResultMiss(int firstFalledPinsCount, int secondFalledPinsCount) {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(firstFalledPinsCount));

        State state = firstBowl.bowl(secondFalledPinsCount);

        assertThat(state instanceof Miss).isTrue();
    }
}
