package bowling.step3.domain;

import bowling.step2.domain.Score;
import bowling.step2.domain.ScoreType;
import bowling.step2.domain.scores.FinalScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinalScoresTest {

    @DisplayName("스코어의 첫 번째 값이 스트라이크인지 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideStrikeScores")
    void 스트라이크_테스트(FinalScores scores) {
        assertEquals(true, scores.isType(ScoreType.STRIKE));
    }

    private static Stream<Arguments> provideStrikeScores() {
        return Stream.of(
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(10))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(10))
                           .nextInit(Score.valueOf(1))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(10))
                           .nextInit(Score.valueOf(2))
            )
        );
    }

    @DisplayName("스코어의 값이 전부 채워졌는지 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideFullyScores")
    void 스코어_채워짐_확인_테스트(FinalScores scores) {
        assertEquals(true, scores.isFullOf());
    }

    private static Stream<Arguments> provideFullyScores() {
        return Stream.of(
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(1))
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(3))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(3))
                           .nextInit(Score.valueOf(4))
                           .nextInit(Score.valueOf(5))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(5))
                           .nextInit(Score.valueOf(6))
                           .nextInit(Score.valueOf(7))
            )
        );
    }

    @DisplayName("스코어가 스페어 되었는지 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideSparedScores")
    void 스코어_스페어_테스트(FinalScores scores) {
        assertEquals(true, scores.isType(ScoreType.SPARED));
    }

    private static Stream<Arguments> provideSparedScores() {
        return Stream.of(
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(1))
                           .nextInit(Score.valueOf(9))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(8))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(3))
                           .nextInit(Score.valueOf(7))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(4))
                           .nextInit(Score.valueOf(6))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(5))
                           .nextInit(Score.valueOf(5))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(1))
                           .nextInit(Score.valueOf(9))
                           .nextInit(Score.valueOf(1))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(8))
                           .nextInit(Score.valueOf(2))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(3))
                           .nextInit(Score.valueOf(7))
                           .nextInit(Score.valueOf(3))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(4))
                           .nextInit(Score.valueOf(6))
                           .nextInit(Score.valueOf(4))
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(5))
                           .nextInit(Score.valueOf(5))
                           .nextInit(Score.valueOf(5))
            )
        );
    }

    @DisplayName("스코어들의 합계를 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideScoresAndSum")
    void 스코어_합계_테스트(FinalScores scores, int expected) {
        assertEquals(expected, scores.totalScore());
    }

    private static Stream<Arguments> provideScoresAndSum() {
        return Stream.of(
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(1)),
                1
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(8)),
                10
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(7)),
                7
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(0)),
                0
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(2))
                           .nextInit(Score.valueOf(8))
                           .nextInit(Score.valueOf(0)),
                10
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(7))
                           .nextInit(Score.valueOf(7)),
                14
            ),
            Arguments.of(
                FinalScores.init()
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(0))
                           .nextInit(Score.valueOf(0)),
                0
            )
        );
    }
}
