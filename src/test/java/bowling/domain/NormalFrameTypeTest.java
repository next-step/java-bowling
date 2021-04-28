package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTypeTest {

    @DisplayName("쓰러트린 핀의 합계가 10개 이상일 경우 예외를 반환한다.")
    @Test
    void overTenPin() {
        FrameType frameType = new NormalFrameType();

        assertThatThrownBy(() -> {
            frameType.pitch(8);
            frameType.pitch(3);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
