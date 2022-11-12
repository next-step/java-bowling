package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PinsTest {

    @Test
    void 볼링_생성() {
        assertThat(Pins.from(5).addPins(5).getFalledPins()).isEqualTo(10);
    }

    @Test
    void 볼링_캐싱() {
        assertThat(Pins.from(5) == Pins.from(5)).isTrue();
    }

    @Test
    void 핀은_0에서_10만_가능() {
        assertThatThrownBy(() -> Pins.from(-1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Pins.from(11))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 스트라이크() {
        assertThat(Pins.from(10).isMax()).isTrue();
    }

    @Test
    void 스페어() {
        assertThat(Pins.from(1).addPins(9).isMax()).isTrue();
    }

    @Test
    void 미스() {
        assertThat(Pins.from(0).isGutter()).isTrue();
    }
}
