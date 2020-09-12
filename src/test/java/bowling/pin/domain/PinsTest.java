package bowling.pin.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    private Pins pins;

    @Test
    @DisplayName("2번의 투구")
    void pitch() {
        for (int i = 0; i < 2; i++) {
            pins = Pins.playPitch("5");
        }
        assertThat(pins.size()).isEqualTo(2);
    }

}
