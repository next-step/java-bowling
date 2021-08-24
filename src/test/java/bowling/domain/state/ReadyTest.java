package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

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

    @Test
    void 출력_예외() {
        Ready ready = new Ready();
        assertThatThrownBy(ready::display).isInstanceOf(IllegalArgumentException.class);
    }
}