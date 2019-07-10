package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.MAX;
import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {

    @Test
    void getScore_isZero() {
        // when
        boolean result = Gutter.isMatch(Pins.DOWN_ZERO);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void bowl_whenPinsTen_thenSpare() {
        // given
        Pins second = Pins.DOWN_ALL;

        // when
        State result = new Gutter().bowl(second);

        // then
        assertThat(result).isInstanceOf(Spare.class);
    }

    @Test
    void bowl_whenPinsLessThanMax_thenMiss() {
        // given
        Pins second = Pins.valueOf(MAX - 1);

        // when
        State result = new Gutter().bowl(second);

        // then
        assertThat(result).isInstanceOf(Miss.class);
    }

    @Test
    void printResult_success() {
        // when
        String print = new Gutter().printResult();

        // then
        assertThat(print).isEqualTo("   -   ");
    }
}