package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class PinsTest {
    @Test
    void 예외처리_0보다작을때() {
        assertThatThrownBy(() -> {
            Pins.pitching(-1);
        }).isInstanceOf(CannotBeLessThanZero.class);
    }

    @Test
    void 예외처리_10보다클때() {
        assertThatThrownBy(() -> {
            Pins.pitching(11);
        }).isInstanceOf(CannotBeBiggerThanMax.class);
    }

}
