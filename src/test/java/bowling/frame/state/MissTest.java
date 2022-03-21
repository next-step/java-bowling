package bowling.frame.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {

    @Test
    public void bowl() {
        assertThatThrownBy(() -> {
            Ready ready = new Ready();
            ready.bowl(8).bowl(1).bowl(10);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
