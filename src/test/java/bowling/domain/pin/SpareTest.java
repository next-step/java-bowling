package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {
    @ParameterizedTest
    @CsvSource({"1,10", "2,9", "3,8"})
    @DisplayName("스페어는 투구들의 합이 10이 아닌 경우 생성할 수 없다.")
    void validate_spare(int firstPins, int secondPins) {
        assertThatThrownBy(() -> Spare.of(Pins.of(firstPins), Pins.of(secondPins)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}