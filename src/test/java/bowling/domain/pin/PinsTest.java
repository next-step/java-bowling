package bowling.domain.pin;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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

    @DisplayName("쓰러뜨린 핀의 합계가 유효한 범위인지 테스트")
    @Test
    void rollOverTen() {
        Pins pins = new Pins();

        assertThatThrownBy(() -> {
            pins.addPins(5);
            pins.addPins(6);

        }).isInstanceOf(IllegalArgumentException.class);
    }

}

