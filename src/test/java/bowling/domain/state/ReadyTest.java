package bowling.domain.state;

import bowling.domain.PinCount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    @Test
    void 종료() {
        Ready ready = new Ready();
        assertThat(ready.isFinish()).isFalse();
    }
}
