package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFramesTest {

    private static Stream<Arguments> makeScenario() {
        List<DownedPin> scenario1 = Arrays.asList(
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10)
        );

        List<DownedPin> scenario2 = Arrays.asList(
                DownedPin.fromNumber(5), DownedPin.fromNumber(4),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(6), DownedPin.fromNumber(3),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(2), DownedPin.fromNumber(3),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(10)
        );

        return Stream.of(
                Arguments.of(scenario1),
                Arguments.of(scenario2)
        );
    }

    @ParameterizedTest
    @MethodSource("makeScenario")
    @DisplayName("모든 일반 프레임이 종료되었는지 확인")
    void testCheckingComplete(List<DownedPin> tries) {
        NormalFrames normalFrames = new NormalFrames();

        for (int i = 0; i < tries.size() - 1; i++) {
            normalFrames.record(tries.get(i));
        }

        normalFrames.record(tries.get(tries.size() - 1));
        assertThat(normalFrames.isEnd()).isTrue();
    }
}
