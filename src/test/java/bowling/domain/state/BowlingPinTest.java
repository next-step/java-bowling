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

    @Test
    void 최대_볼링핀_테스트() {
        assertThat(BowlingPin.of(10).isMax()).isTrue();
        assertThat(BowlingPin.of(9).isMax()).isFalse();
    }

    @Test
    void 합계_테스트() {
        assertThat(BowlingPin.of(3).sum(BowlingPin.of(7))).isEqualTo(BowlingPin.of(10));
        assertThat(BowlingPin.of(2).sum(BowlingPin.of(7))).isEqualTo(BowlingPin.of(9));
    }

    @Test
    void 합계_유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BowlingPin.of(3).sum(BowlingPin.of(8)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> BowlingPin.of(5).sum(BowlingPin.of(8)));
    }

    @Test
    void 볼링핀_출력_테스트() {
        assertThat(BowlingPin.of(7).score()).isEqualTo("7");
        assertThat(BowlingPin.of(0).score()).isEqualTo("-");
    }
}
