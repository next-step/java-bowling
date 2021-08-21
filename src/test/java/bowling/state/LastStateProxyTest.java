package bowling.state;

import bowling.pin.Pin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LastStateProxyTest {
    private LastStateProxy proxy;

    @BeforeEach
    void setUp() {
        proxy = LastStateProxy.init();
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("3번의 투구를 모두 하는 경우의 수")
    void nextPitches(final Pin firstDownedPins, final Pin secondDownedPins, final Pin thirdDownedPins) throws Exception {
        proxy.nextPitch(firstDownedPins);
        proxy.nextPitch(secondDownedPins);

        final State endState = proxy.nextPitch(thirdDownedPins);

        Assertions.assertThat(proxy.isEnd()).isTrue();
        Assertions.assertThat(endState).isInstanceOf(End.class);
    }

    private static Stream<Arguments> nextPitches() {
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
    void isClean() throws Exception {
        Assertions.assertThat(proxy.isClean()).isFalse();
    }

    @Test
    @DisplayName("호출 시 마지막 프레임의 가장 마지막의 상태를 반환한다")
    void lastState() throws Exception {
        Pin dummyPin = Pin.from(0);

        State state = proxy.lastState();
        Assertions.assertThat(state).isInstanceOf(Ready.class);

        proxy.nextPitch(dummyPin);
        state = proxy.lastState();
        Assertions.assertThat(state).isInstanceOf(Progress.class);

        proxy.nextPitch(dummyPin);
        state = proxy.lastState();
        Assertions.assertThat(state).isInstanceOf(Miss.class);
    }
}
