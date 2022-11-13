package bowling.domain;

import bowling.strategy.FallenPinCalculateStrategy;
import bowling.type.BowlingScore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralScoreTest {

    static Stream<Arguments> bowlingScoreArgs() {
        return Stream.of(
                Arguments.of(new GeneralScore(Arrays.asList(0)), BowlingScore.NONE),
                Arguments.of(new GeneralScore(Arrays.asList(10)), BowlingScore.STRIKE),
                Arguments.of(new GeneralScore(Arrays.asList(5,5)), BowlingScore.SPARE),
                Arguments.of(new GeneralScore(Arrays.asList(7,1)), BowlingScore.MISS),
                Arguments.of(new GeneralScore(Arrays.asList(8,0)), BowlingScore.GUTTER)
        );
    }

    @Test
    void testIsStrike() {
        GeneralScore generalScore = new GeneralScore(maxNum -> 10);
        assertThat(generalScore.isStrike()).isTrue();
    }

    @Test
    void testNextScore(){
        GeneralScore generalScore = new GeneralScore(maxNum -> 5);
        generalScore.next();

        assertThat(generalScore).isEqualTo(new GeneralScore(Arrays.asList(5,5)));
    }

    @ParameterizedTest
    @MethodSource("bowlingScoreArgs")
    void testBowlingScore(GeneralScore generalScore, BowlingScore result){
        assertThat(generalScore.getBowlingScore()).isEqualTo(result);
    }

}
