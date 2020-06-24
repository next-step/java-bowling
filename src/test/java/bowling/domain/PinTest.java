package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("Pin 클래스 테스트")
public class PinTest {

    @DisplayName("Pin 객체를 생성할 수 있다.")
    @Test
    void create() {
        int fallenPin = 10;
        Pin pin = new Pin(fallenPin);

        assertThat(pin).isEqualTo(new Pin(fallenPin));
    }

    @DisplayName("Pin은 0보다 작거나 10보다 크면 예외를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void invalid_pin(int fallenPin) {
        assertThatExceptionOfType(IncorrectPinException.class)
                .isThrownBy(() -> new Pin(fallenPin))
                .withMessage(IncorrectPinException.MESSAGE);
    }
}
