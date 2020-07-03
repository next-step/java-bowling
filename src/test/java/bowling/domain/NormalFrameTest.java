package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("일반 투구 프레임")
    @Test
    void normalFrameInit() {
        Frame frame = Frame.of();
        assertThat(frame instanceof NormalFrame).isTrue();
    }

}
