package bowling.domain.shot.type;

import bowling.domain.shot.Pins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterCheckerTest {

    @Test
    void availableFirstShots() {
        assertThat(new GutterChecker(true).availableFirstShots(Pins.of(0)))
                .isTrue();
        assertThat(new GutterChecker(false).availableFirstShots(Pins.of(0)))
                .isFalse();
        assertThat(new GutterChecker(true).availableFirstShots(Pins.of(1)))
                .isFalse();
    }

    @Test
    void availableSecondShots() {
        assertThat(new GutterChecker(false).availableSecondShots(Pins.of(1), Pins.of(0)))
                .isTrue();
        assertThat(new GutterChecker(true).availableSecondShots(Pins.of(10), Pins.of(0)))
                .isFalse();
        assertThat(new GutterChecker(false).availableSecondShots(Pins.of(1), Pins.of(1)))
                .isFalse();
    }
}
