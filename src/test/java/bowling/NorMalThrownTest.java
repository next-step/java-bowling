package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NorMalThrownTest {

    @Test
    void 스트라이크() {
        assertThat(new NorMalThrown(Pins.from(10)).isStrike()).isTrue();
    }

    @Test
    void 투구점수() {
        assertThat(new NorMalThrown(Pins.from(5)).getScore()).isEqualTo(5);
    }

    @Test
    void 볼링() {
        NorMalThrown thrown = new NorMalThrown(Pins.from(5));
        thrown.bowl(3);
        assertThat(thrown.getScore()).isEqualTo(8);
    }
}
