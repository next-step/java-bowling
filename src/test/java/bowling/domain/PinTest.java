package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinTest {
    @DisplayName("핀을 생성할 수 있다.")
    @Test
    void create() {
        boolean isKnockOver = false;
        Pin expect = new Pin(isKnockOver);

        Pin actual = new Pin(isKnockOver);

        assertThat(actual).isEqualTo(expect);
    }
}