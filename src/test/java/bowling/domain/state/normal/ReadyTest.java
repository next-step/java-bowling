package bowling.domain.state.normal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    void 종료() {
        Ready ready = new Ready();
        assertThat(ready.isFinish()).isFalse();
    }
}
