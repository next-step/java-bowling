package bowling.domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    @Test
    void pitch() {
        Ready ready = new Ready();
        State state = ready.pitch(10);

        assertThat(state).isInstanceOf(Strike.class);
        assertThat(state).hasToString("X");
    }

    @Test
    void pitch2() {
        Ready ready = new Ready();
        State state = ready.pitch(0);

        assertThat(state).isInstanceOf(FirstBowl.class);
        assertThat(state).hasToString("0");
    }
}
