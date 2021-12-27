package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("핀 생성 성공")
    void create(int pin) {
        assertThat(new Pins(pin)).isInstanceOf(Pins.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    @DisplayName("핀 수가 최소 수 보다 작을 경우")
    void minimumException(int pin) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Pins(pin));
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12, 13})
    @DisplayName("핀 수가 최소 수 보다 작을 경우")
    void maximumException(int pin) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Pins(pin));
    }

}
