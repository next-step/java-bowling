package bowling.model.frame;

import bowling.model.delivery.FinalDeliveryEntry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private static Stream<Arguments> provideForRoll() {
        return Stream.of(
                Arguments.of(2, 8, 9),
                Arguments.of(10, 2, 0),
                Arguments.of(0, 10, 3));
    }

    @ParameterizedTest
    @MethodSource("provideForRoll")
    @DisplayName("투구 진행하기 : 보너스 투구까지 가능한 경우")
    void roll(int firstPins, int secondPins, int thirdPins) {
        // given
        Frame finalFrame = FinalFrame.firstRoll(firstPins);

        FinalDeliveryEntry expectedEntry = new FinalDeliveryEntry(firstPins);
        expectedEntry.roll(secondPins);
        expectedEntry.roll(thirdPins);
        Frame expected = new FinalFrame(expectedEntry);

        // when
        finalFrame = finalFrame.roll(secondPins);
        finalFrame = finalFrame.roll(thirdPins);

        // then
        assertThat(finalFrame).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForIsEnd() {
        return Stream.of(
                Arguments.of(false, Arrays.asList(10, 2)),
                Arguments.of(true, Arrays.asList(5, 2)),
                Arguments.of(false, Arrays.asList(2, 8)),
                Arguments.of(true, Arrays.asList(3, 7, 10)));
    }

    @ParameterizedTest
    @MethodSource("provideForIsEnd")
    @DisplayName("투구 끝났는지 확인하기")
    void isEnd(boolean expected, List<Integer> pins) {
        // given
        Frame finalFrame = FinalFrame.firstRoll(pins.get(0));
        for (int i = 1; i < pins.size(); i++) {
            finalFrame = finalFrame.roll(pins.get(i));
        }

        // when
        boolean result = finalFrame.isEnd();

        // then
        assertThat(result).isEqualTo(expected);
    }

}
