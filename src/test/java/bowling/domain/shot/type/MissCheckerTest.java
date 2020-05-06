package bowling.domain.shot.type;

import bowling.domain.shot.Pins;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class MissCheckerTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void availableFirstFalse(int score) {
        assertThat(new MissChecker(true).availableFirstShots(Pins.of(score)))
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void availableFirstTrue(int score) {
        assertThat(new MissChecker(true).availableFirstShots(Pins.of(score)))
                .isTrue();

        assertThat(new MissChecker(false).availableFirstShots(Pins.of(score)))
                .isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,0",
            "0,10",
            "1,9",
            "9,0",
            "9,1"
    })
    void availableSecondFalse(int first, int second) {
        assertThat(new MissChecker(false).availableSecondShots(Pins.of(first), Pins.of(second)))
                .isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,1",
            "0,9",
            "1,8",
            "8,1"
    })
    void availableSecondTrue(int first, int second) {
        assertThat(new MissChecker(false).availableSecondShots(Pins.of(first), Pins.of(second)))
                .isTrue();

        assertThat(new MissChecker(true).availableSecondShots(Pins.of(first), Pins.of(second)))
                .isFalse();
    }
}
