package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NoneTest {

    @Test
    void bowl_pinsZero_thanGutter() {
        // given
        Pins pins = Pins.DOWN_ZERO;

        // when
        State result = new None().bowl(pins);

        // then
        assertThat(result).isInstanceOf(Gutter.class);
    }

    @Test
    void bowl_pinsMax_thanStrike() {
        // given
        Pins pins = Pins.DOWN_ALL;

        // when
        State result = new None().bowl(pins);

        // then
        assertThat(result).isInstanceOf(Strike.class);
    }

    @Test
    void printResult() {
        // when
        String result = new None().printResult();

        // then
        assertThat(result).isEqualTo("      ");
    }
}