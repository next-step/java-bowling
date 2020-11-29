package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("쓰러진 핀 테스트")
public class PinTest {
    @DisplayName("핀 개수가 0개 미만일때")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1})
    public void invalidMinPin(int pins) {
        assertThatThrownBy(() -> {
            Pin.of(pins);
        }).isInstanceOf(InvalidMinimumPinException.class);
    }

    @DisplayName("핀 개수가 10개 초과일때")
    @ParameterizedTest
    @ValueSource(ints = {11, 12, 13})
    public void invalidMaxPin(int pins) {
        assertThatThrownBy(() -> {
            Pin.of(pins);
        }).isInstanceOf(InvalidMaximumPinException.class);
    }
}