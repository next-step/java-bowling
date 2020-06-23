package bowling.score;

import bowling.exception.BowlingBuildingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @DisplayName("Score 객체 정상 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 10})
    public void makeScore_정상(int score) {
        Score object = Score.valueOf(score);

        assertThat(object.getScore()).isEqualTo(score);
    }

    @DisplayName("Score 객체 생성 실패(잘못된 범위의 정수)")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void makeScore_예외(int score) {
        assertThatThrownBy(() -> {
            Score.valueOf(score);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_SCORE_RANGE);
    }

    @DisplayName("Score가 10이면 isMaximumScore은 True")
    @Test
    public void isMaximumScore_True() {
        assertThat(Score.valueOf(10).isMaximumScore()).isTrue();
        assertThat(Score.valueOf(9).isMaximumScore()).isFalse();
    }

    @DisplayName("Score가 0이면 isMinimumScore은 True")
    @Test
    public void isMinimumScore_True() {
        assertThat(Score.valueOf(0).isMinimumScore()).isTrue();
        assertThat(Score.valueOf(1).isMinimumScore()).isFalse();
    }
}
