package domain.state.closed;

import domain.Pins;
import domain.state.State;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static domain.state.closed.Miss.ALERT_CANNOT_BE_MISS;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MissTest {

    @ParameterizedTest
    @CsvSource({"0, 10", "1, 9", "10, 0"})
    void 첫_투구결과와_두번째_투구결과의_합이_10이상인_경우_예외가_발생한다(int firstPins, int secondPins) {

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Miss(Pins.from(firstPins), Pins.from(secondPins));
                }).withMessage(ALERT_CANNOT_BE_MISS);
    }


    @ParameterizedTest
    @CsvSource({"0, 9", "1, 8", "1, 0", "0, 0"})
    void 미쓰_상태에서_다시_투구하면_예외가_발생한다(int firstPins, int secondPins) {
        //given
        State state = new Miss(Pins.from(firstPins), Pins.from(secondPins));

        //when
        //then
        assertThatExceptionOfType(ClosedFrameException.class)
                .isThrownBy(() -> {
                    state.update(Pins.from(firstPins));
                });
    }
}
