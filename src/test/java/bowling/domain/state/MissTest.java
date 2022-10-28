package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    void description() {
        assertThat(miss().description()).isEqualTo("9|-");
    }

    @Test
    void tries() {
        assertThat(miss().tries()).isEqualTo(2);
    }

    private Miss miss() {
        return new Miss(FallenPin.of(9), FallenPin.of(0));
    }
}
