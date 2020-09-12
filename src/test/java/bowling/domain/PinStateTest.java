package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinStateTest {
    @Test
    void constructor() {
        assertThat(PinState.from()).isNotNull();
    }

    @Test
    void canHitAndCounting() {
        PinState pinState = PinState.from();

        assertThat(pinState.canHit()).isTrue();
        pinState.counting();
        assertThat(pinState.canHit()).isTrue();
        pinState.counting();
        assertThat(pinState.canHit()).isFalse();
    }

    @Test
    void isFirstTime() {
        PinState pinState = PinState.from();

        assertThat(pinState.isFirstTime()).isFalse();
        pinState.counting();
        assertThat(pinState.isFirstTime()).isTrue();
    }
}
