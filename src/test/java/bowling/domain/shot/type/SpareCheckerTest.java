package bowling.domain.shot.type;

import bowling.domain.shot.Pins;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpareCheckerTest {

    @Test
    void availableFirstShots() {
        assertThat(new SpareChecker().availableFirstShots(Pins.of(5)))
                .isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,10",
            "1,9",
            "2,8",
            "9,1"
    })
    void availableSecondShots(int first, int second) {
        assertThat(new SpareChecker().availableSecondShots(Pins.of(first), Pins.of(second)))
                .isTrue();
    }
}
