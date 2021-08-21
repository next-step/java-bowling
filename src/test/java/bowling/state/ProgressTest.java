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

class ProgressTest {
    private Progress progress;

    @BeforeEach
    void setUp() {
        final Pin downedPins = Pin.from(5);
        progress = Progress.from(downedPins);
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("Progress상태일 경우 이전 투구에 쓰러진과 현재 투구에 쓰러트린 Pin의 합이 총 10개면 Spare를 반환하고, 아닐 경우 Miss를 반환한다")
    void nextPitch(final Pin downedPins, final Class expectedClass) {
        final State state = progress.nextPitch(downedPins);
        Assertions.assertThat(state).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> nextPitch() {
        return Stream.of(
                Arguments.of(Pin.from(5), Spare.class),
                Arguments.of(Pin.from(3), Miss.class)
        );
    }

    @Test
    @DisplayName("Progress상태는 현재 처리한 Pin의 개수를 반환한다")
    void getScore() {
        Assertions.assertThat(progress.getScore()).containsExactly(5);
    }

    @Test
    @DisplayName("Progress는 Frame을 더 진행 할 수 있다")
    void isEnd() {
        Assertions.assertThat(progress.isEnd()).isFalse();
    }

    @Test
    @DisplayName("Progress상태일 경우 레인에 Pin이 남아있음을 의미한다")
    void isClean() {
        Assertions.assertThat(progress.isClean()).isFalse();
    }
}
