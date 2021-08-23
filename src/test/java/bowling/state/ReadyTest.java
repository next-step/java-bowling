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

class ReadyTest {
    private Ready ready;

    @BeforeEach
    void setUp() {
        ready = Ready.init();
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("Ready상태일 경우 쓰러진 Pin이 10개면 Strike를 반환하고, 아닐 경우 Progress를 반환한다")
    void nextPitch(final Pin downedPins, final Class expectedClass) {
        final State state = ready.nextPitch(downedPins);
        Assertions.assertThat(state).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> nextPitch() {
        return Stream.of(
                Arguments.of(Pin.from(Pin.MAX_COUNT_OF_PIN), Strike.class),
                Arguments.of(Pin.from(Pin.MIN_COUNT_OF_PIN), Progress.class)
        );
    }

    @Test
    @DisplayName("Ready상태는 반환 할 점수가 없다")
    void getScore() {
        Assertions.assertThat(ready.getScore()).isEmpty();
    }

    @Test
    @DisplayName("Ready는 Frame을 더 진행 할 수 있다")
    void isEnd() {
        Assertions.assertThat(ready.isEnd()).isFalse();
    }

    @Test
    @DisplayName("Ready상태일 경우 레인에 Pin이 남아있음을 의미한다")
    void isClean() {
        Assertions.assertThat(ready.isClean()).isFalse();
    }
}
