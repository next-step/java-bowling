package bowling.step2.domain.state;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalTest {
    public static final Normal NORMAL = new Normal(new Score(3));
    
    @Test
    @DisplayName("스페어")
    void spare() {
        assertThat(NORMAL.bowl(7)).isExactlyInstanceOf(Spare.class);
    }
    
    @Test
    @DisplayName("미스")
    void miss() {
        assertThat(NORMAL.bowl(6)).isExactlyInstanceOf(Miss.class);
    }
    
    @Test
    @DisplayName("프레임 진행")
    void frame_running() {
        assertThat(NORMAL.isFinished()).isFalse();
    }
}