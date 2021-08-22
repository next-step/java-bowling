package bowling.frame;

import bowling.dto.StateDtos;
import bowling.pin.Pin;
import bowling.state.LastStateProxy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LastFrameTest {
    private LastFrame lastFrame;

    @BeforeEach
    void setUp() {
        lastFrame = LastFrame.init(LastStateProxy.init());
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("3번의 투구를 모두 하는 경우의 수")
    void play(final Pin firstDownedPins, final Pin secondDownedPins, final Pin thirdDownedPins) throws Exception {
        lastFrame.play(firstDownedPins);
        lastFrame.play(secondDownedPins);
        lastFrame.play(thirdDownedPins);
        Assertions.assertThat(lastFrame.hasTurn()).isFalse();
    }

    private static Stream<Arguments> play() {
        return Stream.of(
                Arguments.of(Pin.from(Pin.MAX_COUNT_OF_PIN), Pin.from(Pin.MAX_COUNT_OF_PIN), Pin.from(Pin.MAX_COUNT_OF_PIN)),
                Arguments.of(Pin.from(Pin.MAX_COUNT_OF_PIN), Pin.from(5), Pin.from(5)),
                Arguments.of(Pin.from(Pin.MIN_COUNT_OF_PIN), Pin.from(Pin.MAX_COUNT_OF_PIN), Pin.from(Pin.MAX_COUNT_OF_PIN)),
                Arguments.of(Pin.from(Pin.MIN_COUNT_OF_PIN), Pin.from(Pin.MAX_COUNT_OF_PIN), Pin.from(3)),
                Arguments.of(Pin.from(5), Pin.from(5), Pin.from(Pin.MAX_COUNT_OF_PIN)),
                Arguments.of(Pin.from(5), Pin.from(5), Pin.from(3))
        );
    }

    @Test
    void convert() throws Exception {
        Assertions.assertThat(lastFrame.convertToStateDtos()).isInstanceOf(StateDtos.class);
    }
}
