package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTypeTest {
    @DisplayName("쓰러트린 핀의 합계가 30개 이상일 경우 예외를 반환한다.")
    @Test
    void overThirtyPin() {
        FrameType frameType = new FinalFrameType();

        assertThatThrownBy(() -> {
            frameType.pitch(10);
            frameType.pitch(10);
            frameType.pitch(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
