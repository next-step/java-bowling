package bowling.step2.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    public final Frame NORMAL_FRAME = new NormalFrame();
    
    @Test
    @DisplayName("다음 프레임으로 넘어가기 - strike")
    void next_frame_strike() {
        assertThat(NORMAL_FRAME.bowl(10)).isNotEqualTo(NORMAL_FRAME);
    }
    
    @Test
    @DisplayName("다음 프레임으로 넘어가기 - spare")
    void next_frame_spare() {
        final Frame frame = NORMAL_FRAME.bowl(5);
        assertThat(frame.bowl(5)).isNotEqualTo(NORMAL_FRAME);
    }
    
    @Test
    @DisplayName("다음 프레임으로 넘어가기 - miss")
    void next_frame_miss() {
        final Frame frame = NORMAL_FRAME.bowl(5);
        assertThat(frame.bowl(4)).isNotEqualTo(NORMAL_FRAME);
    }
    
    @Test
    @DisplayName("한 번 더 투구하기 - normal")
    void next_frame_normal() {
        assertThat(NORMAL_FRAME.bowl(4)).isEqualTo(NORMAL_FRAME);
    }
}
