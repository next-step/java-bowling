package bowling;

import bowling.domain.frame.Frames;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FramesTest {

    private static Stream<Arguments> nowFrameArguments() {
        return Stream.of(
                arguments(Arrays.asList(1, 5, 3, 4, 2, 3), 3),
                arguments(Arrays.asList(1, 9, 10, 10, 10, 10), 5),
                arguments(Arrays.asList(1, 5, 3, 4, 2, 3, 10, 10, 10, 10, 10, 10, 10, 1), 9),
                arguments(Arrays.asList(), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("nowFrameArguments")
    public void nowFrame(List<Integer> scores, int expectNowFrame) throws Exception {
        // given
        Frames frames = new Frames();
        for (int score : scores) {
            frames = frames.addScore(score);
        }

        // when

        // then
        assertThat(frames.nowFrame()).isEqualTo(expectNowFrame);
    }

    private static Stream<Arguments> isFinished() {
        return Stream.of(
                arguments(Arrays.asList(1, 5, 3, 4, 2, 3), false),
                arguments(Arrays.asList(1, 9, 10, 10, 10, 10), false),
                arguments(Arrays.asList(1, 5, 3, 4, 2, 3, 10, 10, 10, 10, 10, 10, 10, 1, 9), true),
                arguments(Arrays.asList(1, 5, 3, 4, 2, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10), true),
                arguments(Arrays.asList(1, 5, 3, 4, 2, 3, 10, 10, 10, 10, 10, 10, 1, 3), true),
                arguments(Arrays.asList(), false)
        );
    }

    @ParameterizedTest
    @MethodSource("isFinished")
    public void isFinished(List<Integer> scores, boolean expectIsFinished) throws Exception {
        // given
        Frames frames = new Frames();
        for (int score : scores) {
            frames = frames.addScore(score);
        }

        // when

        // then
        assertThat(frames.isFinished()).isEqualTo(expectIsFinished);
    }
}
