package bowling.domain;

import org.junit.jupiter.api.Test;

import static bowling.domain.PinState.DEFAULT_TRY_COUNT;
import static bowling.domain.PinState.MAX_TRY_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class PinStateTest {
    @Test
    void of() {
        assertThat(PinState.of(MAX_TRY_COUNT, DEFAULT_TRY_COUNT)).isNotNull();
    }

    @Test
    void canHitAndCounting() {
        PinState pinState = PinState.of(MAX_TRY_COUNT, DEFAULT_TRY_COUNT);

        assertThat(pinState.canHit()).isTrue();
        pinState.counting();
        assertThat(pinState.canHit()).isTrue();
        pinState.counting();
        assertThat(pinState.canHit()).isFalse();
    }

    @Test
    void isFirstTime() {
        PinState pinState = PinState.of(MAX_TRY_COUNT, DEFAULT_TRY_COUNT);

        assertThat(pinState.isFirstTime()).isFalse();
        pinState.counting();
        assertThat(pinState.isFirstTime()).isTrue();
    }
}
