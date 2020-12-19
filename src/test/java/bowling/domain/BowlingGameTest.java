package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    private static Stream<Arguments> makeScenario() {
        List<DownedPin> scenario1 = Arrays.asList(
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(4), DownedPin.fromNumber(6),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(5), DownedPin.fromNumber(5),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4)
        );

        List<DownedPin> scenario2 = Arrays.asList(
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(4), DownedPin.fromNumber(6),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(5), DownedPin.fromNumber(5),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(10), DownedPin.fromNumber(5), DownedPin.fromNumber(4)
        );

        List<DownedPin> scenario3 = Arrays.asList(
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(4), DownedPin.fromNumber(6),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(5), DownedPin.fromNumber(5),
                DownedPin.fromNumber(10),
                DownedPin.fromNumber(4), DownedPin.fromNumber(4),
                DownedPin.fromNumber(6), DownedPin.fromNumber(4), DownedPin.fromNumber(9)
        );

        return Stream.of(
                Arguments.of(scenario1),
                Arguments.of(scenario2),
                Arguments.of(scenario3)
        );
    }

    @ParameterizedTest
    @MethodSource("makeScenario")
    @DisplayName("")
    void testRun(List<DownedPin> pins) {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < pins.size() - 1; i++) {
            game.record(pins.get(i));
            assertThat(game.isEnd()).isFalse();
        }

        game.record(pins.get(pins.size() - 1));
        assertThat(game.isEnd()).isTrue();
    }
}
