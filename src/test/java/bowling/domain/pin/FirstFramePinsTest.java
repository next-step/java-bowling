package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FirstFramePinsTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("FirstFramePins 는 하나의 Pins 객체를 파라미터로 받는다.")
    void create_FirstFramePins(int countOfPins) {
        assertThat(FirstFramePins.of(Pins.of(countOfPins))).isNotNull();
    }
}