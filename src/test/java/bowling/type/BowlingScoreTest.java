package bowling.type;

import bowling.domain.Scores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingScoreTest {

    static Stream<Arguments> bowlingScoreArgs() {
        return Stream.of(
                Arguments.of(new Scores(Arrays.asList(0)), BowlingScore.NONE),
                Arguments.of(new Scores(Arrays.asList(10)), BowlingScore.STRIKE),
                Arguments.of(new Scores(Arrays.asList(5,5)), BowlingScore.SPARE),
                Arguments.of(new Scores(Arrays.asList(7,1)), BowlingScore.MISS),
                Arguments.of(new Scores(Arrays.asList(8,0)), BowlingScore.GUTTER)
        );
    }

    @ParameterizedTest
    @MethodSource("bowlingScoreArgs")
    void testBowlingScore(Scores scores, BowlingScore result){
        assertThat(BowlingScore.from(scores)).isEqualTo(result);
    }
}
