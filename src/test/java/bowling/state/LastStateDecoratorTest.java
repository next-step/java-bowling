package bowling.state;

import bowling.pin.Pin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static bowling.pin.Pin.*;

public class LastStateDecoratorTest {
    private LastStateDecorator decorator;

    @BeforeEach
    void setUp() {
        decorator = LastStateDecorator.init();
    }

    @Test
    @DisplayName("마지막 프레임이 2턴만에 경우는 첫 투구에 Miss, 두번째 투구에도 Miss가 나는 경우 뿐이다")
    void nextPitch() throws Exception {
        decorator.nextPitch(from(5));
        decorator.nextPitch(from(3));
        Assertions.assertThat(decorator.isEnd()).isTrue();
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("3번의 투구를 모두 하는 경우의 수")
    void nextPitches(final Pin firstDownedPins, final Pin secondDownedPins, final Pin thirdDownedPins, final int expectedSize) throws Exception {
        decorator.nextPitch(firstDownedPins);
        decorator.nextPitch(secondDownedPins);
        decorator.nextPitch(thirdDownedPins);
        decorator.tailStateCheck();
        Assertions.assertThat(decorator.getStates().size()).isEqualTo(expectedSize);
    }

    private static Stream<Arguments> nextPitches() {
        return Stream.of(
                Arguments.of(from(MAX_COUNT_OF_PIN), from(MAX_COUNT_OF_PIN), from(MAX_COUNT_OF_PIN), 3),
                Arguments.of(from(MAX_COUNT_OF_PIN), from(5), from(5), 2),
                Arguments.of(from(MIN_COUNT_OF_PIN), from(MAX_COUNT_OF_PIN), from(MAX_COUNT_OF_PIN), 2),
                Arguments.of(from(MIN_COUNT_OF_PIN), from(MAX_COUNT_OF_PIN), from(3), 2),
                Arguments.of(from(5), from(5), from(MAX_COUNT_OF_PIN), 2),
                Arguments.of(from(5), from(5), from(3), 2)
        );
    }

    @Test
    void isClean() throws Exception {
        Assertions.assertThat(decorator.isClean()).isFalse();
    }
}
