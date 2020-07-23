package bowling.domain.rolling;

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

        assertThat(rollings.isState(State.STRIKE)).isTrue();
    }

    @Test
    @DisplayName("Gutter 후 프레임 상태 확인")
    public void setRollingResultGutter() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(0);

        assertThat(rollings.isState(State.GUTTER)).isTrue();
    }

    @Test
    @DisplayName("Spare 후 프레임 상태 확인")
    public void setRollingResultSpare() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        assertThat(rollings.isState(State.SPARE)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Miss 후 프레임 상태 확인")
    @ValueSource(ints = {1, 3, 9})
    public void setRollingResultMissFirstRolling(int knockedPinCount) {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(knockedPinCount);

        assertThat(rollings.isState(State.MISS)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("연속 Miss 후 프레임 상태 확인")
    @ValueSource(ints = {1, 6})
    public void setRollingResultMissNotFirstRolling(int knockedPinCount) {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(3);
        rollings.roll(knockedPinCount);

        assertThat(rollings.isState(State.MISS)).isTrue();
    }

    @Test
    @DisplayName("Strike-Miss 후 프레임 상태 확인")
    public void setRollingBonusResultMiss() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(3);

        assertThat(rollings.isState(State.MISS)).isTrue();
        assertThat(rollings.isRollingPossible()).isFalse();
    }

    @Test
    @DisplayName("보너스 라운드에서 Gutter 투구 후 프레임 상태 확인")
    public void setRollingBonusResultGutter() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(0);

        assertThat(rollings.isState(State.GUTTER)).isTrue();
        assertThat(rollings.isRollingPossible()).isFalse();
    }

    @Test
    @DisplayName("보너스 라운드에서 Strike 투구 후 프레임 상태 확인")
    public void setRollingBonusResultStrike() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(10);

        assertThat(rollings.isState(State.STRIKE)).isTrue();
        assertThat(rollings.isRollingPossible()).isFalse();
    }
}
