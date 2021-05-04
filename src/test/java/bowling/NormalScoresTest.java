package bowling;

import bowling.domain.frame.NormalScores;
import bowling.domain.frame.Score;
import bowling.domain.frame.Scores;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NormalScoresTest {

    private static Stream<Arguments> NormalFrame_addScore() {
        return Stream.of(
                arguments(0, 5, new NormalScores(Arrays.asList(Score.GUTTER, Score.FIVE))),
                arguments(1, 9, new NormalScores(Arrays.asList(Score.ONE, Score.SPARE))),
                arguments(2, 8, new NormalScores(Arrays.asList(Score.TWO, Score.SPARE))),
                arguments(3, 4, new NormalScores(Arrays.asList(Score.THREE, Score.FOUR))),
                arguments(4, 3, new NormalScores(Arrays.asList(Score.FOUR, Score.THREE))),
                arguments(5, 5, new NormalScores(Arrays.asList(Score.FIVE, Score.SPARE))),
                arguments(6, 4, new NormalScores(Arrays.asList(Score.SIX, Score.SPARE))),
                arguments(7, 1, new NormalScores(Arrays.asList(Score.SEVEN, Score.ONE))),
                arguments(8, 1, new NormalScores(Arrays.asList(Score.EIGHT, Score.ONE))),
                arguments(9, 1, new NormalScores(Arrays.asList(Score.NINE, Score.SPARE)))
        );
    }

    @ParameterizedTest
    @MethodSource("NormalFrame_addScore")
    public void NormalFrame_addScore(int score1, int score2, Scores expectScores) throws Exception {
        // given
        Scores normalScores = new NormalScores();

        // when
        normalScores.addScore(score1);
        normalScores.addScore(score2);

        // then
        assertThat(normalScores).isEqualTo(expectScores);
    }


    @Test
    public void NormalFrame_addScore_STRIKE() throws Exception {
        // given
        Scores normalScores = new NormalScores();
        Scores expectScores = new NormalScores(Arrays.asList(Score.STRIKE));
        // when
        normalScores.addScore(10);

        // then
        assertThat(normalScores).isEqualTo(expectScores);
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
        Scores normalScores = new NormalScores();

        // when
        normalScores.addScore(score1);
        normalScores.addScore(score2);

        // then
        assertThat(normalScores.isFinished()).isTrue();
    }

    @Test
    public void NormalFrameIsFinished_프레임이_끝난_경우2() throws Exception {
        // given
        Scores normalScores = new NormalScores();

        // when
        normalScores.addScore(10);

        // then
        assertThat(normalScores.isFinished()).isTrue();
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
        Scores normalScores = new NormalScores();

        // when
        normalScores.addScore(score);

        // then
        assertThat(normalScores.isFinished()).isFalse();
    }

}
