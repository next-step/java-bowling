package bowling.game.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PinTest {

    @DisplayName("쓰러진 핀의 갯수가 0 ~ 10 사이가 아니면 IllegalArguments Exception throw")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void validatePinCount(int pinCount) {
        assertThatThrownBy(() -> new Pin(pinCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
    }

    @DisplayName("쓰러진 핀이 10개이면 true 반환")
    @ParameterizedTest
    @CsvSource({"10, true", "1, false"})
    void isMaxCount(int pinCount, boolean result) {
        Pin pin = new Pin(pinCount);

        assertThat(pin.isMaxCount()).isEqualTo(result);
    }

    @DisplayName("쓰러진 핀이 0개이면 true 반환")
    @ParameterizedTest
    @CsvSource({"0, true", "3, false"})
    void isMinCount(int pinCount, boolean result) {
        Pin pin = new Pin(pinCount);

        assertThat(pin.isMinCount()).isEqualTo(result);
    }

    @DisplayName("두 핀의 합을 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 2, 3", "3, 5, 8"})
    void addPin(int currentCount, int beforeCount, int result) {
        Pin current = new Pin(currentCount);
        Pin before = new Pin(beforeCount);
        Pin expected = new Pin(result);

        assertThat(Pin.add(current, before)).isEqualTo(expected);
    }
}