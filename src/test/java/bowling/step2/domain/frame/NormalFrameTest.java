package bowling.step2.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    public final Frame NORMAL_FRAME = new NormalFrame(1);
    
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
    
    @Test
    @DisplayName("9번째 프레임 끝난 후 FinalFrame 반환")
    void next_final_frame() {
        Frame normalFrame = new NormalFrame(9);
        assertThat(normalFrame.bowl(10)).isExactlyInstanceOf(FinalFrame.class);
    }
}
