package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {

    @DisplayName("범위를 초과하는 핀을 쓰러뜨리면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void valid_pin(int countOfDownPin) {
        assertThatThrownBy(() -> {
            Pin.from(countOfDownPin);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}