package bowling.step2.domain;

import bowling.step2.dto.CountOfFallenPinsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}