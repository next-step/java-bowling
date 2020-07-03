package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {
    @ParameterizedTest
    @CsvSource({"1,2", "2,3", "3,4"})
    @DisplayName("Miss 의 경우 두개의 핀 값을 전달받는다.")
    void create_miss(int firstPins, int secondPins) {
        assertThat(Miss.of(Pins.of(firstPins), Pins.of(secondPins))).isNotNull();
    }
}