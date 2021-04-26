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
    public void valueOf(int score, Score expectScore) {
        // given

        // when

        // then
        Assertions.assertThat(Score.valueOf(score)).isEqualTo(expectScore);
    }
}
