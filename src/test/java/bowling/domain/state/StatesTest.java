package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bowling.domain.state.progress.Ready;

public class StatesTest {
    @Test
    void 생성_테스트() {
        States states = new States(new ArrayList<>());
        states.add(new Ready().bowl(BowlingPin.of(5)));

        assertThat(states.size()).isEqualTo(1);
    }

    @Test
    void 보너스_존재_테스트() {
        States states = new States(new ArrayList<>());
        states.add(new Ready().bowl(BowlingPin.of(5)));
        states.add(new Ready().bowl(BowlingPin.of(5)));

        assertThat(states.hasNotBonus()).isTrue();
    }
}
