package bowling.domain.state;

import bowling.exception.BowlingStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StateTest {
    @Test
    void ready() {
        State ready = new Ready();
        assertThat(ready).isInstanceOf(Ready.class);
    }

    @Test
    void ready_strike() {
        State ready = new Ready();
        assertThat(ready.bowl(10)).isInstanceOf(Strike.class);
    }

    @Test
    void firstBowl_miss() {
        State firstBowl = new FirstBowl(1);
        assertThat(firstBowl.bowl(1)).isInstanceOf(Miss.class);
    }

    @Test
    void firstBowl_spare() {
        State firstBowl = new FirstBowl(1);
        assertThat(firstBowl.bowl(9)).isInstanceOf(Spare.class);
    }

    @Test
    void bonus() {
        State bonus = new Bonus(1, 2);
        assertThat(bonus.bowl(1)).isInstanceOf(Bonus.class);
    }

    @DisplayName("bonus count가 1인데 두번 보너스볼을 굴리면 에러")
    @Test
    void bonus_error() {
        State bonus = new Bonus(1, 1);
        assertThatThrownBy(()->bonus.bowl(2)).isInstanceOf(BowlingStateException.class);
    }
}