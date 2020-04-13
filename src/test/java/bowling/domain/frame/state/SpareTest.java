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
        Pins first = Pins.of().knockOver(new BowlCount(9));
        State spare = new Spare(first);

        assertThat(spare.toString()).isEqualTo("9|/");
    }
}