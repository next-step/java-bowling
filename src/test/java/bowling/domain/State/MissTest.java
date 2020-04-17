package bowling.domain.State;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {

    @DisplayName("두번을 던지고 또다시 던지려고 할때 예외 처리")
    @Test
    void validateBowl() {
        Miss miss = new Miss(Pins.bowl(2), Pins.bowl(7));
        assertThatThrownBy(() -> {
            miss.bowl(7);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
