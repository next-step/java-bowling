package bowling_step3.state;

import bowling_step3.domain.state.Ready;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {
    @Test
    public void pitch() {
        assertThatThrownBy(() -> {
            Ready ready = new Ready();
            ready.pitch(8).pitch(1).pitch(10);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
