package bowling;

import bowling.domain.frame.FinalFrame;
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

public class FinalFrameTest {

    private static Stream<Arguments> getFrameScore_normal() {
        return Stream.of(
                arguments(new FinalFrame(Arrays.asList(Score.GUTTER, Score.FIVE)), 5),
                arguments(new FinalFrame(Arrays.asList(Score.THREE, Score.FOUR)), 7),
                arguments(new FinalFrame(Arrays.asList(Score.FOUR, Score.THREE)), 7),
                arguments(new FinalFrame(Arrays.asList(Score.SEVEN, Score.ONE)), 8),
                arguments(new FinalFrame(Arrays.asList(Score.EIGHT, Score.ONE)), 9),
                arguments(new FinalFrame(Arrays.asList(Score.GUTTER, Score.GUTTER)), 0),
                arguments(new FinalFrame(Arrays.asList(Score.TWO, Score.SPARE, Score.FIVE)), 15),
                arguments(new FinalFrame(Arrays.asList(Score.FIVE, Score.SPARE, Score.STRIKE)), 20),
                arguments(new FinalFrame(Arrays.asList(Score.FIVE, Score.SPARE, Score.GUTTER)), 10),
                arguments(new FinalFrame(Arrays.asList(Score.STRIKE, Score.FIVE, Score.SPARE)), 20),
                arguments(new FinalFrame(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.STRIKE)), 30),
                arguments(new FinalFrame(Arrays.asList(Score.STRIKE, Score.GUTTER, Score.GUTTER)), 10)
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
}
