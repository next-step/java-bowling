package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @DisplayName("투구 점수를 받아 결과를 확인한다")
    @ParameterizedTest
    @MethodSource("findByScoresArguments")
    void findByScores(int firstScore, int secondScore, Result expected) {
        Score first = Score.of(firstScore);
        Score second = Score.of(secondScore);
        Scores scores = Scores.from(first);
        scores.inputSecondScore(second);

        Result result = Result.findByScores(scores);

        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> findByScoresArguments() {
        return Stream.of(
                Arguments.of(10, 0, Result.STRIKE),
                Arguments.of(0, 10, Result.SPARE),
                Arguments.of(5, 5, Result.SPARE),
                Arguments.of(1, 1, Result.MISS),
                Arguments.of(0, 1, Result.MISS),
                Arguments.of(0, 0, Result.GUTTER)
        );
    }
}
