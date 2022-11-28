package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PinTest {

    @Test
    void 볼링_생성() {
        assertThat(Pin.from(5).addPins(5).getFalledPins()).isEqualTo(10);
    }

    @Test
    void 볼링_캐싱() {
        assertThat(Pin.from(5) == Pin.from(5)).isTrue();
    }

    @Test
    void 핀은_0에서_10만_가능() {
        assertThatThrownBy(() -> Pin.from(-1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Pin.from(11))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 스트라이크() {
        assertThat(Pin.from(10).isStrike()).isTrue();
    }

    @Test
    void 스페어() {
        assertThat(Pin.from(1).isSpare(9)).isTrue();
    }
}
