package bowling.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.state.Bonus;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    private final State spare = new Spare(new KnockedPinCount(5), new KnockedPinCount(5));

    @Test
    void 스페어에서_한번더투구하면_보너스를반환() {
        assertThat(spare.bowl(5)).isInstanceOf(Bonus.class);
    }
}
