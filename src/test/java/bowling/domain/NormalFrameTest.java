package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    static Stream<Arguments> next() {
        return Stream.of(
                Arguments.of(5, BowlingPins.of(10), NormalFrame.class),
                Arguments.of(9, BowlingPins.of(10), FinalFrame.class)
        );
    }

    @DisplayName("프레임의 첫번쨰 볼링핀이 10보다 작으면 현재 프레임을 반환한다")
    @ValueSource(ints = {1, 5, 9})
    @ParameterizedTest
    void first(int number) {
        NormalFrame frame = new NormalFrame(1);
        Frame resultFrame = frame.execute(BowlingPins.of(number));

        assertThat(resultFrame).isSameAs(frame);
    }

    @DisplayName("프레임의 첫번째 볼링핀이 10이면 다음 프레임을 반환한다. 단, 9프레임의 경우 FinalFrame을 반환한다")
    @MethodSource("next")
    @ParameterizedTest
    void nextTest(int frameNumber, BowlingPins bowlingPins, Class<?> expected) {
        NormalFrame frame = new NormalFrame(frameNumber);

        Frame execute = frame.execute(bowlingPins);

        assertAll(
                () -> assertThat(execute).isInstanceOf(expected),
                () -> assertThat(frameNumber + 1).isEqualTo(execute.getFrameNumber())
        );
    }

    @DisplayName("프레임의 첫번째 쓰러트린 볼링핀과 다음에 쓰러트린 볼링핀의 갯수가 10을 넘어가면 익셉션")
    @Test
    void exceed() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.execute(BowlingPins.of(9));

        assertThatThrownBy(() -> normalFrame.execute(BowlingPins.of(2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}