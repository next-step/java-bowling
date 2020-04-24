package bowling.domain.scoreType;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpareCheckerTest {

    @Test
    void availableFirstShots() {
        assertThat(new SpareChecker().availableFirstShots(Score.of(5)))
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
        assertThat(new SpareChecker().availableSecondShots(Score.of(first), Score.of(second)))
                .isTrue();
    }
}
