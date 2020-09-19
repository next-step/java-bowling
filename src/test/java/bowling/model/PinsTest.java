package bowling.model;

import bowling.ExceptionMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = {-2, -1, -10})
    @DisplayName("Pins 생성 실패 : 0이하인 경우 ")
    void create_fail_min(int countOfPins) {
        assertThatThrownBy(() -> Pins.of(countOfPins))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.PINS_MIN_EXCEPTION);
    }

    private static Stream<Arguments> provideForGetState() {
        return Stream.of(
                Arguments.of(10, State.STRIKE),
                Arguments.of(0, State.GUTTER),
                Arguments.of(5, State.MISS));
    }

    @ParameterizedTest
    @MethodSource("provideForGetState")
    @DisplayName("현재 상태 가져오기")
    void getNextState(int firstFallenPins, State expected) {
        // given
        Pins pins = Pins.of(firstFallenPins);

        // when
        State result = pins.getState();

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForGetNextState() {
        return Stream.of(
                Arguments.of(8, 2, State.SPARE),
                Arguments.of(2, 0, State.GUTTER),
                Arguments.of(3, 5, State.MISS));
    }

    @ParameterizedTest
    @MethodSource("provideForGetNextState")
    @DisplayName("다음 상태 가져오기")
    void getNextState(int firstFallenPins, int nextFallenPins, State expected) {
        // given
        Pins pins = Pins.of(firstFallenPins);

        // when
        State result = pins.getNextState(nextFallenPins);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"8:3", "5:6", "9:10"}, delimiter = ':')
    @DisplayName("다음 상태 가져오기 실패 : 넘어뜨릴 핀의 갯수가 남은 핀의 갯수보다 큰 경우")
    void getNextState_fail(int firstFallenPins, int nextFallenPins) {
        // given
        Pins pins = Pins.of(firstFallenPins);

        // when
        assertThatThrownBy(() -> pins.getNextState(nextFallenPins))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.PINS_LAST_PINS_EXCEPTION);
    }

}
