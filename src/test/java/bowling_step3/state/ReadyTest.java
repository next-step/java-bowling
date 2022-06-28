package bowling_step3.state;

import bowling_step3.domain.state.FirstPitch;
import bowling_step3.domain.state.Ready;
import bowling_step3.domain.state.Status;
import bowling_step3.domain.state.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    @Test
    public void bowlWhenStrike() throws Exception {
        Ready ready = new Ready();
        Status state = ready.pitch(10);
        assertThat(state instanceof Strike).isTrue();
    }

    @Test
    public void bowlWhenFirst() throws Exception {
        Ready ready = new Ready();
        Status state = ready.pitch(9);
        assertThat(state instanceof FirstPitch).isTrue();
    }
}
