package bowling.domain.score;

import bowling.domain.exception.BowlingBuildingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PitchScoreTest {

    @DisplayName("Score 객체 정상 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 10})
    public void makeScore_정상(int score) {
        PitchScore object = PitchScore.valueOf(score);

        assertThat(object.getPitchScore()).isEqualTo(score);
    }

    @DisplayName("Score 객체 생성 실패(잘못된 범위의 정수)")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void makeScore_예외(int score) {
        assertThatThrownBy(() -> {
            PitchScore.valueOf(score);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PITCH_SCORE_RANGE);
    }

    @DisplayName("Score가 10이면 isMaximumScore은 True")
    @Test
    public void isMaximumScore_True() {
        assertThat(PitchScore.valueOf(10).isMaximum()).isTrue();
        assertThat(PitchScore.valueOf(9).isMaximum()).isFalse();
    }

    @DisplayName("Score가 0이면 isMinimumScore은 True")
    @Test
    public void isMinimumScore_True() {
        assertThat(PitchScore.valueOf(0).isMinimum()).isTrue();
        assertThat(PitchScore.valueOf(1).isMinimum()).isFalse();
    }
}
