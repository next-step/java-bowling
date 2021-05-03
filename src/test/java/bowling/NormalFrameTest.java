package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Optional;
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
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore.get()).isEqualTo(expectScore);
    }

    private static Stream<Arguments> getFrameScore_계산_안된_상태() {
        return Stream.of(
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), Optional.empty()),
                arguments(new NormalFrame(Arrays.asList(Score.GUTTER, Score.SPARE)), Optional.empty()),
                arguments(new NormalFrame(Arrays.asList(Score.THREE, Score.SPARE)), Optional.empty()),
                arguments(new NormalFrame(Arrays.asList(Score.FOUR, Score.SPARE)), Optional.empty())

        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_계산_안된_상태")
    public void getFrameScore_계산_안된_상태(Frame frame, Optional<Integer> expectScore) {
        // given
        Frame nextFrame = new NormalFrame();
        nextFrame.nextFrame(new NormalFrame());
        frame.nextFrame(nextFrame);


        // when
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);
    }

    private static Stream<Arguments> getFrameScore_spare() {
        return Stream.of(
                arguments(new NormalFrame(Arrays.asList(Score.TWO, Score.SPARE)), new NormalFrame(Arrays.asList(Score.TWO, Score.SPARE)), Optional.of(12)),
                arguments(new NormalFrame(Arrays.asList(Score.FIVE, Score.SPARE)), new NormalFrame(Arrays.asList(Score.STRIKE)), Optional.of(20)),
                arguments(new NormalFrame(Arrays.asList(Score.SIX, Score.SPARE)), new NormalFrame(Arrays.asList(Score.GUTTER, Score.GUTTER)), Optional.of(10)),
                arguments(new NormalFrame(Arrays.asList(Score.NINE, Score.SPARE)), new NormalFrame(Arrays.asList(Score.GUTTER, Score.FIVE)), Optional.of(10)),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new FinalFrame(Arrays.asList(Score.STRIKE, Score.STRIKE)), Optional.of(30)),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new FinalFrame(Arrays.asList(Score.STRIKE)), Optional.empty())
        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_spare")
    public void getFrameScore_스페어(Frame frame, Frame nextFrame, Optional<Integer> expectScore) {
        // given
        frame.nextFrame(nextFrame);

        // when
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);

    }

    private static Stream<Arguments> getFrameScore_strike_2개() {
        return Stream.of(
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.GUTTER, Score.FIVE)), 20),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), 30),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.FIVE)), 25),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.STRIKE)), new FinalFrame(Arrays.asList(Score.STRIKE)), 30)
        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_strike_2개")
    public void getFrameScore_스트라이크_다음도스트라이크(Frame frame, Frame nextFrame1, Frame nextFrame2, int expectScore) {
        // given
        frame.nextFrame(nextFrame1);
        nextFrame1.nextFrame(nextFrame2);

        // when
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore.get()).isEqualTo(expectScore);
    }

    private static Stream<Arguments> getFrameScore_strike_1개() {
        return Stream.of(
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.GUTTER, Score.GUTTER)), 10),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.FIVE, Score.SPARE)), 20),
                arguments(new NormalFrame(Arrays.asList(Score.STRIKE)), new NormalFrame(Arrays.asList(Score.THREE, Score.ONE)), 14)

        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_strike_1개")
    public void getFrameScore_스트라이크(Frame frame, Frame nextFrame1, int expectScore) {
        // given
        frame.nextFrame(nextFrame1);

        // when
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore.get()).isEqualTo(expectScore);
    }

    @Test
    public void getFrameScore_void() {
        // given
        Frame frame = new NormalFrame();

        // when
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(Optional.empty());
    }
}
