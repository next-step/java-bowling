package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PinsTest {

    @Test
    void 볼링_생성() {
        assertThat(new Pins(5).totalPins(5).getFalledPins()).isEqualTo(10);
    }

    @Test
    void 볼링_캐싱() {
        assertThat(Pins.from(5) == Pins.from(5)).isTrue();
    }

    @Test
    void 핀은_0에서_10만_가능() {
        assertThatThrownBy(() -> new Pins(-1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Pins(11))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 스트라이크() {
        assertThat(new Pins(10).isStrike()).isTrue();
    }

    @Test
    void 미스() {
        assertThat(new Pins(0).isMiss()).isTrue();
    }
}
