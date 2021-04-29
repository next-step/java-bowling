package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FrameNumberTest {

    @Test
    @DisplayName("프레임 번호는 1부터 10 사이의 값만 허용한다.")
    void rangeOfFrameNumbers() {
        for (int i = 1; i <= 10; ++i) {
            assertThatCode(() -> FrameNumber.wrap(i)).doesNotThrowAnyException();
        }
    }

    @Test
    @DisplayName("범위를 벗어나면 예외 처리한다.")
    void throwExceptionIfValueIsOutOfRange() {
        assertAll(
            () -> assertThatThrownBy(() -> FrameNumber.wrap(11)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> FrameNumber.wrap(0)).isInstanceOf(IllegalArgumentException.class)
        );
    }
}