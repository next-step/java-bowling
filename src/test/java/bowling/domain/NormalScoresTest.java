package bowling.domain;

import bowling.step2.domain.Score;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalScoresTest {

    @DisplayName("스코어의 첫 번째 값이 스트라이크인지 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideStrikeScores")
    void 스트라이크_테스트 (Scores scores) {
        assertEquals(true, scores.isStrike());
    }

    private static Stream<Arguments> provideStrikeScores () {
        return Stream.of(
            Arguments.of(
                NormalScores.init()
                            .nextInit(Score.valueOf(10))
            ),
            Arguments.of(
                NormalScores.init()
                            .nextInit(Score.valueOf(10))
                            .nextInit(Score.valueOf(1))
            ),
            Arguments.of(
                NormalScores.init()
                            .nextInit(Score.valueOf(10))
                            .nextInit(Score.valueOf(2))
            )
        );
    }
}
