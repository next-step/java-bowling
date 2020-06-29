package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FramePinsTest {
    @ParameterizedTest
    @MethodSource("parametersByCreateFramePins")
    @DisplayName("FramePins 투구의 총 합은 10 개을 초과할 수 없다.")
    void validate_FramePins(Pins firstPins, Pins secondPins) {
        assertThatThrownBy(() -> FramePins.of(firstPins, secondPins))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 프레임 당 투구의 합은 " + FramePins.MAX_PINS_PER_FRAME + "개를 초과할 수 없습니다.");
    }

    static Stream<Arguments> parametersByCreateFramePins() {
        return Stream.of(
                arguments(Pins.of(10), Pins.of(10)),
                arguments(Pins.of(1), Pins.of(10)),
                arguments(Pins.of(5), Pins.of(6)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 9})
    @DisplayName("FramePins 는 스트라이크인 경우 투구는 반드시 10개이어야 한다.")
    void validate_strike(int countOfPins) {
        assertThatThrownBy(() -> FramePins.strike(Pins.of(countOfPins)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("스트라이크의 투구는 반드시 " + FramePins.MAX_PINS_PER_FRAME + " 개 입니다.");
    }

    @ParameterizedTest
    @MethodSource("parametersByNPE")
    @DisplayName("FramePins 은 파라미터가 하나라도 null 이면 생성할 수 없다.")
    void validate_null(Pins firstPins, Pins secondPins) {
        assertThatThrownBy(() -> FramePins.of(firstPins, secondPins))
                .isInstanceOf(NullPointerException.class);
    }

    static Stream<Arguments> parametersByNPE() {
        return Stream.of(
                arguments(Pins.of(1), null),
                arguments(null, Pins.of(1)));
    }

}