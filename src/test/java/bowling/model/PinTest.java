package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PinTest {

    @DisplayName("핀이 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void createPin(int input) {
        Pin pin = Pin.of(input);
        assertThat(pin).isEqualTo(Pin.of(input));
    }

    @DisplayName("핀의 개수가 0~10 사이가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void validatePin(int input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Pin.of(input);
                });
    }

}
