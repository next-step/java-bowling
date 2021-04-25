package bowling.domain.pin;

import bowling.exception.InvalidPinCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("핀의 개수를 입력받아 핀을 생성한다.")
    void create(final int pinCount) {
        // given
        // when
        final Pin pin = new Pin(pinCount);

        // then
        assertThat(pin).isEqualTo(new Pin(pinCount));
    }

    @Test
    @DisplayName("기본 생성자로 생성시 10개의 핀이 생성된다.")
    void defaultConstructor() {
        // given
        final int defaultPinCount = 10;

        // when
        final Pin pin = new Pin();

        // then
        assertThat(pin).isEqualTo(new Pin(defaultPinCount));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("핀의 수는 0~10 이어야 한다.")
    void validate(final int invalidPinCount) {
        assertThatThrownBy(() -> new Pin(invalidPinCount))
                .isInstanceOf(InvalidPinCountException.class)
                .hasMessage(InvalidPinCountException.INVALID_PIN_COUNT);
    }
}
