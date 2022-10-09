package bowling.step2.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FinalFrameTest {
    public final Frame FINAL_FRAME = new FinalFrame();
    
    @Test
    @DisplayName("첫번째와 두번째 투구에서 스트라이크 시 한 번 더 투구")
    void strike_bonus_bowl() {
        Frame frame = FINAL_FRAME.bowl(10);
        assertThat(frame).isEqualTo(FINAL_FRAME);
    
        frame = FINAL_FRAME.bowl(10);
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
    @DisplayName("게임 종료 : 스트라이크 3번")
    void finish_three_strike() {
        Frame frame = FINAL_FRAME.bowl(10);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(10);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(10);
        assertThat(frame).isNotEqualTo(FINAL_FRAME);
    }
    
    @Test
    @DisplayName("게임 종료 : 스트라이크 -> 스페어")
    void finish_strike_spare() {
        Frame frame = FINAL_FRAME.bowl(10);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(5);
        assertThat(frame).isNotEqualTo(FINAL_FRAME);
    }
    
    @Test
    @DisplayName("게임 종료 : 스트라이크 -> 미스")
    void finish_strike_miss() {
        Frame frame = FINAL_FRAME.bowl(10);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(3);
        assertThat(frame).isNotEqualTo(FINAL_FRAME);
    }
    
    @Test
    @DisplayName("게임 종료 : 스페어 -> 스트라이크")
    void finish_spare_strike() {
        Frame frame = FINAL_FRAME.bowl(5);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(10);
        assertThat(frame).isNotEqualTo(FINAL_FRAME);
    }
    
    @Test
    @DisplayName("게임 종료 : 스페어 -> 노멀")
    void finish_spare_normal() {
        Frame frame = FINAL_FRAME.bowl(5);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(7);
        assertThat(frame).isNotEqualTo(FINAL_FRAME);
    }
    
    @Test
    @DisplayName("게임 종료 : 미스")
    void finish_miss() {
        Frame frame = FINAL_FRAME.bowl(5);
        assertThat(frame).isEqualTo(FINAL_FRAME);
        
        frame = frame.bowl(4);
        assertThat(frame).isNotEqualTo(FINAL_FRAME);
    }
}
