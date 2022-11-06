package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {

    @DisplayName("핀 개수 정합성 체크")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "11", "", " "})
    void pin_validation(String countOfPin) {
        assertThatThrownBy(() -> Pin.of(countOfPin))
                .isInstanceOf(IllegalArgumentException.class);
    }

}