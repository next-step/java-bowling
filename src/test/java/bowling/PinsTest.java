package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PinsTest {

    @Test
    void 볼링_생성() {
        assertThat(new Pins(5).totalPins(5)).isEqualTo(10);
    }

    @Test
    void 핀은_0에서_10만_가능() {
        assertThatThrownBy(() -> new Pins(-1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Pins(11))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
