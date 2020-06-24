package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("State enum 테스트")
public class StateTest {

    @DisplayName("일치하는 핀 갯수를 State 타입으로 변경할 수 있다.")
    @ParameterizedTest
    @MethodSource
    void valueOf(int fallenPins, State expected, boolean isSpare) {
        State actual = State.valueOf(fallenPins, isSpare);
        assertThat(actual).isEqualTo(expected);

    }

    private static Stream<Arguments> valueOf() {
        return Stream.of(
                Arguments.of(0, State.GUTTER, false),
                Arguments.of(1, State.ONE, false),
                Arguments.of(2, State.TWO, false),
                Arguments.of(3, State.THREE, false),
                Arguments.of(4, State.FOUR, false),
                Arguments.of(5, State.FIVE, false),
                Arguments.of(6, State.SIX, false),
                Arguments.of(7, State.SEVEN, false),
                Arguments.of(8, State.EIGHT, false),
                Arguments.of(9, State.NINE, false),
                Arguments.of(10, State.STRIKE, false),
                Arguments.of(10, State.SPARE, true)
        );
    }

    @Test
    void strike() {
        int previousFallenPins = 0;
        int nextFallenPins = 10;
        int statesLength = 0;

        State actual = State.bowl(previousFallenPins, nextFallenPins, statesLength);

        assertThat(actual).isEqualTo(State.STRIKE);
    }

    @Test
    void gutter() {
        int previousFallenPins = 0;
        int nextFallenPins = 0;
        int statesLength = 0;

        State actual = State.bowl(previousFallenPins, nextFallenPins, statesLength);

        assertThat(actual).isEqualTo(State.GUTTER);
    }

    @Test
    void spare() {
        int previousFallenPins = 2;
        int nextFallenPins = 8;
        int statesLength = 0;

        State actual = State.bowl(previousFallenPins, nextFallenPins, statesLength);

        assertThat(actual).isEqualTo(State.SPARE);
    }

    @Test
    void one() {
        int previousFallenPins = 0;
        int nextFallenPins = 1;
        int statesLength = 0;

        State actual = State.bowl(previousFallenPins, nextFallenPins, statesLength);

        assertThat(actual).isEqualTo(State.ONE);
    }

    @Test
    void validateFallenPinSum() {
        int previousFallenPins = 2;
        int nextFallenPins = 9;
        int statesLength = 0;

        assertThatExceptionOfType(FallenPinsSumException.class)
                .isThrownBy(() -> State.bowl(previousFallenPins, nextFallenPins, statesLength))
                .withMessage(FallenPinsSumException.MESSAGE);
    }
}
