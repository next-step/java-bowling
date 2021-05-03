package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FinalFrameTest {

    private static Stream<Arguments> getFrameScore_1() {
        return Stream.of(
                arguments(3, Optional.empty()),
                arguments(10, Optional.empty()),
                arguments(0, Optional.empty())

        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_1")
    public void getFrameScore_1개(int score1, Optional<Integer> expectScore) throws Exception {
        // given
        Frame frame = new FinalFrame();
        frame.addScore(score1);

        // when
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);
    }

    private static Stream<Arguments> getFrameSgetFrameScore_계산_안된_상태_2() {
        return Stream.of(
                arguments(3, 7, Optional.empty()),
                arguments(10, 3, Optional.empty()),
                arguments(0, 5, Optional.of(5)),
                arguments(4, 3, Optional.of(7)),
                arguments(7, 1, Optional.of(8)),
                arguments(8, 1, Optional.of(9)),
                arguments(0, 0, Optional.of(0))
        );
    }

    @ParameterizedTest
    @MethodSource("getFrameSgetFrameScore_계산_안된_상태_2")
    public void getFrameScore_2개(int score1, int score2, Optional<Integer> expectScore) throws Exception {
        // given
        Frame frame = new FinalFrame();
        frame.addScore(score1);
        frame.addScore(score2);

        // when
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);
    }

    private static Stream<Arguments> getFrameScore_3() {
        return Stream.of(
                arguments(2, 8, 5, Optional.of(15)),
                arguments(5, 5, 10, Optional.of(20)),
                arguments(5, 5, 0, Optional.of(10)),
                arguments(10, 5, 5, Optional.of(20)),
                arguments(10, 10, 10, Optional.of(30)),
                arguments(10, 0, 0, Optional.of(10))
        );
    }

    @ParameterizedTest
    @MethodSource("getFrameScore_3")
    public void getFrameScore_3개(int score1, int score2, int score3, Optional<Integer> expectScore) throws Exception {
        // given
        Frame frame = new FinalFrame();
        frame.addScore(score1);
        frame.addScore(score2);
        frame.addScore(score3);

        // when
        Optional<Integer> resultScore = frame.frameScore();

        // then
        assertThat(resultScore).isEqualTo(expectScore);
    }

    @Test
    public void getFrameScore_0개() {
        //given
        Frame frame = new FinalFrame();

        //when
        Optional<Integer> resultScore = frame.frameScore();

        //then
        assertThat(resultScore).isEqualTo(Optional.empty());
    }

}
