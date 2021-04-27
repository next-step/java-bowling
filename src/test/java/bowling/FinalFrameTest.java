package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FinalFrameTest {

    private static Stream<Arguments> FinalFrame_프레임이_끝난_경우_2번_던지기() {
        return Stream.of(
                arguments(0, 5),
                arguments(3, 4),
                arguments(4, 3),
                arguments(7, 1),
                arguments(8, 1)

        );
    }


    @ParameterizedTest
    @MethodSource("FinalFrame_프레임이_끝난_경우_2번_던지기")
    public void FinalFrame_프레임이_끝난_경우_2번_던지기(int score1, int score2) {
        // given
        Frame finalFrame = FinalFrame.valueOf(Arrays.asList(score1, score2));

        // when

        // then
        assertThat(finalFrame.isFinished()).isTrue();
    }

    private static Stream<Arguments> FinalFrame_프레임이_끝난_경우_3번_던지기() {
        return Stream.of(
                arguments(1, 9, 3),
                arguments(2, 8, 10),
                arguments(5, 5, 0),
                arguments(6, 4, 2),
                arguments(9, 1, 5),
                arguments(10, 10, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("FinalFrame_프레임이_끝난_경우_3번_던지기")
    public void FinalFrame_프레임이_끝난_경우_3번_던지기(int score1, int score2, int score3) {
        // given
        Frame finalFrame = FinalFrame.valueOf(Arrays.asList(score1, score2, score3));

        // when

        // then
        assertThat(finalFrame.isFinished()).isTrue();
    }

    private static Stream<Arguments> FinalFrame_프레임이_끝나지_않은_경우_2번_던지기() {
        return Stream.of(
                arguments(1, 9),
                arguments(2, 8),
                arguments(5, 5),
                arguments(6, 4),
                arguments(9, 1),
                arguments(10, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("FinalFrame_프레임이_끝나지_않은_경우_2번_던지기")
    public void FinalFrame_프레임이_끝나지_않은_경우_2번_던지기(int score1, int score2) {
        // given
        Frame finalFrame = FinalFrame.valueOf(Arrays.asList(score1, score2));

        // when

        // then
        assertThat(finalFrame.isFinished()).isFalse();
    }

    private static Stream<Arguments> FinalFrame_프레임이_끝나지_않은_경우_1번_던지기() {
        return Stream.of(
                arguments(0),
                arguments(1),
                arguments(2),
                arguments(3),
                arguments(4),
                arguments(5),
                arguments(6),
                arguments(7),
                arguments(8),
                arguments(9),
                arguments(10)
        );
    }

    @ParameterizedTest
    @MethodSource("FinalFrame_프레임이_끝나지_않은_경우_1번_던지기")
    public void FinalFrame_프레임이_끝나지_않은_경우_1번_던지기(int score1) {
        // given
        Frame finalFrame = FinalFrame.valueOf(Arrays.asList(score1));

        // when

        // then
        assertThat(finalFrame.isFinished()).isFalse();
    }

}
