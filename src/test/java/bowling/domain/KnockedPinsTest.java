package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class KnockedPinsTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    public void count(final int count) {
        assertThat(KnockedPins.from(count)).isEqualTo(KnockedPins.from(count));
    }

    @Test
    public void invalid_0_미만() {
        assertThatIllegalArgumentException().isThrownBy(() -> KnockedPins.from(-1));
    }

    @Test
    public void invalid_10_초과() {
        assertThatIllegalArgumentException().isThrownBy(() -> KnockedPins.from(11));
    }
}
