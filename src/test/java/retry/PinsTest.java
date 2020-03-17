package retry;

import bowling.retry.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinsTest {

    @Test
    @DisplayName("핀 생성")
    void create() {
        // give
        Pins pins = new Pins();
        // when
        pins.add(5);
        pins.add(5);
        // then
        assertThat(pins.getCurrentHit()).isEqualTo(10);
        assertThat(pins.getPins().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("핀 예외 처리")
    void except() {
        // give
        Pins pins = new Pins();
        // when
        pins.add(10);
        // then
        assertThatIllegalArgumentException().isThrownBy(() -> pins.add(2));
    }
}
