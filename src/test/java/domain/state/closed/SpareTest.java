package domain.state.closed;

import domain.Pins;
import domain.state.State;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SpareTest {
    @ParameterizedTest
    @CsvSource({"0, 9", "1, 8", "9, 0"})
    void 첫_투구결과와_두번째_투구결과의_합이_10이아니면_스페어_생성시_예외가_발생한다(int firstPins, int secondPins) {

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Spare(Pins.from(firstPins), Pins.from(secondPins));
                }).withMessage(Spare.ALERT_CANNOT_BE_SPARE);
    }

    @ParameterizedTest
    @CsvSource({"0, 10", "1, 9"})
    void 스페어_상태에서_다시_투구하면_예외가_발생한다(int firstPins, int secondPins) {
        //given
        State state = new Spare(Pins.from(firstPins), Pins.from(secondPins));

        //when
        //then
        assertThatExceptionOfType(ClosedFrameException.class)
                .isThrownBy(() -> {
                    state.update(Pins.from(firstPins));
                });
    }
}
