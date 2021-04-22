package bowling.domain.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BowlingPinTest {

    @Test
    void 유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BowlingPin.of(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> BowlingPin.of(11));
    }
}
