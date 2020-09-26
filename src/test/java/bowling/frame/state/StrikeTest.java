package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StrikeTest {

    private State state;

    @BeforeEach
    void setUp() {
        state = Strike.of(Pin.bowl("10"));
    }

    @Test
    @DisplayName("두번째 볼링 결과 - 스트라이크")
    void result() {
        assertThat(state.isFinish()).isTrue();
    }

    @Test
    @DisplayName("두번째 볼링 결과 - 스트라이크")
    void resultMark() {
        assertThat(state.toString()).isEqualTo("X");
    }
}
