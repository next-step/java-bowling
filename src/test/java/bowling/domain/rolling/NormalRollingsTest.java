package bowling.domain.rolling;

import bowling.domain.frame.Score;
import bowling.domain.state.StateFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalRollingsTest {
    @Test
    public void setRollingResultStrke() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(10);

        assertThat(rollings.isState(StateFormat.STRIKE)).isTrue();
    }

    @Test
    public void setRollingResultGutter() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(0);

        assertThat(rollings.isState(StateFormat.GUTTER)).isTrue();
    }

    @Test
    public void setRollingResultSpare() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        assertThat(rollings.isState(StateFormat.SPARE)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 9})
    public void setRollingResultMissFirstRolling(int knockedPinCount) {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(knockedPinCount);

        assertThat(rollings.isState(StateFormat.MISS)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    public void setRollingResultMissNotFirstRolling(int knockedPinCount) {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(3);
        rollings.roll(knockedPinCount);

        assertThat(rollings.isState(StateFormat.MISS)).isTrue();
    }

    @Test
    @DisplayName("프레임의 투구가 끝난 후 점수 계산 확인")
    public void calculateScoreOfAllRollings() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        assertThat(rollings.calculateScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("투구 1회 추가 계산")
    public void calculateOneAdditionalScoreOfAllRollings() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        Score score = Score.newScore(2, 1);
        rollings.calculateAdditionalScore(score);

        assertThat(score.isCalculateDone()).isTrue();
        assertThat(score.getScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("투구 2회 추가 계산")
    public void calculateTwoAdditionalScoreOfAllRollings() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        Score score = Score.newScore(2, 2);
        rollings.calculateAdditionalScore(score);

        assertThat(score.isCalculateDone()).isTrue();
        assertThat(score.getScore()).isEqualTo(12);
    }
}
