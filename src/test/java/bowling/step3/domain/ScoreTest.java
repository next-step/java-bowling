package bowling.step3.domain;

import bowling.step2.domain.Score;
import bowling.step2.exception.ScoreRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    @DisplayName("스코어 입력 값이 정상적이지 않을 경우 ScoreRangeException 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 스코어_검증_테스트(int score) {
        assertThatExceptionOfType(ScoreRangeException.class)
            .isThrownBy(() -> Score.valueOf(score));
    }

    @DisplayName("값 객체의 재사용 여부를 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void 스코어_재사용_테스트(int value) {
        Score score = Score.valueOf(value);
        assertEquals(Score.valueOf(value), score);
    }

    @DisplayName("스코어의 스트라이크 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {10})
    void 스코어_스트라이크_일치_테스트(int value) {
        Score score = Score.valueOf(value);
        assertEquals(Score.getStrike(), score);
    }

    @DisplayName("스코어의 스트라이크 불일치 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void 스코어_스트라이크_불일치_테스트(int value) {
        Score score = Score.valueOf(value);
        assertEquals(false, score == Score.getStrike());
    }

    @DisplayName("스코어의 합계를 확인하는 테스트")
    @ParameterizedTest
    @MethodSource("provideScoresAndSumScore")
    void 스코어_합_테스트(Score score1, Score score2, Score expected) {
        assertEquals(expected, score1.sum(score2));
    }

    private static Stream<Arguments> provideScoresAndSumScore() {
        return Stream.of(
            Arguments.of(Score.valueOf(0), Score.valueOf(0), Score.valueOf(0)),
            Arguments.of(Score.valueOf(1), Score.valueOf(0), Score.valueOf(1)),
            Arguments.of(Score.valueOf(1), Score.valueOf(1), Score.valueOf(2)),
            Arguments.of(Score.valueOf(2), Score.valueOf(1), Score.valueOf(3)),
            Arguments.of(Score.valueOf(3), Score.valueOf(1), Score.valueOf(4)),
            Arguments.of(Score.valueOf(4), Score.valueOf(1), Score.valueOf(5)),
            Arguments.of(Score.valueOf(5), Score.valueOf(1), Score.valueOf(6)),
            Arguments.of(Score.valueOf(6), Score.valueOf(1), Score.valueOf(7)),
            Arguments.of(Score.valueOf(7), Score.valueOf(1), Score.valueOf(8)),
            Arguments.of(Score.valueOf(8), Score.valueOf(1), Score.valueOf(9)),
            Arguments.of(Score.valueOf(9), Score.valueOf(1), Score.valueOf(10)),
            Arguments.of(Score.valueOf(10), Score.valueOf(0), Score.valueOf(10))
        );
    }
}
