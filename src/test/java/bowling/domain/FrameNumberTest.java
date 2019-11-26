package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameNumberTest {

    @Test
    @DisplayName("FrameNumber 생성 테스트")
    void of() {
        FrameNumber one = FrameNumber.of(1);
        assertThat(one).isEqualTo(new FrameNumber(1));
    }

    @Test
    @DisplayName("FrameNumber 범위를 벗어났을때 Exception 테스트")
    void boundError() {
        assertThatThrownBy(() -> {
            FrameNumber.of(0);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage(FrameNumber.FRAME_NUMBER_BOUND_ERROR_MESSAGE);

        assertThatThrownBy(() -> {
            FrameNumber.of(11);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage(FrameNumber.FRAME_NUMBER_BOUND_ERROR_MESSAGE);
    }
}
