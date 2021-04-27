package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NormalFrameTest {

    private static Stream<Arguments> NormalFrame_프레임이_끝난_경우() {
        return Stream.of(
                arguments(0, 5),
                arguments(1, 9),
                arguments(2, 8),
                arguments(3, 4),
                arguments(4, 3),
                arguments(5, 5),
                arguments(6, 4),
                arguments(7, 1),
                arguments(8, 1),
                arguments(9, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("NormalFrame_프레임이_끝난_경우")
    public void NormalFrameIsFinished_프레임이_끝난_경우1(int score1, int score2) throws Exception {
        // given
        Frame normalFrame = new NormalFrame();

        // when
        normalFrame = normalFrame.addScore(score1);
        normalFrame = normalFrame.addScore(score2);

        // then
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    public void NormalFrameIsFinished_프레임이_끝난_경우2() throws Exception {
        // given
        Frame normalFrame = new NormalFrame();

        // when
        normalFrame = normalFrame.addScore(10);

        // then
        assertThat(normalFrame.isFinished()).isTrue();
    }

    private static Stream<Arguments> NormalFrame_프레임이_안끝난_경우() {
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
                arguments(9)
        );
    }

    @ParameterizedTest
    @MethodSource("NormalFrame_프레임이_안끝난_경우")
    public void NormalFrameIsFinished_프레임이_안끝난_경우(int score) throws Exception {
        // given
        Frame normalFrame = new NormalFrame();

        // when
        normalFrame = normalFrame.addScore(score);

        // then
        assertThat(normalFrame.isFinished()).isFalse();
    }
}
