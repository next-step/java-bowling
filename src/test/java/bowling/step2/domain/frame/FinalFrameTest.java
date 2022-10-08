package bowling.step2.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    public final Frame FINAL_FRAME = new FinalFrame();
    
    @Test
    @DisplayName("첫번째 투구에서 스트라이크 시 한 번 더 투구")
    void strike_bonus_bowl() {
        Frame frame = FINAL_FRAME.bowl(10);
        assertThat(frame).isEqualTo(FINAL_FRAME);
    }
    
    @Test
    @DisplayName("두번째 투구에서 스페어 시 한 번 더 투구")
    void spare_bonus_bowl() {
        Frame frame = FINAL_FRAME.bowl(5);
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(FINAL_FRAME);
    }
    
    @Test
    @DisplayName("두번째 투구에서 미스 시 게임 종료")
    void miss_not_bonus_bowl() {
        Frame frame = FINAL_FRAME.bowl(5);
        frame = frame.bowl(3);
        assertThat(frame).isNotEqualTo(FINAL_FRAME);
    }
    
    @Test
    @DisplayName("보너스 투구 완료 시 게임 종료")
    void bonus_bowl_success() {
        Frame frame = FINAL_FRAME.bowl(10);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(5);
        assertThat(frame).isNotEqualTo(FINAL_FRAME);
    }
}
