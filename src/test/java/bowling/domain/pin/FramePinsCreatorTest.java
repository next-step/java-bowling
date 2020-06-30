package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FramePinsCreatorTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("핀이 10이 아닌 경우 FirstFramePins 를 반환한다.")
    void return_framePins(int countOfPins) {
        assertThat(FramePinsCreator.next(Pins.of(countOfPins)))
                .isEqualTo(FirstFramePins.of(Pins.of(countOfPins)));
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    @DisplayName("핀이 10인 경우 Strike 를 반환한다.")
    void return_strike(int countOfPins) {
        assertThat(FramePinsCreator.next(Pins.of(countOfPins)))
                .isEqualTo(Strike.of(Pins.of(countOfPins)));
    }
}