package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinTest {

    @DisplayName("핀을 생성할 수 있다.")
    @Test
    void create() {
        Pin expect = Pin.of();

        Pin actual = Pin.of();

        assertThat(actual).isEqualTo(expect);
    }
}