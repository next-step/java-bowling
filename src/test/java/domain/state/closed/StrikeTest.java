package domain.state.closed;

import domain.Pins;
import domain.state.State;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.Pins.GUTTER_PINS;
import static domain.Pins.STRIKE_PINS;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StrikeTest {
    @ParameterizedTest
    @ValueSource(ints = {GUTTER_PINS, STRIKE_PINS})
    void 스트라이크_상태에서_다시_투구를_시도하면_예외가_발생한다(int fallenPins) {

        //given
        State state = new Strike();

        //when
        //then
        assertThatExceptionOfType(ClosedFrameException.class)
                .isThrownBy(() -> {
                    state.update(Pins.from(fallenPins));
                });
    }
}
