package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FrameNumberTest {

    @DisplayName("FrameNumber가 1 ~ 10이 아니면 IllegalArgumentException throw")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 11, 12})
    void validateNullNumber(int number) {
        assertThatThrownBy(() -> new FrameNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("프레임 번호는 1 ~ 10 사이의 값이어야 합니다. - " + number);
    }
}