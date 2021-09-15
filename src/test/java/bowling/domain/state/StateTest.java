package bowling.domain.state;

import bowling.exception.BowlingStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StateTest {
    @DisplayName("공을 굴리지 않은 최초 상태는 ready")
    @Test
    void ready() {
        State ready = new Ready();
        assertThat(ready).isInstanceOf(Ready.class);
    }

    @DisplayName("공을 한번 굴려서 10점이 나오면 strike")
    @Test
    void ready_strike() {
        State ready = new Ready();
        assertThat(ready.bowl(10)).isInstanceOf(Strike.class);
    }

    @DisplayName("두번째 굴려서 총합이 10점이 안될경우 miss")
    @Test
    void firstBowl_miss() {
        State firstBowl = new FirstBowl(1);
        assertThat(firstBowl.bowl(1)).isInstanceOf(Miss.class);
    }

    @DisplayName("두번 굴려서 10점이 나오면 spare")
    @Test
    void firstBowl_spare() {
        State firstBowl = new FirstBowl(1);
        assertThat(firstBowl.bowl(9)).isInstanceOf(Spare.class);
    }

    @DisplayName("bonus count가 0보다 크면 보너스 투구 상태")
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