package bowling.state;

import bowling.domain.state.Bonus;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    private final State strike = new Strike();

    @Test
    void 스트라이크에서_한번더투구하면_보너스를반환() {
        assertThat(strike.bowl(5)).isInstanceOf(Bonus.class);
    }
}
