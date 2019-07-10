package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    void printResult() {
        // given
        Pins first = Pins.valueOf(1);

        // when
        String result = Spare.valueOf(first).printResult();

        // then
        assertThat(result).isEqualTo("   1|/   ");
    }
}