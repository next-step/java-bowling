package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;


class PinsTest {

    @DisplayName("쓰러뜨린 핀 갯수 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void addTest(int pin) {
        // given
        Pins pins = new Pins();

        // when
        pins.addPins(pin);

        // then
        assertThat(pins.getTotalPins()).isEqualTo(pin);
    }

}

