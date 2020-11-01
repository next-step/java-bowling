package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicPinsTest {
    @ParameterizedTest
    @DisplayName("기본 핀을 쓰러뜨릴때는 0이상 10이하만 가능하다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void zeroToTen(int pinCount) {
        assertThat(new BasicPins(pinCount)).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("기본 핀을 쓰러뜨릴때는 0이상 10이하가 아니면 예외 발생")
    @ValueSource(ints = {-1, 11})
    void except(int pinCount) {
        assertThrows(IllegalArgumentException.class, () -> new BasicPins(pinCount));
    }
}
