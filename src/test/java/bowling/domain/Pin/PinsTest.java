package bowling.domain.Pin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.Pin.PinSecondValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PinsTest {

    @Test
    @DisplayName("첫번째 Pin 정보를 저장할 수 있다.")
    void savePinTest() {

        // given
        Pin pin = Pin.of(3);

        // when
        Pins result = Pins.ofFirst(pin);

        // then
        assertThat(result).isInstanceOf(Pins.class);
    }

    @Test
    @DisplayName("두번째 핀 정보가 입력될 때 남은 핀보다 많이 들어오면 Exception이 발생해야 한다.")
    void pinSaveFailBySecondPinTest() {

        // given
        Pin first = Pin.of(8);
        Pin second = Pin.of(3);
        Pins pins = Pins.ofFirst(first);

        // when & then
        assertThatExceptionOfType(PinSecondValueException.class)
            .isThrownBy(() -> pins.ofSecond(second))
            .withMessageMatching("첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.");
    }

}