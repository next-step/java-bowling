package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinsTest {
    @Test
    @DisplayName("첫번째 시도에 핀이 10개 쓰러지면 스트라이크 처리한다")
    public void bowlStrikeWhenFirstBowlFallenTen() {
        Pins pins = new Pins();
        pins.bowl(10);

        assertThat(pins.isStrike()).isTrue();
    }

    @Test
    @DisplayName("첫번째 + 두번째 시도로 쓰러진 핀이 10개면 스페어 처리한다")
    public void sumOfFallenPinsTenReturnsSpare() {
        Pins pins = new Pins();
        pins.bowl(9);
        pins.bowl(1);

        assertThat(pins.isSpare()).isTrue();
    }
}
