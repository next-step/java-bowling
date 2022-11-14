package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinsTest {

    @Test
    void knockDownTest() {
        Pins pins = new Pins();

        pins.knockDown(1);
        assertThat(9).isEqualTo(pins.standingPinCount());

        pins.knockDown(2);
        assertThat(7).isEqualTo(pins.standingPinCount());

        pins.knockDown(3);
        assertThat(4).isEqualTo(pins.standingPinCount());

        pins.knockDown(4);
        assertThat(0).isEqualTo(pins.standingPinCount());
    }
}
