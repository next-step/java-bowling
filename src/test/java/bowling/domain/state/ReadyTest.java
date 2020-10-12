package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    @DisplayName("Ready 상태 투구 - Strike")
    @Test
    void pitch() {
        Ready ready = new Ready();
        State state = ready.pitch(10);

        assertThat(state)
                .isInstanceOf(Strike.class)
                .hasToString("X");
    }

    @DisplayName("Ready 상태 투구 - FirstBowl")
    @Test
    void pitch2() {
        Ready ready = new Ready();
        State state = ready.pitch(0);

        assertThat(state)
                .isInstanceOf(FirstBowl.class)
                .hasToString("0");
    }
}
