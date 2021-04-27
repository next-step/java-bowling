package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.Score;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NormalFrameTest {

    private static Stream<Arguments> NormalFrame_addScore() {
        return Stream.of(
                arguments(0, 5, new NormalFrame(Arrays.asList(Score.GUTTER, Score.FIVE))),
                arguments(1, 9, new NormalFrame(Arrays.asList(Score.ONE, Score.SPARE))),
                arguments(2, 8, new NormalFrame(Arrays.asList(Score.TWO, Score.SPARE))),
                arguments(3, 4, new NormalFrame(Arrays.asList(Score.THREE, Score.FOUR))),
                arguments(4, 3, new NormalFrame(Arrays.asList(Score.FOUR, Score.THREE))),
                arguments(5, 5, new NormalFrame(Arrays.asList(Score.FIVE, Score.SPARE))),
                arguments(6, 4, new NormalFrame(Arrays.asList(Score.SIX, Score.SPARE))),
                arguments(7, 1, new NormalFrame(Arrays.asList(Score.SEVEN, Score.ONE))),
                arguments(8, 1, new NormalFrame(Arrays.asList(Score.EIGHT, Score.ONE))),
                arguments(9, 1, new NormalFrame(Arrays.asList(Score.NINE, Score.SPARE)))
        );
    }

    @ParameterizedTest
    @MethodSource("NormalFrame_addScore")
    public void NormalFrame_addScore(int score1, int score2, Frame expectFrame) throws Exception {
        // given
        Frame normalFrame = new NormalFrame();

        // when
        normalFrame.addScore(score1);
        normalFrame.addScore(score2);

        // then
        assertThat(normalFrame).isEqualTo(expectFrame);
    }


    @Test
    public void NormalFrame_addScore_STRIKE() throws Exception {
        // given
        Frame normalFrame = new NormalFrame();
        Frame expectFrame = new NormalFrame(Arrays.asList(Score.STRIKE));
        // when
        normalFrame.addScore(10);

        // then
        assertThat(normalFrame).isEqualTo(expectFrame);
    }


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
        normalFrame.addScore(score1);
        normalFrame.addScore(score2);

        // then
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    public void NormalFrameIsFinished_프레임이_끝난_경우2() throws Exception {
        // given
        Frame normalFrame = new NormalFrame();

        // when
        normalFrame.addScore(10);

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
        normalFrame.addScore(score);

        // then
        assertThat(normalFrame.isFinished()).isFalse();
    }

    private static Stream<Arguments> toPrint() {
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
}
