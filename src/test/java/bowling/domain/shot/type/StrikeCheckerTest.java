package bowling.domain.shot.type;

import bowling.domain.shot.Pins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeCheckerTest {

    @Test
    void availableFirstShots() {
        assertThat(new StrikeChecker().availableFirstShots(Pins.of(10)))
                .isTrue();
        assertThat(new StrikeChecker().availableFirstShots(Pins.of(9)))
                .isFalse();
    }

    @Test
    void availableSecondShots() {
        assertThat(new StrikeChecker().availableSecondShots(Pins.of(0), Pins.of(10)))
                .isFalse();
    }
}
