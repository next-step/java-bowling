package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {

    @DisplayName("핀은 최소 0개에서 최대 10개까지 넘어진다.")
    @ParameterizedTest(name = "핀이 {0} 개가 넘어진다")
    @ValueSource(ints = {0, 10})
    void pin(final int count) {

        final Pin pin = new Pin(count);
        assertThat(pin).isEqualTo(new Pin(count));
    }

    @DisplayName("핀은 0개 미만이거나 10개 초과되면 안된다.")
    @ParameterizedTest(name = "핀은 {0} 개가 넘어갈 수 없다.")
    @ValueSource(ints = {-1, 11})
    void valid_pin(final int count) {

        assertThatThrownBy(() -> new Pin(count))
                .isInstanceOf(IllegalArgumentException.class);
    }
}