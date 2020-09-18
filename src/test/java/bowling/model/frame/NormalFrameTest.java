package bowling.model.frame;

import bowling.model.delivery.NormalDeliveryEntry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private static Stream<Arguments> provideForRoll() {
        return Stream.of(
                Arguments.of(2, 8, NormalDeliveryEntry.of(2)),
                Arguments.of(3, 0, NormalDeliveryEntry.of(3)),
                Arguments.of(4, 5, NormalDeliveryEntry.of(4)));
    }

    @ParameterizedTest
    @MethodSource("provideForRoll")
    @DisplayName("투구 진행하기")
    void roll(int firstPins, int secondPins, NormalDeliveryEntry expectedEntry) {
        // given
        Frame normalFrame = NormalFrame.of(firstPins);
        expectedEntry.roll(secondPins);
        NormalFrame expected = new NormalFrame(expectedEntry);

        // when
        Frame result = normalFrame.roll(secondPins);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForIsEnd() {
        return Stream.of(
                Arguments.of(false, 2, null),
                Arguments.of(true, 1, 5),
                Arguments.of(true, 3, 7),
                Arguments.of(true, 10, null));
    }

    @ParameterizedTest
    @MethodSource("provideForIsEnd")
    @DisplayName("투구 끝났는지 확인하기")
    void isEnd(boolean expected, Integer firstPins, Integer secondPins) {
        // given
        Frame normalFrame = NormalFrame.of(firstPins);
        if (Objects.nonNull(secondPins)) {
            normalFrame = normalFrame.roll(secondPins);
        }

        // when
        boolean result = normalFrame.isEnd();

        // then
        assertThat(result).isEqualTo(expected);
    }

}
