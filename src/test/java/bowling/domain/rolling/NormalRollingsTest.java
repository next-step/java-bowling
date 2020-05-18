package bowling.domain.rolling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalRollingsTest {
    @Test
    public void setRollingResultStrke() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(10);

        assertThat(rollings.isState(State.STRIKE)).isTrue();
    }

    @Test
    public void setRollingResultGutter() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(0);

        assertThat(rollings.isState(State.GUTTER)).isTrue();
    }

    @Test
    public void setRollingResultSpare() {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(3);
        rollings.roll(7);

        assertThat(rollings.isState(State.SPARE)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 9})
    public void setRollingResultMissFirstRolling(int knockedPinCount) {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(knockedPinCount);

        assertThat(rollings.isState(State.MISS)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    public void setRollingResultMissNotFirstRolling(int knockedPinCount) {
        NormalRollings rollings = NormalRollings.init();
        rollings.roll(3);
        rollings.roll(knockedPinCount);

        assertThat(rollings.isState(State.MISS)).isTrue();
    }
}
