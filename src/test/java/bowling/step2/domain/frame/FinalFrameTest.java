package bowling.step2.domain.frame;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    public Frame finalFrame;
    
    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
    }
    
    @Test
    @DisplayName("첫번째와 두번째 투구에서 스트라이크 시 한 번 더 투구")
    void strike_bonus_bowl() {
        Frame frame = finalFrame.bowl(10);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = finalFrame.bowl(10);
        assertThat(frame).isEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("두번째 투구에서 스페어 시 한 번 더 투구")
    void spare_bonus_bowl() {
        Frame frame = finalFrame.bowl(5);
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("두번째 투구에서 미스 시 게임 종료")
    void miss_not_bonus_bowl() {
        Frame frame = finalFrame.bowl(5);
        frame = frame.bowl(3);
        assertThat(frame).isNotEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("게임 종료 : 스트라이크 3번")
    void finish_three_strike() {
        Frame frame = finalFrame.bowl(10);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(10);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(10);
        assertThat(frame).isNotEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("게임 종료 : 스트라이크 -> 스페어")
    void finish_strike_spare() {
        Frame frame = finalFrame.bowl(10);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(5);
        assertThat(frame).isNotEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("게임 종료 : 스트라이크 -> 미스")
    void finish_strike_miss() {
        Frame frame = finalFrame.bowl(10);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(3);
        assertThat(frame).isNotEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("게임 종료 : 스페어 -> 스트라이크")
    void finish_spare_strike() {
        Frame frame = finalFrame.bowl(5);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(10);
        assertThat(frame).isNotEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("게임 종료 : 스페어 -> 노멀")
    void finish_spare_normal() {
        Frame frame = finalFrame.bowl(5);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(5);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(7);
        assertThat(frame).isNotEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("게임 종료 : 미스")
    void finish_miss() {
        Frame frame = finalFrame.bowl(5);
        assertThat(frame).isEqualTo(finalFrame);
        
        frame = frame.bowl(4);
        assertThat(frame).isNotEqualTo(finalFrame);
    }
    
    @Test
    @DisplayName("노멀 프레임 아님을 확인")
    void is_not_normal_frame() {
        assertThat(finalFrame.isNormalFrame()).isFalse();
    }
    
    @Test
    @DisplayName("점수 확인")
    void display() {
        Frame frame = finalFrame.bowl(5);
        frame = frame.bowl(5);
        assertThat(frame.getScores()).isEqualTo(Arrays.asList(new Score(5), new Score(5), new Score(-1)));
        
        frame.bowl(5);
        assertThat(frame.getScores()).isEqualTo(Arrays.asList(new Score(5), new Score(5), new Score(5)));
    }
}
