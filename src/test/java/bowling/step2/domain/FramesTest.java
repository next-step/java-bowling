package bowling.step2.domain;

import bowling.step2.dto.CountOfFallenPinsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    public Frames frames;
    
    @BeforeEach
    void setUp() {
        frames = new Frames();
    }
    
    @Test
    @DisplayName("현재 프레임이 계속 진행중 확인")
    void is_current_frame_running() {
        assertThat(frames.bowl(new CountOfFallenPinsDTO("7"))).isTrue();
        assertThat(frames.bowl(new CountOfFallenPinsDTO("3"))).isFalse();
    }
    
    @Test
    @DisplayName("누적 점수 리스트 구하기")
    void cumulative_scores() {
        frames.bowl(new CountOfFallenPinsDTO("3"));
        frames.bowl(new CountOfFallenPinsDTO("7")); // 20
        frames.bowl(new CountOfFallenPinsDTO("10")); // 44
        frames.bowl(new CountOfFallenPinsDTO("10")); // 64
        frames.bowl(new CountOfFallenPinsDTO("4"));
        frames.bowl(new CountOfFallenPinsDTO("6")); // 79
        frames.bowl(new CountOfFallenPinsDTO("5"));
        frames.bowl(new CountOfFallenPinsDTO("3")); // 87
        frames.bowl(new CountOfFallenPinsDTO("0"));
        frames.bowl(new CountOfFallenPinsDTO("3")); // 90
        frames.bowl(new CountOfFallenPinsDTO("2"));
        frames.bowl(new CountOfFallenPinsDTO("8")); // 110
        frames.bowl(new CountOfFallenPinsDTO("10")); // 133
        frames.bowl(new CountOfFallenPinsDTO("10")); // 153
        
        assertThat(frames.getCumulativeScores()).isEqualTo(Arrays.asList(20, 44, 64, 79, 87, 90, 110, -1, -1, -1));
        
        frames.bowl(new CountOfFallenPinsDTO("3"));
        frames.bowl(new CountOfFallenPinsDTO("7"));
        frames.bowl(new CountOfFallenPinsDTO("10")); // 173
        
        assertThat(frames.getCumulativeScores()).isEqualTo(Arrays.asList(20, 44, 64, 79, 87, 90, 110, 133, 153, 173));
    }
}