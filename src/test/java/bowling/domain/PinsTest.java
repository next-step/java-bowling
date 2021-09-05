package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    @DisplayName("핀을 생성시에는 쓰러뜨린 핀들은 없다")
    @Test
    void pinsCreateTest() {
        // Given
        Pins pins = Pins.of();
        // When && Then
        assertThat(pins.numberOfPinDowns()).isEqualTo(0);
    }

    @DisplayName("핀을 쓰러뜨린 개수만큼 리턴 된다")
    @Test
    void whenDownThePinsReturnDownsSize() {
        // Given
        Pins pins = Pins.of();
        assertThat(pins.roll(10)).isEqualTo(10);
    }
}
