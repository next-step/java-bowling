package bowling.domain.pin;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {
    @DisplayName("10개의 핀을 가진 Pins 객체를 생성할 수 있다.")
    @Test
    void create() {
        Pins expect = Pins.of();

        Pins actual = Pins.of();

        assertThat(actual).isEqualTo(expect);
    }
}