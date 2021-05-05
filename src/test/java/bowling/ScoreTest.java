package bowling;

import bowling.domain.frame.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ScoreTest {

    private static Stream<Arguments> ScoreValueOfTestArguments() {
        return Stream.of(
                arguments(0, Score.GUTTER),
                arguments(1, Score.ONE),
                arguments(2, Score.TWO),
                arguments(3, Score.THREE),
                arguments(4, Score.FOUR),
                arguments(5, Score.FIVE),
                arguments(6, Score.SIX),
                arguments(7, Score.SEVEN),
                arguments(8, Score.EIGHT),
                arguments(9, Score.NINE),
                arguments(10, Score.STRIKE)
        );
    }

    @ParameterizedTest
    @MethodSource("ScoreValueOfTestArguments")
    public void ScoreValueOfTestArguments(int score, Score expectScore) {
        // given

        // when

        // then
        Assertions.assertThat(Score.valueOf(score)).isEqualTo(expectScore);
    }

    private static Stream<Arguments> ScoreValueOfTestArguments_2개이상() {
        return Stream.of(
                arguments(Score.GUTTER, 10, Score.SPARE),
                arguments(Score.ONE, 9, Score.SPARE),
                arguments(Score.TWO, 5, Score.FIVE),
                arguments(Score.THREE, 0, Score.GUTTER),
                arguments(Score.FOUR, 4, Score.FOUR),
                arguments(Score.FIVE, 1, Score.ONE),
                arguments(Score.SIX, 4, Score.SPARE),
                arguments(Score.SEVEN, 3, Score.SPARE),
                arguments(Score.EIGHT, 0, Score.GUTTER),
                arguments(Score.NINE, 1, Score.SPARE),
                arguments(Score.STRIKE, 0, Score.GUTTER),
                arguments(Score.STRIKE, 10, Score.STRIKE)
        );
    }

    @ParameterizedTest
    @MethodSource("ScoreValueOfTestArguments_2개이상")
    public void ScoreValueOfTestArguments_2개이상(Score previousScore, int nowScore, Score expectScore) {
        // given

        // when

        // then
        Assertions.assertThat(Score.valueOf(previousScore, nowScore)).isEqualTo(expectScore);
    }
}