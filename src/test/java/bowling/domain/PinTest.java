package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {

    @Test
    void canCreate() {
        Pin pin = new Pin(5);
    }

    @DisplayName("유효한 볼링핀의 범위인지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void testPinNumber(final int pins) {
        assertThatThrownBy(() -> {
            new Pin(pins);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
