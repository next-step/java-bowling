package bowling.domain.rolling;

import bowling.domain.frame.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RollingTest {
    @Test
    public void setRollingResultStrke() {
        Rolling rolling = Rolling.roll(null, 10);

        assertThat(rolling.getState()).isEqualTo(State.STRIKE);
    }

    @Test
    public void setRollingResultGutter() {
        Rolling rolling = Rolling.roll(null, 0);

        assertThat(rolling.getState()).isEqualTo(State.GUTTER);
    }

    @Test
    public void setRollingResultSpare() {
        Rolling firstRolling = Rolling.roll(null, 3);
        Rolling secondRolling = Rolling.roll(firstRolling, 7);

        assertThat(secondRolling.getState()).isEqualTo(State.SPARE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 9})
    public void setRollingResultMissFirstRolling(int knockedPinCount) {
        Rolling rolling = Rolling.roll(null, knockedPinCount);

        assertThat(rolling.getState()).isEqualTo(State.MISS);
    }

    @Test
    @ValueSource(ints = {1, 6})
    public void setRollingResultMissNotFirstRolling(int knockedPinCount) {
        Rolling firstRolling = Rolling.roll(null, 3);
        Rolling secondRolling = Rolling.roll(firstRolling, knockedPinCount);

        assertThat(secondRolling.getState()).isEqualTo(State.MISS);
    }
}
