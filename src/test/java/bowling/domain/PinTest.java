package bowling.domain;

import bowling.exception.PinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class PinTest {
    @ParameterizedTest
    @DisplayName("count 가 0부터 10의 숫자가 아니면, BadCountOfPinsException 이 발생한다.")
    @ValueSource(ints = {-1, 11})
    void constructor(int count) {
        assertThatExceptionOfType(PinException.class)
                .isThrownBy(() -> Pin.of(count));
    }

    @ParameterizedTest
    @DisplayName("count 가 같다면, of 가 리턴하는 Pin 도 같아야 한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void of(int count) {
        assertThat(Pin.of(count))
                .isEqualTo(Pin.of(count));
    }

    @ParameterizedTest
    @DisplayName("exportPinDto 테스트")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void exportPinDto(int count) {
        assertThat(Pin.of(count).exportPinDto().getCountOfPins())
                .isEqualTo(count);
    }

    @ParameterizedTest
    @DisplayName("sum 테스트")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void getCountOfPins(int count) {
        int num = 100;
        assertThat(Pin.of(count).sum(num))
                .isEqualTo(num + count);
    }
}
