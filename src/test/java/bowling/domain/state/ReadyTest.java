package bowling.domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {
    @Test
    void 스트라이크_테스트() {
        Ready ready = new Ready();
        assertThat(ready.nextPitch(10)).isInstanceOf(Strike.class);
    }

    @Test
    void 스페어처리_테스트() {
        Ready ready = new Ready();
        assertThat(ready.nextPitch(8)).isInstanceOf(FirstPitch.class);
    }
}