package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {

    private static Stream<Arguments> makeMissScenario() {
        List<DownedPin> scenario1 = new ArrayList<>();
        scenario1.add(DownedPin.fromNumber(6));
        scenario1.add(DownedPin.fromNumber(3));

        List<DownedPin> scenario2 = new ArrayList<>();
        scenario2.add(DownedPin.fromNumber(6));

        List<DownedPin> scenario3 = new ArrayList<>();

        return Stream.of(Arguments.of(scenario1),
                Arguments.of(scenario2),
                Arguments.of(scenario3));
    }

    @Test
    @DisplayName("Strike 상태")
    void testStrike() {
        List<DownedPin> downedPins = new ArrayList<>();
        downedPins.add(DownedPin.fromNumber(10));

        assertThat(FrameStatus.getStatus(downedPins))
                .isEqualTo(FrameStatus.STRIKE);
    }

    @Test
    @DisplayName("Spare 상태")
    void testSpare() {
        List<DownedPin> downedPins = new ArrayList<>();

        downedPins.add(DownedPin.fromNumber(3));
        downedPins.add(DownedPin.fromNumber(7));

        assertThat(FrameStatus.getStatus(downedPins))
                .isEqualTo(FrameStatus.SPARE);
    }

    @MethodSource("makeMissScenario")
    @ParameterizedTest
    @DisplayName("MISS 상태")
    void testMiss(List<DownedPin> downedPins) {
        assertThat(FrameStatus.getStatus(downedPins))
                .isEqualTo(FrameStatus.MISS);
    }
}
