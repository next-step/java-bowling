package bowling.game.vo;

import bowling.game.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}