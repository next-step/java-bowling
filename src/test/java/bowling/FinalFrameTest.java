package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FinalFrameTest {

    private static Stream<Arguments> getFrameScore_1() {
        return Stream.of(
                arguments(3, 3),
                arguments(10, 10),
                arguments(0, 0)

        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_1")
    public void getFrameScore_1개(int score1, int expectScore) throws Exception {
        // given
        Frame frame = new FinalFrame();
        frame.addScore(score1);

        // when
        int resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);
    }

    private static Stream<Arguments> getFrameScore_2() {
        return Stream.of(
                arguments(3, 7, 10),
                arguments(0, 5, 5),
                arguments(10, 3, 13),
                arguments(4, 3, 7),
                arguments(7, 1, 8),
                arguments(8, 1, 9),
                arguments(0, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_2")
    public void getFrameScore_2개(int score1, int score2, int expectScore) throws Exception {
        // given
        Frame frame = new FinalFrame();
        frame.addScore(score1);
        frame.addScore(score2);

        // when
        int resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);
    }

    private static Stream<Arguments> getFrameScore_3() {
        return Stream.of(
                arguments(2, 8, 5, 15),
                arguments(5, 5, 10, 20),
                arguments(5, 5, 0, 10),
                arguments(10, 5, 5, 20),
                arguments(10, 10, 10, 30),
                arguments(10, 0, 0, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_3")
    public void getFrameScore_3개(int score1, int score2, int score3, int expectScore) throws Exception {
        // given
        Frame frame = new FinalFrame();
        frame.addScore(score1);
        frame.addScore(score2);
        frame.addScore(score3);

        // when
        int resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);
    }


}
