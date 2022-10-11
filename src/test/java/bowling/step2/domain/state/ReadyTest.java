package bowling.step2.domain.state;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    public Ready ready;
    
    @BeforeEach
    void setUp() {
        ready = new Ready();
    }
    
    @Test
    @DisplayName("스트라이크")
    void strike() {
        State state = ready.bowl(10);
        assertThat(state).isExactlyInstanceOf(Strike.class);
    }
    
    @Test
    @DisplayName("노멀")
    void normal() {
        State state = ready.bowl(3);
        assertThat(state).isExactlyInstanceOf(Normal.class);
    }
    
    @Test
    @DisplayName("프레임 진행")
    void frame_running() {
        assertThat(ready.isFinished()).isFalse();
    }
    
    @Test
    @DisplayName("점수 확인")
    void display() {
        assertThat(ready.getScores()).isEqualTo(List.of(new Score(-1)));
    }
}
