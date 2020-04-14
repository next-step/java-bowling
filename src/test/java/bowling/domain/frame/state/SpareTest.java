package bowling.domain.frame.state;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {
    @DisplayName("SPARE의 점수를 갖고올 수 있다.")
    @Test
    void spare() {
        String expect = "9|/";
        Pins first = Pins.of().knockOver(new BowlCount(9));

        State actual = new Spare(first);

        assertThat(actual.toResult()).isEqualTo(expect);
    }
}