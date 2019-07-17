package bowling.model.frame.state;

import bowling.model.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pin.MIN;
import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @DisplayName("결과 값의 프린트를 확인한다")
    @Test
    void printResult() {
        // given
        Pin first = Pin.valueOf(MIN + 1);

        // when
        String result = Spare.valueOf(first).printResult();

        // then
        assertThat(result).isEqualTo("1|/");
    }
}