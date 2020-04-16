package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PinTest {

    @DisplayName("쓰러 트린 핀에 올바른 값이 들어 왔는지 확인하고 아니라면 예외발생")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void validatePin(int pin) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Pin(pin);
        });

    }
}
