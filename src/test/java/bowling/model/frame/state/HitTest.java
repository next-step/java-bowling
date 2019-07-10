package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    @Test
    void createState() {
        // given
        Pins pins = Pins.valueOf(1);

        // when
        State result = Hit.valueOf(pins);

        // then
        assertThat(result).isInstanceOf(Hit.class);
    }

    @Test
    void printResult() {
        // given
        Pins pins = Pins.valueOf(1);

        // when
        String result = Hit.valueOf(pins).printResult();

        // then
        assertThat(result).isEqualTo("   1   ");
    }
}