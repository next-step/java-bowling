package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FramePinsCreatorTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("핀이 10이 아닌 경우 FirstFramePins 를 반환한다.")
    void return_framePins(int countOfPins) {
        assertThat(FramePinsCreator.next(null, Pins.of(countOfPins)))
                .isEqualTo(FirstFramePins.of(Pins.of(countOfPins)));
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    @DisplayName("핀이 10인 경우 Strike 를 반환한다.")
    void return_strike(int countOfPins) {
        assertThat(FramePinsCreator.next(null, Pins.of(countOfPins)))
                .isEqualTo(Strike.of(Pins.of(countOfPins)));
    }

    @ParameterizedTest
    @CsvSource({"1,9", "2,8"})
    @DisplayName("두 번의 투구를 통해 10 개의 핀을 쓰러트리면 Spare 를 반환한다.")
    void return_spare(int countOfFirstPins, int countOfSecondPins) {
        Pins firstPins = Pins.of(countOfFirstPins);
        Pins secondPins = Pins.of(countOfSecondPins);

        FramePins framePins = FramePinsCreator.next(null, firstPins);
        assertThat(FramePinsCreator.next(framePins, secondPins))
                .isEqualTo(Spare.of(firstPins, secondPins));
    }

    @ParameterizedTest
    @CsvSource({"0,0"})
    @DisplayName("두 번의 투구를 통해 0 개의 핀을 쓰러트리면 Gutter 를 반환한다.")
    void return_gutter(int countOfFirstPins, int countOfSecondPins) {
        Pins firstPins = Pins.of(countOfFirstPins);
        Pins secondPins = Pins.of(countOfSecondPins);

        FramePins framePins = FramePinsCreator.next(null, firstPins);
        assertThat(FramePinsCreator.next(framePins, secondPins))
                .isEqualTo(Gutter.of());
    }

    @ParameterizedTest
    @CsvSource({"1,0", "1,2", "3,4"})
    @DisplayName("두 번의 투구를 통해 쓰러트린 핀이 0 개도 10개도 아닌 경우 Miss 를 반환한다.")
    void return_strike(int countOfFirstPins, int countOfSecondPins) {
        Pins firstPins = Pins.of(countOfFirstPins);
        Pins secondPins = Pins.of(countOfSecondPins);

        FramePins framePins = FramePinsCreator.next(null, firstPins);
        assertThat(FramePinsCreator.next(framePins, secondPins))
                .isEqualTo(Miss.of(firstPins, secondPins));
    }
}