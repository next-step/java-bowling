package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("NormalFrame Test")
public class NormalFrameTest {

    static Stream<Arguments> provideFinishCheckFrame() {
        return Stream.of(
                Arguments.of(new int[]{10}, true),
                Arguments.of(new int[]{9, 1}, true),
                Arguments.of(new int[]{5, 4}, true),
                Arguments.of(new int[]{0, 0}, true),
                Arguments.of(new int[]{9}, false),
                Arguments.of(new int[]{1}, false)
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideFinishCheckFrame")
    @DisplayName("완료된 프레임인지 확인한다.")
    void finishFrameCheck(int[] numbers, boolean actual) {
        // given
        Frame frame = NormalFrame.of(1, numbers);

        // when
        boolean finished = frame.isFinished();

        // then
        assertThat(finished).isEqualTo(actual);
    }

    @Test
    @DisplayName("다음 프레임 => 현재 프레임")
    void nextFrameIsCurrentFrame() {
        // given
        Frame frame = NormalFrame.of(1);
        frame.inputKnockDownNumber(5);

        // when
        Frame nextFrame = frame.nextFrame();

        assertThat(frame).isEqualTo(nextFrame);
    }

    @Test
    @DisplayName("다음 프레임  => 다음 프레임")
    void nextFrameIsNewFrame() {
        // given
        Frame frame = NormalFrame.of(1);
        frame.inputKnockDownNumber(10);

        // when
        Frame nextFrame = frame.nextFrame();

        assertThat(frame).isNotEqualTo(nextFrame);
    }
}
