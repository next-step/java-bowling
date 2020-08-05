package bowling.domain.rolling;

import bowling.domain.frame.Score;
import bowling.domain.state.StateFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalRollingsTest {
    @Test
    @DisplayName("Strike 후 프레임 상태 확인")
    public void setRollingResultStrike() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);

        assertThat(rollings.isState(StateFormat.STRIKE)).isTrue();
    }

    @Test
    @DisplayName("Gutter 후 프레임 상태 확인")
    public void setRollingResultGutter() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(0);

        assertThat(rollings.isState(StateFormat.GUTTER)).isTrue();
    }

    @Test
    @DisplayName("Spare 후 프레임 상태 확인")
    public void setRollingResultSpare() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        assertThat(rollings.isState(StateFormat.SPARE)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Miss 후 프레임 상태 확인")
    @ValueSource(ints = {1, 3, 9})
    public void setRollingResultMissFirstRolling(int knockedPinCount) {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(knockedPinCount);

        assertThat(rollings.isState(StateFormat.MISS)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("연속 Miss 후 프레임 상태 확인")
    @ValueSource(ints = {1, 6})
    public void setRollingResultMissNotFirstRolling(int knockedPinCount) {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(3);
        rollings.roll(knockedPinCount);

        assertThat(rollings.isState(StateFormat.MISS)).isTrue();
    }

    @Test
    @DisplayName("Strike-Miss 후 프레임 상태 확인")
    public void setRollingBonusResultMiss() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(3);

        assertThat(rollings.isState(StateFormat.MISS)).isTrue();
        assertThat(rollings.isRollingPossible()).isFalse();
    }

    @Test
    @DisplayName("보너스 라운드에서 Gutter 투구 후 프레임 상태 확인")
    public void setRollingBonusResultGutter() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(0);

        assertThat(rollings.isState(StateFormat.GUTTER)).isTrue();
        assertThat(rollings.isRollingPossible()).isFalse();
    }

    @Test
    @DisplayName("보너스 라운드에서 Strike 투구 후 프레임 상태 확인")
    public void setRollingBonusResultStrike() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(10);

        assertThat(rollings.isState(StateFormat.STRIKE)).isTrue();
        assertThat(rollings.isRollingPossible()).isFalse();
    }

    @Test
    @DisplayName("프레임의 일반 투구가 Gutter 일 떄 점수 계산")
    public void checkCalculateScoreWhenGutter() {
        int gutterPinCount = 0;
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(gutterPinCount);

        assertThat(rollings.calculateScore()).isEqualTo(gutterPinCount);
    }

    @Test
    @DisplayName("프레임의 일반 투구가 Miss 일 떄 점수 계산")
    public void checkCalculateScoreWhenMiss() {
        int missPinCount = 6;
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(missPinCount);

        assertThat(rollings.calculateScore()).isEqualTo(missPinCount);
    }

    @Test
    @DisplayName("프레임의 일반 투구가 Strike 일 떄 점수 계산")
    public void checkCalculateScoreWhenStrike() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);

        assertThat(rollings.calculateScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("프레임의 일반 투구가 Spare 일 떄 점수 계산")
    public void checkCalculateScoreWhenSpare() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(2);
        rollings.roll(8);

        assertThat(rollings.calculateScore()).isEqualTo(10);
    }

    @ParameterizedTest
    @DisplayName("프레임의 보너스 투구까지 완료 후 점수 계산 확인")
    @ValueSource(ints = {0, 5, 10})
    public void calculateScoreOfAllRollings(int knockedDownPinCount) {
        int initialScore = 10;
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(initialScore);
        rollings.roll(knockedDownPinCount);

        assertThat(rollings.calculateScore()).isEqualTo(initialScore + knockedDownPinCount);
    }

    @Test
    @DisplayName("투구 1회 추가 계산")
    public void calculateOneAdditionalScoreOfAllRollings() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(3);

        Score score = Score.newScore(2, 1);
        rollings.calculateAdditionalScore(score);

        assertThat(score.isCalculateDone()).isTrue();
        assertThat(score.getScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("투구 2회 추가 계산")
    public void calculateTwoAdditionalScoreOfAllRollings() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        Score score = Score.newScore(2, 2);
        rollings.calculateAdditionalScore(score);

        assertThat(score.isCalculateDone()).isTrue();
        assertThat(score.getScore()).isEqualTo(12);
    }
}
