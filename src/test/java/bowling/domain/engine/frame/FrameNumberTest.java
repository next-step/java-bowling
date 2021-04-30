package bowling.domain.engine.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FrameNumberTest {

    @Test
    @DisplayName("프레임 번호는 1부터 10 사이의 값만 허용한다.")
    void rangeOfFrameNumbers() {
        IntStream.rangeClosed(1, 10).forEach(value -> {
            assertThatCode(() -> FrameNumber.wrap(value)).doesNotThrowAnyException();
        });
    }

    @Test
    @DisplayName("범위를 벗어나면 예외 처리한다.")
    void throwExceptionIfValueIsOutOfRange() {
        assertAll(
            () -> assertThatThrownBy(() -> FrameNumber.wrap(11)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> FrameNumber.wrap(0)).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("다음 프레임 번호를 가져온다.")
    void getNextFrameNumber() {
        FrameNumber frameNumber = FrameNumber.firstFrame();

        assertThat(frameNumber.getNextNumber().getNumber()).isEqualTo(2);
    }
    
}
