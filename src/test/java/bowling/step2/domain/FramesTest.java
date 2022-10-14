package bowling.step2.domain;

import bowling.step2.dto.CountOfFallenPinsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    public Player player;
    
    @BeforeEach
    void setUp() {
        player = new Player("SJH");
    }
    
    @Test
    @DisplayName("현재 프레임이 계속 진행중 확인")
    void is_current_frame_running() {
        final Frames frames = new Frames();
        assertThat(frames.bowl(new CountOfFallenPinsDTO("7"))).isTrue();
        assertThat(frames.bowl(new CountOfFallenPinsDTO("3"))).isFalse();
    }
    
//    @Test
//    @DisplayName("누적 점수 구하기 - 스트라이크")
//    void get_cumulative_score_strike() {
//        player.bowl(new CountOfFallenPinsDTO("10"));
//        player.bowl(new CountOfFallenPinsDTO("5"));
//        player.bowl(new CountOfFallenPinsDTO("5"));
//        final Frames frames = player.getFrames();
//
//        List<String> cumulativeScores = frames.calculateCumulativeScores();
//        assertThat(cumulativeScores).isEqualTo(List.of("20"));
//    }
}