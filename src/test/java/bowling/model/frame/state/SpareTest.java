package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.MIN;
import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @DisplayName("결과 값의 프린트를 확인한다")
    @Test
    void printResult() {
        // given
        Pins first = Pins.valueOf(MIN + 1);

        // when
        String result = Spare.valueOf(first).printResult();

        // then
        assertThat(result).isEqualTo("1|/");
    }
}