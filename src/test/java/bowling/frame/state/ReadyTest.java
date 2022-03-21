package bowling.frame.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    @Test
    public void bowlWhenStrike() throws Exception {
        Ready ready = new Ready();
        State state = ready.bowl(10);
        assertThat(state instanceof Strike).isTrue();
    }

    @Test
    public void bowlWhenFirst() throws Exception {
        Ready ready = new Ready();
        State state = ready.bowl(9);
        assertThat(state instanceof FirstBowl).isTrue();
    }
}
