package bowling.step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    public static final NormalFrame NORMAL_FRAME = new NormalFrame();
    
    @Test
    @DisplayName("다음 프레임으로 넘어가기 - strike")
    void next_frame() {
        assertThat(NORMAL_FRAME.bowl(10)).isNotEqualTo(NORMAL_FRAME);
    }
}
