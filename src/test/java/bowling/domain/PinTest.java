package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinTest {
    @DisplayName("핀을 생성할 수 있다.")
    @Test
    void create() {
        PinState standing = PinState.STANDING;
        Pin expect = Pin.of(standing);

        Pin actual = Pin.of(standing);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("핀을 넘어트릴 수 있다.")
    @Test
    void knockOver() {
        PinState standing = PinState.STANDING;
        Pin expect = Pin.of(PinState.KNOCK_OVER);

        Pin actual = Pin.of(standing).knockOver();

        assertThat(actual).isEqualTo(expect);
    }
}