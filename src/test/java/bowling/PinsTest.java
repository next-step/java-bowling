package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinsTest {
    @Test
    @DisplayName("첫번째 시도에 핀이 10개 쓰러지면 스트라이크다")
    public void bowlStrikeWhenFirstBowlFallenTen() {
        Pins pins = new Pins();
        pins.bowl(10);
        assertThat(pins.isStrike()).isEqualTo(true);
    }
}
