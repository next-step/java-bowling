package domain.bowling;

import domain.Pins;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SecondSetTest {

    @Test
    @DisplayName("범위를 넘어가는 예외처리")
    void InputException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            SecondSet.of(Pins.of(9), Pins.of(2));
        });
    }

    @Test
    @DisplayName("bowl 예외처리")
    void exception() {
        SecondSet set = SecondSet.of(Pins.of(9), Pins.of(1));
        Assertions.assertThrows(RuntimeException.class, () -> {
            set.bowl(Pins.of(1));
        });
    }

}