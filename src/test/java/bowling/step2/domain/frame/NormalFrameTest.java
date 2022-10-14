package bowling.step2.domain.frame;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    public Frame normalFrame;
    
    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame(1);
    }
    
    @Test
    @DisplayName("다음 프레임으로 넘어가기 - strike")
    void next_frame_strike() {
        assertThat(normalFrame.bowl(10)).isNotEqualTo(normalFrame);
    }
    
    @Test
    @DisplayName("다음 프레임으로 넘어가기 - spare")
    void next_frame_spare() {
        final Frame frame = normalFrame.bowl(5);
        assertThat(frame.bowl(5)).isNotEqualTo(normalFrame);
    }
    
    @Test
    @DisplayName("다음 프레임으로 넘어가기 - miss")
    void next_frame_miss() {
        final Frame frame = normalFrame.bowl(5);
        assertThat(frame.bowl(4)).isNotEqualTo(normalFrame);
    }
    
    @Test
    @DisplayName("한 번 더 투구하기 - normal")
    void next_frame_normal() {
        assertThat(normalFrame.bowl(4)).isEqualTo(normalFrame);
    }
    
    @Test
    @DisplayName("9번째 프레임 끝난 후 FinalFrame 반환")
    void next_final_frame() {
        Frame normalFrame = new NormalFrame(9);
        assertThat(normalFrame.bowl(10)).isExactlyInstanceOf(FinalFrame.class);
    }
    
    @Test
    @DisplayName("노멀 프레임인지 확인")
    void is_normal_frame() {
        assertThat(normalFrame.isNormalFrame()).isTrue();
    }
    
    @Test
    @DisplayName("점수 확인")
    void display() {
        final Frame frame = normalFrame.bowl(7);
        assertThat(frame.getScores()).isEqualTo(List.of(new Score(7)));
        
        frame.bowl(3);
        assertThat(frame.getScores()).isEqualTo(Arrays.asList(new Score(7), new Score(3)));
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 레디")
    void get_one_next_score_ready() {
        int oneNextScore = normalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(-1);
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 노멀")
    void get_one_next_score_normal() {
        normalFrame.bowl(3);
        int oneNextScore = normalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(3);
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 미스")
    void get_one_next_score_miss() {
        normalFrame.bowl(5);
        normalFrame.bowl(3);
        int oneNextScore = normalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(5);
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 스페어")
    void get_one_next_score_spare() {
        normalFrame.bowl(3);
        normalFrame.bowl(7);
        int oneNextScore = normalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(3);
    }
    
    @Test
    @DisplayName("다음 프레임의 1개의 점수 구하기 - 스트라이크")
    void get_one_next_score_strike() {
        normalFrame.bowl(10);
        int oneNextScore = normalFrame.getOneNextScore();
        assertThat(oneNextScore).isEqualTo(10);
    }
}
