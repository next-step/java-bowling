package bowling.domain.bowlinggame;

import bowling.domain.frame.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PinStatusTest {

    private static Stream<Arguments> makeScenarioOnTwoPitch() {
        return Stream.of(
                Arguments.of(3, 6, " 3 | 6 "),
                Arguments.of(6, 4, " 6 | / "),
                Arguments.of(0, 10, " - | / "),
                Arguments.of(0, 0, " - | - ")
        );
    }

    @Test
    @DisplayName("Pin 상태 번역(스트라이크)")
    void testIsStrike() {
        List<DownedPin> downedPins = new ArrayList<>();
        downedPins.add(DownedPin.fromNumber(10));

        assertThat(PinStatus.interpretPinsStatus(downedPins)).isEqualTo(" X ");
    }

    @ParameterizedTest
    @MethodSource("makeScenarioOnTwoPitch")
    @DisplayName("Pin 상태 번역(스페어)")
    void testIsSpare(int firstPitchDownedPin, int secondPitchDownedPin, String expected) {
        List<DownedPin> downedPins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(firstPitchDownedPin);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(secondPitchDownedPin);
        downedPins.add(firstPitch);
        downedPins.add(secondPitch);

        assertThat(PinStatus.interpretPinsStatus(downedPins)).isEqualTo(expected);
    }
}
