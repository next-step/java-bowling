package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    void description() {
        assertThat(spare().description()).isEqualTo("9|/");
    }

    @Test
    void tries() {
        assertThat(spare().tries()).isEqualTo(2);
    }

    private Spare spare() {
        return new Spare(FallenPin.of(9), FallenPin.of(1));
    }
}
