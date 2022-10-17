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
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 레디")
    void get_one_next_score_ready() {
        int oneNextScore = finalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 노멀")
    void get_one_next_score_normal() {
        finalFrame.bowl(3);
        int oneNextScore = finalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(3);
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 미스")
    void get_one_next_score_miss() {
        finalFrame.bowl(5);
        finalFrame.bowl(3);
        int oneNextScore = finalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(5);
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 스페어")
    void get_one_next_score_spare() {
        finalFrame.bowl(3);
        finalFrame.bowl(7);
        int oneNextScore = finalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(3);
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 스트라이크")
    void get_one_next_score_strike() {
        finalFrame.bowl(10);
        int oneNextScore = finalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(10);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 레디")
    void get_two_next_score_ready() {
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 노멀")
    void get_two_next_score_normal() {
        finalFrame.bowl(3);
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 미스")
    void get_two_next_score_miss() {
        finalFrame.bowl(5);
        finalFrame.bowl(3);
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(8);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 스페어")
    void get_two_next_score_spare() {
        finalFrame.bowl(3);
        finalFrame.bowl(7);
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(10);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 스트라이크 => 레디")
    void get_two_next_score_strike_ready() {
        finalFrame.bowl(10);
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 스트라이크 => 노멀")
    void get_two_next_score_strike_normal() {
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(15);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 스트라이크 => 미스")
    void get_two_next_score_strike_miss() {
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        finalFrame.bowl(4);
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(15);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 스트라이크 => 스페어")
    void get_two_next_score_strike_spare() {
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        finalFrame.bowl(5);
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(15);
    }
    
    @Test
    @DisplayName("다음 프레임의 2개의 점수 구하기 - 스트라이크 => 스트라이크")
    void get_two_next_score_strike_strike() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        int twoNextScore = finalFrame.getTwoNextScore();
        assertThat(twoNextScore).isEqualTo(20);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 레디")
    void cumulative_score_ready() {
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 노멀")
    void cumulative_score_normal() {
        finalFrame.bowl(6);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 미스")
    void cumulative_score_miss() {
        finalFrame.bowl(6);
        finalFrame.bowl(3);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(14);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스페어 => 레디")
    void cumulative_score_spare_ready() {
        finalFrame.bowl(6);
        finalFrame.bowl(4);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스페어 => 노멀")
    void cumulative_score_spare_normal() {
        finalFrame.bowl(6);
        finalFrame.bowl(4);
        finalFrame.bowl(5);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(20);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스페어 => 스트라이크")
    void cumulative_score_spare_strike() {
        finalFrame.bowl(6);
        finalFrame.bowl(4);
        finalFrame.bowl(10);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(25);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크 => 레디")
    void cumulative_score_strike() {
        finalFrame.bowl(10);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크 => 노멀")
    void cumulative_score_strike_normal() {
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크 => 미스")
    void cumulative_score_strike_miss() {
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        finalFrame.bowl(3);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(23);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크 => 스페어")
    void cumulative_score_strike_spare() {
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        finalFrame.bowl(5);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(25);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크 => 스트라이크 => 레디")
    void cumulative_score_strike_strike_ready() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크 => 스트라이크 => 노멀")
    void cumulative_score_strike_strike_normal() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(30);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크 => 스트라이크 => 스트라이크")
    void cumulative_score_strike_strike_strike() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        int twoNextScore = finalFrame.calculateCumulativeScore(5);
        assertThat(twoNextScore).isEqualTo(35);
    }
}
