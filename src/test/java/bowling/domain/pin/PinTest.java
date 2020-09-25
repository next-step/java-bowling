package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PinTest {

    @DisplayName("Pin 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void createTest(int number) {
        Pin pin = Pin.of(number);
        assertThat(pin).isEqualTo(Pin.of(number));
    }

    @DisplayName("Pin 생성 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void validatePinTest(int pin) {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.of(pin));
    }
}