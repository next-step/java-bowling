package bowling.score;

import bowling.exception.BowlingBuildingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @DisplayName("Score 객체 정상 생성")
    @Test
    public void makeScore_정상() {
        assertThatCode(() -> {
            Score.valueOf(0);
            Score.valueOf(10);
        }).doesNotThrowAnyException();
    }

    @DisplayName("Score 객체 생성 실패(잘못된 범위의 정수)")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void makeScore_정상(int score) {
        assertThatThrownBy(() -> {
            Score.valueOf(score);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_SCORE_RANGE);
    }
}
