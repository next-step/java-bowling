package bowling.domain;

import bowling.step2.domain.Score;
import bowling.step2.exception.ScoreRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    @DisplayName("스코어 입력 값이 정상적이지 않을 경우 ScoreRangeException 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 스코어_검증_테스트 (int score) {
        assertThatExceptionOfType(ScoreRangeException.class)
            .isThrownBy(() -> Score.valueOf(score));
    }

    @DisplayName("값 객체의 재사용 여부를 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void 스코어_재사용_테스트 (int value) {
        Score score = Score.valueOf(value);
        assertEquals(Score.valueOf(value), score);
    }

    @DisplayName("스코어의 스트라이크 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = { 10 })
    void 스코어_스트라이크_일치_테스트 (int value) {
        Score score = Score.valueOf(value);
        assertEquals(Score.getStrike(), score);
    }

    @DisplayName("스코어의 스트라이크 불일치 테스트")
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 })
    void 스코어_스트라이크_불일치_테스트 (int value) {
        Score score = Score.valueOf(value);
        assertEquals(false, score == Score.getStrike());
    }
}
