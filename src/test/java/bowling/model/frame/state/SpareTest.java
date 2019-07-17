package bowling.model.frame.state;

import bowling.model.DownPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.DownPin.MIN;
import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @DisplayName("결과 값의 프린트를 확인한다")
    @Test
    void printResult() {
        // given
        DownPin first = DownPin.valueOf(MIN);

        // when
        String result = Spare.valueOf(first).printResult();

        // then
        assertThat(result).isEqualTo("-|/");
    }
}