package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.Score;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NormalFrameTest {
    private static Stream<Arguments> getFrameScore_normal() {
        return Stream.of(
                arguments(new NormalFrame(Arrays.asList(Score.GUTTER, Score.FIVE)), 5),
                arguments(new NormalFrame(Arrays.asList(Score.THREE, Score.FOUR)), 7),
                arguments(new NormalFrame(Arrays.asList(Score.FOUR, Score.THREE)), 7),
                arguments(new NormalFrame(Arrays.asList(Score.SEVEN, Score.ONE)), 8),
                arguments(new NormalFrame(Arrays.asList(Score.EIGHT, Score.ONE)), 9),
                arguments(new NormalFrame(Arrays.asList(Score.GUTTER, Score.GUTTER)), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_normal")
    public void getFrameScore_일반(Frame frame, int expectScore) {
        // given


        // when
        int resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);
    }

    private static Stream<Arguments> getFrameScore_spare() {
        return Stream.of(

                arguments(new NormalFrame(Arrays.asList(Score.TWO, Score.SPARE)), new NormalFrame(Arrays.asList(Score.TWO, Score.SPARE)), 12),
                arguments(new NormalFrame(Arrays.asList(Score.FIVE, Score.SPARE)), new NormalFrame(Arrays.asList(Score.STRIKE)), 20),
                arguments(new NormalFrame(Arrays.asList(Score.SIX, Score.SPARE)), new NormalFrame(Arrays.asList(Score.GUTTER, Score.GUTTER)), 10),
                arguments(new NormalFrame(Arrays.asList(Score.NINE, Score.SPARE)), new NormalFrame(Arrays.asList(Score.GUTTER, Score.FIVE)), 10)
        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_spare")
    public void getFrameScore_스페어(Frame frame, Frame nextFrame, int expectScore) {
        // given
        frame.nextFrame(nextFrame);

        // when
        int resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);

    }

    private static Stream<Arguments> getFrameScore_strike_2개() {
        return Stream.of(
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.GUTTER, Score.FIVE)), 20),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), 30),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.FIVE)), 25)

        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_strike_2개")
    public void getFrameScore_스트라이크_다음도스트라이크(Frame frame, Frame nextFrame1, Frame nextFrame2, int expectScore) {
        // given
        frame.nextFrame(nextFrame1);
        nextFrame1.nextFrame(nextFrame2);

        // when
        int resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);

    }

    private static Stream<Arguments> getFrameScore_strike_1개() {
        return Stream.of(
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.GUTTER, Score.GUTTER)), 10),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.FIVE, Score.SPARE)), 20),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.THREE, Score.ONE)), 13)

        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_strike_1개")
    public void getFrameScore_스트라이크() {
        // given


        // when


        // then


    }
}
