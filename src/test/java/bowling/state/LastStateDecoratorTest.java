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

public class LastStateDecoratorTest {
    private LastStateDecorator decorator;

    @BeforeEach
    void setUp() {
        decorator = LastStateDecorator.init();
    }

    @Test
    @DisplayName("마지막 프레임이 2턴만에 경우는 첫 투구에 Miss, 두번째 투구에도 Miss가 나는 경우 뿐이다")
    void nextPitch() throws Exception {
        decorator.nextPitch(Pin.from(5));
        decorator.nextPitch(Pin.from(3));
        Assertions.assertThat(decorator.isEnd()).isTrue();
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("3번의 투구를 모두 하는 경우의 수")
    void nextPitches(final Pin firstDownedPins, final Pin secondDownedPins, final Pin thirdDownedPins) throws Exception {
        decorator.nextPitch(firstDownedPins);
        decorator.nextPitch(secondDownedPins);
        decorator.nextPitch(thirdDownedPins);
        decorator.tailStateCheck();
        Assertions.assertThat(decorator.isEnd()).isTrue();
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
        Assertions.assertThat(decorator.isClean()).isFalse();
    }
}
