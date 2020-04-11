package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {
    @DisplayName("10개의 핀을 가진 Pins 객체를 생성할 수 있다.")
    @Test
    void create() {
        PinGenerator pinGenerator = new PinGenerator();
        Pins expect = Pins.of(pinGenerator);

        Pins actual = Pins.of(pinGenerator);

        assertThat(actual).isEqualTo(expect);
    }
}