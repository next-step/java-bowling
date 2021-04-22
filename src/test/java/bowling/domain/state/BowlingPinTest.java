package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BowlingPinTest {

    @Test
    void 생성_테스트() {
        assertThat(BowlingPin.of(5)).isEqualTo(BowlingPin.of(5));
    }

    @Test
    void 유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BowlingPin.of(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> BowlingPin.of(11));
    }
}
