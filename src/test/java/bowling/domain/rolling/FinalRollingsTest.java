package bowling.domain.rolling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalRollingsTest {
    @Test
    public void setRollingResultStrke() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);

        assertThat(rollings.islastState(State.STRIKE)).isTrue();
    }

    @Test
    public void setRollingResultGutter() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(0);

        assertThat(rollings.islastState(State.GUTTER)).isTrue();
    }

    @Test
    public void setRollingResultSpare() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        assertThat(rollings.islastState(State.SPARE)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 9})
    public void setRollingResultMissFirstRolling(int knockedPinCount) {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(knockedPinCount);

        assertThat(rollings.islastState(State.MISS)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    public void setRollingResultMissNotFirstRolling(int knockedPinCount) {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(3);
        rollings.roll(knockedPinCount);

        assertThat(rollings.islastState(State.MISS)).isTrue();
    }

    @Test
    public void setRollingBonusResultMiss() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(3);

        assertThat(rollings.islastState(State.MISS)).isTrue();
    }

    @Test
    public void setRollingBonusResultGutter() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(0);

        assertThat(rollings.islastState(State.GUTTER)).isTrue();
    }

    @Test
    public void setRollingBonusResultStrike() {
        FinalRollings rollings = FinalRollings.init();
        rollings.roll(10);
        rollings.roll(10);

        assertThat(rollings.islastState(State.STRIKE)).isTrue();
    }
}
