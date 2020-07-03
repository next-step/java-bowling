package bowling.pin.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PinTest {

    @Test
    void of() {
        assertThat(Pin.of(1)).isEqualTo(Pin.of(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,11})
    void exception(int felled) {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.of(felled));
    }

    @Test
    void add() {
        assertThat(Pin.of(1).add(Pin.of(3))).isEqualTo(Pin.of(4));
    }

    @Test
    void addException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.of(9).add(Pin.of(3)));
    }
}