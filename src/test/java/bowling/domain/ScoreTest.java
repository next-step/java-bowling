package bowling.domain;

import bowling.step2.domain.Score;
import bowling.step2.exception.ScoreRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @DisplayName("스코어 입력 값이 정상적이지 않을 경우 ScoreRangeException 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 스코어_검증_테스트 (int score) {
        assertThatExceptionOfType(ScoreRangeException.class)
            .isThrownBy(() -> Score.valueOf(score));
    }
}
